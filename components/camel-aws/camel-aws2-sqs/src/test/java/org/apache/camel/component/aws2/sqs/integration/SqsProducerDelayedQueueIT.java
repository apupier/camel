/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.aws2.sqs.integration;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.camel.EndpointInject;
import org.apache.camel.component.mock.MockEndpoint;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.sqs.model.ListQueuesResponse;

import static org.assertj.core.api.Assertions.assertThat;

class SqsProducerDelayedQueueIT extends Aws2SQSBaseTest {

    @EndpointInject("mock:result")
    private MockEndpoint result;

    @Test
    void delayedQueue() throws Exception {
        int delay = 20;
        String delayedQueueuName = sharedNameGenerator.getName() + "_delayed";
        Instant start = Instant.now();
        // create delayed queue
        template.requestBody(
                String.format("aws2-sqs://%s?autoCreateQueue=true&delayQueue=true&delaySeconds=%d&operation=listQueues",
                        delayedQueueuName, delay),
                null, ListQueuesResponse.class).queueUrls();

        String msg = sendSingleMessageToQueue(delayedQueueuName);
        awaitMessageWithExpectedContentFromQueue(msg, delayedQueueuName);

        assertThat(Duration.between(start, Instant.now()).getSeconds()).isGreaterThanOrEqualTo(delay);
    }

    private void awaitMessageWithExpectedContentFromQueue(String expectedContent, String queueName) {
        Awaitility.await().pollInterval(1, TimeUnit.SECONDS).atMost(120, TimeUnit.SECONDS)
                .until(() -> expectedContent.equals(receiveMessageFromQueue(queueName, false)));

    }

    String sendSingleMessageToQueue(String queueName) {
        final String msg = "sqs" + UUID.randomUUID().toString().replace("-", "");
        return template.requestBody("aws2-sqs://" + queueName, msg, String.class);
    }

    String receiveMessageFromQueue(String queueName, boolean deleteMessage) {
        return consumer.receiveBody(
                String.format("aws2-sqs://%s?deleteAfterRead=%s&deleteIfFiltered=%s&defaultVisibilityTimeout=0",
                        queueName, deleteMessage, deleteMessage),
                10000, String.class);
    }
}

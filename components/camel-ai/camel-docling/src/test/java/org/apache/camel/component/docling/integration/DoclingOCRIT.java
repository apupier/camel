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
package org.apache.camel.component.docling.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.docling.DoclingComponent;
import org.apache.camel.component.docling.DoclingConfiguration;
import org.apache.camel.component.docling.DoclingHeaders;
import org.apache.camel.test.infra.docling.services.DoclingService;
import org.apache.camel.test.infra.docling.services.DoclingServiceFactory;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DisabledIfSystemProperty(named = "ci.env.name", matches = ".*", disabledReason = "Too much resources on GitHub Actions")
class DoclingOCRIT extends CamelTestSupport {

	private static final Logger LOG = LoggerFactory.getLogger(DoclingOCRIT.class);

	@RegisterExtension
	static DoclingService doclingService = DoclingServiceFactory.createService();

	@Override
	protected CamelContext createCamelContext() throws Exception {
		CamelContext context = super.createCamelContext();
		DoclingComponent docling = context.getComponent("docling", DoclingComponent.class);
		DoclingConfiguration conf = new DoclingConfiguration();
		conf.setUseDoclingServe(true);
		conf.setDoclingServeUrl(doclingService.doclingServerUrl());
		docling.setConfiguration(conf);

		LOG.info("Testing Docling-Serve metadata extraction at: {}", doclingService.doclingServerUrl());

		return context;
	}

	@Test
	void testOCROnPng() throws Exception {
		testOCR("test-ocr.png");
	}

	@Test
	void testOCROnPdf() throws Exception {
		testOCR("test-ocr.pdf");
	}

	private void testOCR(String fileName) throws IOException {
		Path testFile = createTestFile(fileName);

		String result = template.requestBodyAndHeader("direct:ocr", testFile.toString(),
				DoclingHeaders.INPUT_FILE_PATH,	testFile.toString(),
				String.class);

		assertThat(result).contains("test ocr in camel docling");
	}

	private Path createTestFile(String fileName) throws IOException {
		try (InputStream in = getClass().getClassLoader().getResourceAsStream(fileName)) {
			Path fileWithText = Files.createTempFile("docling-test-ocr", fileName.substring(fileName.indexOf(".")));
			Files.copy(in, fileWithText, StandardCopyOption.REPLACE_EXISTING);
			return fileWithText;
		}
	}

	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("direct:ocr").to("docling:convert?operation=EXTRACT_TEXT");
			}
		};
	}
}

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
package org.apache.camel.dsl.jbang.core.commands;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class DependencyCopyTest {

	@Test
	void testCopy(@TempDir Path tempDir) throws Exception {
		DependencyCopy dependencyCopy = new DependencyCopy(new CamelJBangMain());
		dependencyCopy.buildTool = "maven";
		Path route = Files.createTempFile("RouteForDependencyCopyTest", ".java");
		Files.writeString(route, """
import org.apache.camel.builder.RouteBuilder;

public class RouteForDependencyCopyTest extends RouteBuilder {

    public void configure() {
        from("timer:timerName?delay=1000")
			.to("log:mylogger");
    }

}
				""");
		dependencyCopy.filePaths = new Path[] {route};
		dependencyCopy.mainClassname = "CamelApplication";
		dependencyCopy.javaVersion = "17";
		dependencyCopy.outputDirectory = tempDir.toString();
		dependencyCopy.doCall();
	}
}

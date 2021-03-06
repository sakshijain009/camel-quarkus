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
package org.apache.camel.quarkus.core.graal;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.oracle.svm.core.jdk.Resources;

/**
 * Helper methods invoked from generated bytecode during image processing
 */
public class ResourceUtils {

    /**
     * Write a string resource into the native image.
     *
     * @param resourcePath the path under which the given {@code content} should be stored
     * @param content      the content that will be serialized using {@code UTF-8}
     */
    public static void registerResources(String resourcePath, String content) {
        try {
            try (InputStream in = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8))) {
                Resources.registerResource(resourcePath, in);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to store resource " + resourcePath, e);
        }
    }

}

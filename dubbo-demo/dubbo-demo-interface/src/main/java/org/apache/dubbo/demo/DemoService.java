/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.demo;

import java.util.concurrent.CompletableFuture;

// 定义了一个名为 DemoService 的接口
public interface DemoService {

    // 定义了一个方法，用于接收一个名字参数，并返回一个问候语字符串
    String sayHello(String name);

    // 定义了一个默认方法，用于异步地执行 sayHello 方法，返回一个 CompletableFuture 对象
    default CompletableFuture<String> sayHelloAsync(String name) {
        // 使用 CompletableFuture.completedFuture 方法创建一个已完成的 CompletableFuture 对象，
        // 该对象的结果为调用 sayHello 方法的返回值
        return CompletableFuture.completedFuture(sayHello(name));
    }
}

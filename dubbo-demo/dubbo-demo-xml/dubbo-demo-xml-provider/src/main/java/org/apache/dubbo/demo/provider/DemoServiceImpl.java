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
package org.apache.dubbo.demo.provider;

import org.apache.dubbo.demo.DemoService;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// DemoService 接口的实现类
public class DemoServiceImpl implements DemoService {
    private static final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

    // 实现 sayHello 方法
    @Override
    public String sayHello(String name) {
        // 输出日志，记录请求信息
        logger.info("Hello " + name + ", request from consumer: "
                + RpcContext.getServiceContext().getRemoteAddress());
        try {
            // 模拟处理时间
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 返回响应信息
        return "Hello " + name + ", response from provider: "
                + RpcContext.getServiceContext().getLocalAddress();
    }

    // 实现 sayHelloAsync 方法
    @Override
    public CompletableFuture<String> sayHelloAsync(String name) {
        // 创建一个 CompletableFuture 对象，并异步执行任务
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            //            try {
            //                Thread.sleep(1000);
            //            } catch (InterruptedException e) {
            //                e.printStackTrace();
            //            }
            // 这里可以进行异步处理，例如调用其他服务或者执行耗时操作

            // 返回异步结果
            return "async result";
        });
        // 返回 CompletableFuture 对象
        return cf;
    }
}

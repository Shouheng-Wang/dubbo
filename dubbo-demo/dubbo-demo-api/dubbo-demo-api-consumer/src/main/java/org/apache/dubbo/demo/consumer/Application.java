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
package org.apache.dubbo.demo.consumer;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.demo.DemoService;
import org.apache.dubbo.rpc.service.GenericService;

public class Application {

    // ZooKeeper 注册中心地址
    private static final String REGISTRY_URL = "zookeeper://127.0.0.1:2181";

    public static void main(String[] args) {
        runWithBootstrap();
    }

    // 使用 Dubbo Bootstrap 启动 Dubbo 服务
    private static void runWithBootstrap() {
        // 创建 ReferenceConfig 对象，用于引用远程服务
        ReferenceConfig<DemoService> reference = new ReferenceConfig<>();
        reference.setInterface(DemoService.class); // 设置服务接口
        reference.setGeneric("true"); // 设置为泛化调用模式

        // 创建 Dubbo Bootstrap 实例
        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap
                .application(new ApplicationConfig("dubbo-demo-api-consumer")) // 设置应用配置
                .registry(new RegistryConfig(REGISTRY_URL)) // 设置注册中心配置
                .protocol(new ProtocolConfig(CommonConstants.DUBBO, -1)) // 设置协议配置
                .reference(reference) // 设置引用远程服务的配置
                .start(); // 启动 Dubbo 服务

        // 从 Bootstrap 缓存中获取 DemoService 实例
        DemoService demoService = bootstrap.getCache().get(reference);
        // 调用远程服务的 sayHello 方法
        String message = demoService.sayHello("dubbo");
        System.out.println(message);

        // 泛化调用示例
        GenericService genericService = (GenericService) demoService;
        // 泛化调用远程服务的 sayHello 方法
        Object genericInvokeResult = genericService.$invoke(
                "sayHello", new String[] {String.class.getName()}, new Object[] {"dubbo generic invoke"});
        System.out.println(genericInvokeResult.toString());
    }
}

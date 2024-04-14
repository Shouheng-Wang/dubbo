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

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.demo.DemoService;

public class Application {

    // ZooKeeper 注册中心地址
    private static final String REGISTRY_URL = "zookeeper://127.0.0.1:2181";

    public static void main(String[] args) {
        startWithBootstrap();
    }

    // 使用 Dubbo Bootstrap 启动 Dubbo 服务
    private static void startWithBootstrap() {
        // 创建 ServiceConfig 对象，用于配置服务
        ServiceConfig<DemoServiceImpl> service = new ServiceConfig<>();
        service.setInterface(DemoService.class); // 设置服务接口
        service.setRef(new DemoServiceImpl()); // 设置服务实现类

        // 创建 Dubbo Bootstrap 实例
        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap
                .application(new ApplicationConfig("dubbo-demo-api-provider")) // 设置应用配置
                .registry(new RegistryConfig(REGISTRY_URL)) // 设置注册中心配置
                .protocol(new ProtocolConfig(CommonConstants.DUBBO, -1)) // 设置协议配置
                .service(service) // 设置服务配置
                .start() // 启动 Dubbo 服务
                .await(); // 等待服务启动完成
    }
}

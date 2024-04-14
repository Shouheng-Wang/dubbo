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

import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

public class Application {
    public static void main(String[] args) throws Exception {
        // 创建 AnnotationConfigApplicationContext 实例，用于加载 Spring 配置
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ProviderConfiguration.class);
        // 启动 Spring 容器
        context.start();
        // 等待用户输入，保持程序运行
        System.in.read();
    }

    // 配置类，使用 Spring 的注解方式配置 Dubbo
    @Configuration
    @EnableDubbo(scanBasePackages = "org.apache.dubbo.demo.provider")
    @PropertySource("classpath:/spring/dubbo-provider.properties") // 加载属性配置文件
    static class ProviderConfiguration {
        @Bean
        public RegistryConfig registryConfig() {
            // 创建 RegistryConfig 实例
            RegistryConfig registryConfig = new RegistryConfig();
            // 配置注册中心地址为 ZooKeeper 地址
            registryConfig.setAddress("zookeeper://127.0.0.1:2181");
            return registryConfig; // 返回 RegistryConfig 实例
        }
    }
}

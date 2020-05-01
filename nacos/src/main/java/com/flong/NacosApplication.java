package com.flong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description NacosApplication
 * @Date 2019/10/24 11:47
 * @Author liangjl
 * @Version V1.0
 * @Copyright (c) All Rights Reserved, 2019.
 */
@RestController
@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
public class NacosApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(NacosApplication.class, args);
        String port = applicationContext.getEnvironment().getProperty("server.port");
        System.err.println("port:" + port);

    }

    /**
     * @Descript 注入配置文件上下文 动态刷新  @value 貌似不支持动态刷新
     * @Date 2019/10/24 13:46
     * @Author liangjl
     */
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @GetMapping(value = "/test/{id}")
    public Object test(@PathVariable Integer id) {
        System.out.println("<><>><><><><>><><>" + id);
        return "当前实例------> A" + "当前端口------>"  +applicationContext.getEnvironment().getProperty("server.port");
    }
}

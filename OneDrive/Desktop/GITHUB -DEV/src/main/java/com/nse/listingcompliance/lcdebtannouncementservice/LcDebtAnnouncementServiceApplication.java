package com.nse.listingcompliance.lcdebtannouncementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableConfigurationProperties
@EnableFeignClients
@EnableSwagger2
@EnableScheduling
@EnableCaching
@EnableDiscoveryClient
@EnableAsync
@RefreshScope
public class LcDebtAnnouncementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LcDebtAnnouncementServiceApplication.class, args);
    }

}

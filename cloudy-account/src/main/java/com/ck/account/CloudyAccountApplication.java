package com.ck.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.ck.*"})
@ComponentScan(basePackages = {"com.ck.*"})
@EntityScan("com.ck.*")
public class CloudyAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudyAccountApplication.class, args);
    }

}

package com.lukeware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Diego Morais
 */
@SpringBootApplication
@EnableMongoRepositories
@EnableFeignClients
public class Application {
  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }
}

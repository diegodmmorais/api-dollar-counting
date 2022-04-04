package com.lukeware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Diego Morais
 */
@EnableAsync
@EnableScheduling
@EnableFeignClients
@SpringBootApplication
public class Application {
  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }
}

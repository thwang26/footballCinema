package com.zerobase.footballcinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FootballCinemaApplication {

  public static void main(String[] args) {
    SpringApplication.run(FootballCinemaApplication.class, args);
  }

}

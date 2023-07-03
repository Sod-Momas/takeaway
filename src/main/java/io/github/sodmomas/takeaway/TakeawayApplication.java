package io.github.sodmomas.takeaway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

//
@MapperScan
@SpringBootApplication
@ComponentScan("io.github.sodmomas.takeaway")
public class TakeawayApplication {

    public static void main(String[] args) {
        SpringApplication.run(TakeawayApplication.class, args);
    }

    @Bean
    ApplicationRunner afterStart(Environment env) {
        return arg -> {
            final Integer port = env.getProperty("server.port", int.class, 8080);
            System.out.printf("手动进页面: http://localhost:%d/%n", port);
        };
    }

}

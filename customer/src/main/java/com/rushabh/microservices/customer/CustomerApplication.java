package com.rushabh.microservices.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(scanBasePackages = {
        "com.rushabh.microservices.customer",
        "com.rushabh.microservices.amqp.message",
        "com.rushabh.microservices.amqp.config"

})
@EnableFeignClients(basePackages = {
        "com.rushabh.microservices.clients.fraud",
        "com.rushabh.microservices.clients.notification"
})
@PropertySources({@PropertySource("classpath:clients-${spring.profiles.active}.properties")})
public class
CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

}

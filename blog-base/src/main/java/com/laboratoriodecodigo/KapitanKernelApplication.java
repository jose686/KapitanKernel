package com.laboratoriodecodigo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication(scanBasePackages = "com.laboratoriodecodigo")
@EntityScan({

        "com.laboratoriodecodigo.modelo",

})
@EnableScheduling
public class KapitanKernelApplication {

    public static void main(String[] args) {
        SpringApplication.run(KapitanKernelApplication.class, args);


    }
}

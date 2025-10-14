package com.laboratoriodecodigo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication(scanBasePackages = "com.laboratoriodecodigo")
@EntityScan({

        "com.laboratoriodecodigo.modelo",

})
public class KapitanKernelApplication {

	public static void main(String[] args) {
		SpringApplication.run(KapitanKernelApplication.class, args);
	}

}

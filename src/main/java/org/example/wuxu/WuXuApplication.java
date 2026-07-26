package org.example.wuxu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.example.wuxu", "config", "common", "module"})
public class WuXuApplication {

    public static void main(String[] args) {
        SpringApplication.run(WuXuApplication.class, args);
    }

}

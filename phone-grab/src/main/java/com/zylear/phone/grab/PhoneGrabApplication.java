package com.zylear.phone.grab;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.zylear.phone.grab.*")
public class PhoneGrabApplication {
    public static void main(String[] args) {
        SpringApplication.run(PhoneGrabApplication.class, args);
    }

}

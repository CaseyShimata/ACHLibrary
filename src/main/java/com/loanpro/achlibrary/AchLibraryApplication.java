package com.example.achlibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AchLibraryApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AchLibraryApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(AchLibraryApplication.class, args);
    }

    @GetMapping("/validateACH")
    public String validateACH() {
        String fileSummary = "Here is the file summary.";
        return fileSummary;
    }
}

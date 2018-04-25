package com.github.hasanmirzae.module.composer;

import com.github.hasanmirzae.module.composer.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class Application  {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }



    @Component
    public class CommandLineAppStartupRunner implements CommandLineRunner {

        @Autowired
        private ModuleRepository moduleRepository;

        @Override
        public void run(String...args) throws Exception {
            System.out.println("Clearing repository ...");
            moduleRepository.deleteAll();
        }
    }
}

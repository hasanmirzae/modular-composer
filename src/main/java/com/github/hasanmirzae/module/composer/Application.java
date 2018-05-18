package com.github.hasanmirzae.module.composer;

import com.github.hasanmirzae.module.composer.repository.ModelTypeRepository;
import com.github.hasanmirzae.module.composer.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Application  {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }



//    @Component
//    public class CommandLineAppStartupRunner implements CommandLineRunner {
//
//        @Autowired
//        private ModuleRepository moduleRepository;
//        @Autowired
//        private ModelTypeRepository modelTypeRepository;
//
//        @Override
//        public void run(String...args) throws Exception {
//            System.out.println("Clearing repository ...");
//            moduleRepository.deleteAll();
//            modelTypeRepository.deleteAll();
//        }
//    }
}

package com.github.hasanmirzae.module.composer.controller;

import com.github.hasanmirzae.module.composer.model.ModuleData;
import com.github.hasanmirzae.module.composer.service.ModuleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private ModuleService moduleService;

    public MainController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @GetMapping(value = "/module/{uuid}")
    public ModuleData getModuleData(@PathVariable String uuid){
        return moduleService.getModuleData(uuid);
    }

}

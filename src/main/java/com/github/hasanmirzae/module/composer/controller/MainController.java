package com.github.hasanmirzae.module.composer.controller;

import com.github.hasanmirzae.module.composer.model.ModuleData;
import com.github.hasanmirzae.module.composer.service.ModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class MainController {

    private ModuleService moduleService;

    public MainController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @GetMapping(value = "module/{uuid}")
    public ModuleData getModuleData(@PathVariable String uuid){
        return moduleService.getModuleData(uuid);
    }

    @PostMapping(value = "module/new")
    public ModuleData initNewModule(){
        return moduleService.initNewModule();
    }

}

package com.github.hasanmirzae.module.composer.controller;

import com.github.hasanmirzae.module.composer.model.*;
import com.github.hasanmirzae.module.composer.repository.ModuleRepository;
import com.github.hasanmirzae.module.composer.service.ModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
public class MainController {

    private ModuleService moduleService;
    private ModuleRepository moduleRepository;

    public MainController(ModuleService moduleService, ModuleRepository moduleRepository) {
        this.moduleService = moduleService;
        this.moduleRepository = moduleRepository;
    }

    @GetMapping(value = "modules/{uuid}")
    public ModuleData getModuleData(@PathVariable String uuid){

        return moduleService.getModuleData(uuid);
    }

    @PostMapping(value = "modules/new")
    public ModuleData initNewModule(@RequestBody ModuleDescription moduleDescription){
        return moduleService.initNewModule(moduleDescription);
    }

    @GetMapping("modules")
    public List<Node> getModules(){
        return moduleRepository.findAll().stream()
                .map(m -> new Node(m))
                .collect(Collectors.toList());
    }

    @PutMapping(value = "modules/add/{uuid}")
    public void insertModule(@PathVariable String uuid){
        moduleService.insertModule(uuid);
    }


    @PostMapping("models")
    public ModelType initNewModule(@RequestBody ModelType modelType){
        return moduleService.createNewType(modelType);
    }

    @GetMapping("models")
    public List<ModelType> getModelTypes(){
        return moduleService.getModelTypes();
    }

    @PostMapping("modules/save")
    public void saveModule(){
        moduleService.saveModule();
    }


    @PostMapping("modules/links")
    public void saveModule(@RequestBody Link link){
        moduleService.addLink(link);
    }

}

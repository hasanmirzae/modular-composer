package com.github.hasanmirzae.module.composer.controller;

import com.github.hasanmirzae.module.composer.model.*;
import com.github.hasanmirzae.module.composer.repository.ModuleRepository;
import com.github.hasanmirzae.module.composer.service.ModuleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @PostMapping(value = "modules")
    public ModuleData createNewModule(@RequestBody ModuleDescription moduleDescription){
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
    public ActionResponse saveModule(@RequestBody ModuleDescription module) throws IOException {
        return new ActionResponse(moduleService.saveModule(module));
    }


    @PostMapping("modules/links")
    public void createLink(@RequestBody Link link){
        moduleService.addLink(link);
    }


    @PostMapping("modules/compose")
    public void compile() throws IOException {
        moduleService.compose();
    }

    @PostMapping("modules/entry")
    public void setEntryModule(@RequestBody Node node) throws IOException {
        moduleService.setEntryModule(node);
    }


    @PostMapping("modules/output")
    public void setOutputModule(@RequestBody Node node) throws IOException {
        moduleService.setOutputModule(node);
    }



}

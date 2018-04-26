package com.github.hasanmirzae.module.composer.service;

import com.github.hasanmirzae.module.Configuration;
import com.github.hasanmirzae.module.composer.model.*;
import com.github.hasanmirzae.module.composer.repository.ModuleRepository;
import com.github.hasanmirzae.module.composer.utils.ModuleUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sun.security.krb5.internal.crypto.Des;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@Scope("singleton")
public class ModuleManager {

    private final ModuleRepository moduleRepository;
//    private Descriptor descriptor;
    private ModuleData moduleData;

    public ModuleManager(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

//    public Descriptor getDescriptor(){
//        return this.descriptor;
//    }


    public void init(ModuleDescription moduleDescription) {
        this.moduleData = new ModuleData(moduleDescription, Collections.emptyList());
    }

    public ModuleData getModuleData() {
        return moduleData;
    }

    public void setModuleData(ModuleData moduleData){
        this.moduleData = moduleData;
    }

    public void addModule(ModuleDescription moduleDescription){
        moduleData.getNodes().add(moduleDescription);
    }

    public void save() {
        moduleRepository.save(this.moduleData);
    }

    public void addLink(Link link) {
        moduleData.getLinks().add(link);
    }

}

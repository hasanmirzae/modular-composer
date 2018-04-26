package com.github.hasanmirzae.module.composer.service;

import com.github.hasanmirzae.module.Configuration;
import com.github.hasanmirzae.module.composer.model.Connection;
import com.github.hasanmirzae.module.composer.model.Descriptor;
import com.github.hasanmirzae.module.composer.model.ModuleDescription;
import com.github.hasanmirzae.module.composer.repository.ModuleRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sun.security.krb5.internal.crypto.Des;

import java.util.UUID;

@Component
@Scope("singleton")
public class ModuleManager {

    private final ModuleRepository moduleRepository;
    private Descriptor descriptor;

    public ModuleManager(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public Descriptor getDescriptor(){
        return this.descriptor;
    }

    public Descriptor initNewInstance(ModuleDescription moduleDescription){
        descriptor = new Descriptor(moduleRepository.save(moduleDescription));
        return descriptor;
    }


}

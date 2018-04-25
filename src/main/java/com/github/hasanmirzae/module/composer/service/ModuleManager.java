package com.github.hasanmirzae.module.composer.service;

import com.github.hasanmirzae.module.Configuration;
import com.github.hasanmirzae.module.composer.model.Connection;
import com.github.hasanmirzae.module.composer.model.Descriptor;
import com.github.hasanmirzae.module.composer.model.ModuleDescription;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sun.security.krb5.internal.crypto.Des;

import java.util.UUID;

@Component
@Scope("singleton")
public class ModuleManager {

    private Descriptor descriptor;


    public Descriptor getDescriptor(){
        return this.descriptor;
    }

    public Descriptor initNewInstance(ModuleDescription moduleDescription){
        descriptor = new Descriptor(moduleDescription);
        return descriptor;

    }
}

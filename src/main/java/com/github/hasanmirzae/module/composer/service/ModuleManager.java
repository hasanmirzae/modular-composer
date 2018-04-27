package com.github.hasanmirzae.module.composer.service;

import com.github.hasanmirzae.module.Configuration;
import com.github.hasanmirzae.module.composer.ModuleComposer;
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
    private ModuleData moduleData;
    private ModuleDescription entryModule;
    private ModuleDescription outputModule;

    public ModuleManager(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }


    public void init(ModuleDescription moduleDescription) {
        this.moduleData = new ModuleData(moduleDescription, Collections.emptyList());
    }

    public ModuleData getModuleData() {
        return moduleData;
    }

    public void setModuleData(ModuleData moduleData) {
        this.moduleData = moduleData;
    }

    public void addModule(ModuleDescription moduleDescription) {
        moduleData.getNodes().add(setModuleIndex(moduleDescription));
    }

    private ModuleDescription setModuleIndex(ModuleDescription moduleDescription) {
        moduleDescription.setIndex((int) this.moduleData.getNodes().stream().filter(m -> m.getUuid()
                                                                                          .equals(moduleDescription
                                                                                                  .getUuid()))
                                                        .count());
        return moduleDescription;
    }

    public void save() {
        moduleRepository.save(this.moduleData);
    }

    public void addLink(Link link) {
        moduleData.getLinks().add(new Connection(findNode(link.getSource()),findNode(link.getTarget())));
    }

    public ModuleDescription getEntryModule() {
        return entryModule;
    }

    public void setEntryModule(String uuid) {
        this.entryModule = findNode(uuid);
    }

    public ModuleDescription getOutputModule() {
        return outputModule;
    }

    public void setOutputModule(String uuid) {
        this.outputModule = findNode(uuid);
    }

    public Descriptor generateDescriptor() {
        Descriptor descriptor = new Descriptor(moduleData);
        descriptor.addConnections(moduleData.getLinks());
        descriptor.addModules(moduleData.getNodes());
        descriptor.setEntryModule(entryModule);
        descriptor.setOutputModule(outputModule);
        return descriptor;
    }

    //    private Connection linkToConnection(Link link) {
    //        return new Connection(findNode(link.getSource()),findNode(link.getTarget()));
    //    }

    private ModuleDescription findNode(String uuid) {
        return moduleData.getNodes().stream().filter(node -> node.getUuid().equals(uuid))
                         .findFirst().get();
    }

    private ModuleDescription findNode(Node description) {
        return moduleData.getNodes().stream()
                         .filter(node -> node.getUuid().equals(description.getUuid())
                                 && node.getIndex() == description.getIndex()).findFirst().get();
    }
}

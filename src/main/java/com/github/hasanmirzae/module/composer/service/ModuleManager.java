package com.github.hasanmirzae.module.composer.service;

import com.github.hasanmirzae.module.composer.model.*;
import com.github.hasanmirzae.module.composer.repository.ModuleRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.UUID;

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
        moduleDescription.setUuid(UUID.randomUUID().toString());
        this.moduleData = new ModuleData(moduleDescription, Collections.emptyList());
        this.entryModule = null;
        this.outputModule = null;
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
                .equals(moduleDescription.getUuid()))
                .count());
        return moduleDescription;
    }

    public void save() {
        moduleRepository.save(this.moduleData);
    }

    public void addLink(Link link) {
        moduleData.getLinks().add(new Connection(findNode(link.getSource()), findNode(link.getTarget())));
    }

    public ModuleDescription getEntryModule() {
        return entryModule;
    }

    public void setEntryModule(Node node) {
        this.entryModule = findNode(node);
    }

    public ModuleDescription getOutputModule() {
        return outputModule;
    }

    public void setOutputModule(Node node) {
        this.outputModule = findNode(node);
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

    private ModuleDescription findNode(Node node) {
        return moduleData.getNodes().stream()
                .filter(m -> m.getUuid().equals(node.getUuid()) && m.getIndex() == node.getIndex()).findFirst().get();
    }
}

package com.github.hasanmirzae.module.composer.service;

import com.github.hasanmirzae.module.composer.model.*;
import com.github.hasanmirzae.module.composer.repository.ModelTypeRepository;
import com.github.hasanmirzae.module.composer.repository.ModuleRepository;
import com.github.hasanmirzae.module.composer.utils.ModuleUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ModuleService {

    private ModuleRepository moduleRepository;
    private ModelTypeRepository modelTypeRepository;
    private ModuleManager moduleManager;

    public ModuleService(ModuleRepository moduleRepository, ModelTypeRepository modelTypeRepository, ModuleManager moduleManager) {
        this.moduleRepository = moduleRepository;
        this.modelTypeRepository = modelTypeRepository;
        this.moduleManager = moduleManager;
    }

    public ModuleData getModuleData(String uuid) {
        Optional<ModuleDescription> descriptor = moduleRepository.findById(uuid);
        if (!descriptor.isPresent())
            ModuleUtils.throwNotFoundModuleException(uuid);

        List<Node> nodes = StreamSupport.stream(moduleRepository.findAllById(getModuleUuids(descriptor.get())).spliterator(), false)
                .map(d -> new Node(d))
                .collect(Collectors.toList());

        ModuleData data = new ModuleData(descriptor.get(), nodes);
        return data;
    }

    private List<String> getModuleUuids(ModuleDescription descriptor) {
        return descriptor
                .getLinks()
                .stream()
                .flatMap(l -> Arrays.asList(l.getSource(), l.getTarget()).stream())
                .collect(Collectors.toList());
    }

    private List<Link> getLinks(List<Connection> connections) {
        return connections.stream()
                .map(con -> new Link(con.getFrom().getUuid(), con.getTo().getUuid()))
                .collect(Collectors.toList());
    }

    private List<Node> getNodes(Descriptor descriptor) {
        return descriptor.getConnections()
                .stream()
                .flatMap(con -> Arrays.asList(new Node(con.getFrom()), new Node(con.getTo())).stream())
                .collect(Collectors.toList());
    }


    public ModuleData initNewModule(ModuleDescription moduleDescription) {
        return new ModuleData(moduleManager.initNewInstance(moduleDescription),Collections.emptyList());
    }

    public void insertModule(String selectedUuid, String targetUuid) {
        Optional<ModuleDescription> toBeInsertedModule = moduleRepository.findById(selectedUuid);
        if (!toBeInsertedModule.isPresent())
            ModuleUtils.throwNotFoundModuleException(selectedUuid);
        Optional<ModuleDescription> targetModule = moduleRepository.findById(targetUuid);
        if (!targetModule.isPresent())
            ModuleUtils.throwNotFoundModuleException(targetUuid);

        moduleManager.getDescriptor()


    }

    public ModelType createNewType(ModelType modelType) {
        return modelTypeRepository.save(modelType);
    }

    public List<ModelType> getModelTypes(){
        return modelTypeRepository.findAll();
    }
}

package com.github.hasanmirzae.module.composer.service;

import com.github.hasanmirzae.module.composer.ModuleComposer;
import com.github.hasanmirzae.module.composer.exception.InvalidDescriptorException;
import com.github.hasanmirzae.module.composer.model.*;
import com.github.hasanmirzae.module.composer.repository.ModelTypeRepository;
import com.github.hasanmirzae.module.composer.repository.ModuleRepository;
import com.github.hasanmirzae.module.composer.utils.ModuleUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        //TODO smelly logic!
        if (!moduleManager.getModuleData().getUuid().equals(uuid))
            throw new RuntimeException("Uuid of the request mismatch with current module in ModuleManager");
        return  moduleManager.getModuleData();
    }

//    private List<String> getModuleUuids(ModuleDescription descriptor) {
//        return descriptor
//                .getLinks()
//                .stream()
//                .flatMap(l -> Arrays.asList(l.getSource(), l.getTarget()).stream())
//                .collect(Collectors.toList());
//    }
//
//    private List<Link> getLinks(List<Connection> connections) {
//        return connections.stream()
//                .map(con -> new Link(con.getSource().getUuid(), con.getTarget().getUuid()))
//                .collect(Collectors.toList());
//    }
//
//    private List<Node> getNodes(Descriptor descriptor) {
//        return descriptor.getConnections()
//                .stream()
//                .flatMap(con -> Arrays.asList(new Node(con.getSource()), new Node(con.getTarget())).stream())
//                .collect(Collectors.toList());
//    }


    public ModuleData initNewModule(ModuleDescription moduleDescription) {
        moduleManager.init(moduleDescription);
        return moduleManager.getModuleData();
    }

    public void insertModule(String uuid) {
        Optional<ModuleDescription> module = moduleRepository.findById(uuid);
        if (!module.isPresent())
            ModuleUtils.throwNotFoundModuleException(uuid);

        moduleManager.addModule(module.get());
    }

    public ModelType createNewType(ModelType modelType) {
        return modelTypeRepository.save(modelType);
    }

    public List<ModelType> getModelTypes(){
        return modelTypeRepository.findAll();
    }

    public String saveModule(ModuleDescription module) throws IOException {
        validateDescriptor(moduleManager.generateDescriptor());
        ModuleComposer composer = new ModuleComposer(moduleManager.generateDescriptor());
        composer.generateProject("target");
        String log = composer.buildProject(Paths.get("target").toAbsolutePath().toString(),moduleManager.getModuleData().getArtifactId());
        moduleManager.save(module);
        return log;
    }

    public void addLink(Link link) {
        moduleManager.addLink(link);
    }

    public void compose() throws IOException {
        validateDescriptor(moduleManager.generateDescriptor());
        ModuleComposer composer = new ModuleComposer(moduleManager.generateDescriptor());
        System.out.println(
            composer.compose()
        );

        composer.generateProject("target");
    }

    private void validateDescriptor(Descriptor descriptor) throws InvalidDescriptorException {
        if (!descriptor.getModules().isEmpty() && (descriptor.getEntryModule() == null || descriptor.getOutputModule() == null))
            throw new InvalidDescriptorException("EntryModule or OutputModule not defined");
    }

    public void setOutputModule(Node node) {
        moduleManager.setOutputModule(node);
    }


    public void setEntryModule(Node node) {
        moduleManager.setEntryModule(node);
    }

}

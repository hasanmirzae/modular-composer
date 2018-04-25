package com.github.hasanmirzae.module.composer.service;

import com.github.hasanmirzae.module.Configuration;
import com.github.hasanmirzae.module.composer.model.*;
import com.github.hasanmirzae.module.composer.repository.ModuleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ModuleService {

    private ModuleRepository moduleRepository;

    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public ModuleData getModuleData(String uuid) {
        Descriptor descriptor = getModuleDescriptor(uuid);
        List<Node> nodes = getNodes(descriptor);
        ModuleData data = new ModuleData(nodes, getLinks(descriptor.getConnections()),"lkhsdfui34r9879","SampleModule","org.edu","sample-mod","org.example","SNAPSHOT-1.0");
        return data;
    }

    private List<Link> getLinks(List<Connection> connections) {
        return connections.stream()
                .map(con -> new Link(con.getFrom().getUuid(),con.getTo().getUuid()))
                .collect(Collectors.toList());
    }

    private List<Node> getNodes(Descriptor descriptor) {
        return descriptor.getConnections()
                .stream()
                .flatMap(con -> Arrays.asList(new Node(con.getFrom()), new Node(con.getTo())).stream())
                .collect(Collectors.toList());
    }

    private Descriptor getModuleDescriptor(String uuid) {
        // generate sample
        Descriptor descriptor = new Descriptor("SampleModule","com.github.hasanmirzae.module.composer");
        Configuration config = new Configuration();
        ModuleDescription entry = new ModuleDescription("uuid-1","SampleModule","org.edu.modules","String","String",true,false);
        entry.setGroupId("org.edu.modules");
        entry.setArtifactId("sample-module");
        entry.setVersion("1.0-SNAPSHOT");
        entry.setConfig("key1:value1,key2;value2");
        ModuleDescription end = new ModuleDescription("uuid-2","SampleModule","org.edu.modules","String","String",false,true);
        end.setGroupId("org.edu.modules");
        end.setArtifactId("sample-module");
        end.setVersion("1.0-SNAPSHOT");
        Connection conn = new Connection(entry,end);
        descriptor.addConnection(conn);
        return descriptor;
    }

    public ModuleData initNewModule() {
        ModuleData data = new ModuleData(Collections.emptyList(),Collections.emptyList(),UUID.randomUUID().toString(),"SampleModule","org.edu","sample-mod","org.example","SNAPSHOT-1.0");
        moduleRepository.save(new ModuleDescription(data));
        return data;
    }

    public void insertModule(String mouleUuid, String targetUuid) {

    }
}

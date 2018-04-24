package com.github.hasanmirzae.module.composer.service;

import com.github.hasanmirzae.module.Configuration;
import com.github.hasanmirzae.module.composer.model.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModuleService {

    public ModuleData getModuleData(String uuid) {
        Descriptor descriptor = getModuleDescriptor(uuid);
        List<ModuleDescription> nodes = getNodes(descriptor);
        ModuleData data = new ModuleData(nodes, getLinks(descriptor.getConnections()));
        return data;
    }

    private List<Link> getLinks(List<Connection> connections) {
        return connections.stream()
                .map(con -> new Link(con.getFrom().getUuid(),con.getTo().getUuid()))
                .collect(Collectors.toList());
    }

    private List<ModuleDescription> getNodes(Descriptor descriptor) {
        return descriptor.getConnections()
                .stream()
                .flatMap(con -> Arrays.asList(con.getFrom(), con.getTo()).stream())
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
        ModuleDescription end = new ModuleDescription("uuid-1","SampleModule","org.edu.modules","String","String",false,true);
        end.setGroupId("org.edu.modules");
        end.setArtifactId("sample-module");
        end.setVersion("1.0-SNAPSHOT");
        Connection conn = new Connection(entry,end);
        descriptor.addConnection(conn);
        return descriptor;
    }
}

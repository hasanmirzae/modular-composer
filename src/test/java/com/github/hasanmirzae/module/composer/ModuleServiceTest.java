package com.github.hasanmirzae.module.composer;

import com.github.hasanmirzae.module.composer.model.ModuleData;
import com.github.hasanmirzae.module.composer.service.ModuleService;
import org.junit.Test;

public class ModuleServiceTest {

    @Test
    public void testGettingGraphData(){
        ModuleService moduleService = new ModuleService();
        ModuleData data = moduleService.getModuleData("");
        System.out.println(data);
    }
}

package com.github.hasanmirzae.module.composer;

import com.github.hasanmirzae.module.composer.model.ModuleData;
import com.github.hasanmirzae.module.composer.service.ModuleService;
import org.junit.Test;

public class ModuleServiceTest {

    @Test
    public void testGettingGraphData(){
        ModuleService moduleService = new ModuleService(null, null, null);
        ModuleData data = moduleService.getModuleData("");
    }
}

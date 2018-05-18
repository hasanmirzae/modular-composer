package com.github.hasanmirzae.module.composer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hasanmirzae.module.composer.model.ModuleData;
import com.github.hasanmirzae.module.composer.repository.ModuleRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ApplictionIT {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ModuleRepository moduleRepository;

    private boolean dbCleared = false;

    @Before
    public void setup(){
        if (!dbCleared){
            // clear database
            moduleRepository.deleteAll();
            System.out.println("DB cleared for test");
            dbCleared = true;
        }
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void test() throws Exception {
        ModuleData moduleData = generateModuleData();

        MvcResult result = mockMvc.perform(post("/api/v1/modules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(moduleToJson(moduleData)))
               .andReturn();
        System.out.println("Result = "+result.getResponse().getContentAsString());
    }

    private String moduleToJson(ModuleData moduleData) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(moduleData);
    }

    private ModuleData generateModuleData() {
        ModuleData module = new ModuleData();
        module.setGroupId("org.example");
        module.setArtifactId("sample-module");
        module.setPackageName("org.example.module");
        module.setVersion("1.0-SNAPSHOT");
        module.setSimpleName("SampleModule");
        return module;
    }
}

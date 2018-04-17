package com.github.hasanmirzae.module.composer;

import com.github.hasanmirzae.module.AbstractModule;

import java.util.function.Function;

public class SampleModule extends AbstractModule<String,String> {
    @Override protected Function<String, String> getLogic() {
        return null;
    }
}

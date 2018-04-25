package com.github.hasanmirzae.module.composer.utils;

import com.github.hasanmirzae.module.composer.exception.ModuleNotFountException;

public class ModuleUtils {

    public static void throwNotFoundModuleException(String uuid){
        throw  new ModuleNotFountException(String.format("Module not found with uuid = %s",uuid));
    }
}

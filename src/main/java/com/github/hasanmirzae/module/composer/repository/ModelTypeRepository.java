package com.github.hasanmirzae.module.composer.repository;

import com.github.hasanmirzae.module.composer.model.ModelType;
import com.github.hasanmirzae.module.composer.model.ModuleDescription;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ModelTypeRepository extends MongoRepository<ModelType, String> {


}

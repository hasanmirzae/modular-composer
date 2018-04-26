package com.github.hasanmirzae.module.composer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "model-type")
//@CompoundIndexes({
//        @CompoundIndex(name = "full_name", def = "{'model-type.simpleName' : 1, 'model-type.packageName': 1}",unique = true)
//})
public class ModelType {

    private String simpleName;
    private String packageName;


    @Id()
    public String getId(){
       return packageName + "." + simpleName;
    }

    public ModelType() {
    }

    public ModelType(String simpleName, String packageName) {
        this.simpleName = simpleName;
        this.packageName = packageName;
    }


    public String getSimpleName() {
        return simpleName;
    }

    public String getPackageName() {
        return packageName;
    }
}

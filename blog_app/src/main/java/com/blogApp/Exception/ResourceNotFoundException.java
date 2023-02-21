package com.blogApp.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String fieldName;
    long fieldValue;

    public ResourceNotFoundException( String resourceName, String fieldName, long fieldValue) {
        super("not found with this id");
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}

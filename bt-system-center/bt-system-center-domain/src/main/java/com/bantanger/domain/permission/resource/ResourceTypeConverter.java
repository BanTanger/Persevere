package com.bantanger.domain.permission.resource;

/**
 * @author chensongmin
 * @description
 * @date 2025/2/6
 */
import jakarta.persistence.AttributeConverter;

public class ResourceTypeConverter implements AttributeConverter<ResourceType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ResourceType resourceType) {
        return resourceType.getCode();
    }

    @Override
    public ResourceType convertToEntityAttribute(Integer code) {
        return ResourceType.of(code).orElse(null);
    }
}

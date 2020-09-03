
/*
 * Camel ApiMethod Enumeration generated by camel-api-component-maven-plugin
 */
package org.apache.camel.component.box.internal;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.camel.component.box.api.BoxSearchManager;

import org.apache.camel.support.component.ApiMethod;
import org.apache.camel.support.component.ApiMethodArg;
import org.apache.camel.support.component.ApiMethodImpl;

import static org.apache.camel.support.component.ApiMethodArg.arg;

/**
 * Camel {@link ApiMethod} Enumeration for org.apache.camel.component.box.api.BoxSearchManager
 */
public enum BoxSearchManagerApiMethod implements ApiMethod {

    SEARCHFOLDER(
        java.util.Collection.class,
        "searchFolder",
        arg("folderId", String.class),
        arg("query", String.class));

    private static final BoxSearchManagerApiMethod[] CACHED_ENUM_VALUES = values();

    private final ApiMethod apiMethod;

    private BoxSearchManagerApiMethod(Class<?> resultType, String name, ApiMethodArg... args) {
        this.apiMethod = new ApiMethodImpl(BoxSearchManager.class, resultType, name, args);
    }

    @Override
    public String getName() { return apiMethod.getName(); }

    public static BoxSearchManagerApiMethod fromValue(String value) throws IllegalArgumentException {
        for (int i = 0; i < CACHED_ENUM_VALUES.length; i++) {
            if (CACHED_ENUM_VALUES[i].toString().equalsIgnoreCase(value)) {
                return CACHED_ENUM_VALUES[i];
            }
        }
        throw new IllegalArgumentException("Invalid value " + value);
    }

    @Override
    public Class<?> getResultType() { return apiMethod.getResultType(); }

    @Override
    public List<String> getArgNames() { return apiMethod.getArgNames(); }

    @Override
    public List<Class<?>> getArgTypes() { return apiMethod.getArgTypes(); }

    @Override
    public Method getMethod() { return apiMethod.getMethod(); }
}

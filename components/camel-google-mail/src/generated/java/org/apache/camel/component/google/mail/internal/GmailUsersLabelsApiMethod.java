
/*
 * Camel ApiMethod Enumeration generated by camel-api-component-maven-plugin
 */
package org.apache.camel.component.google.mail.internal;

import java.lang.reflect.Method;
import java.util.List;

import com.google.api.services.gmail.Gmail.Users.Labels;

import org.apache.camel.support.component.ApiMethod;
import org.apache.camel.support.component.ApiMethodArg;
import org.apache.camel.support.component.ApiMethodImpl;

import static org.apache.camel.support.component.ApiMethodArg.arg;

/**
 * Camel {@link ApiMethod} Enumeration for com.google.api.services.gmail.Gmail$Users$Labels
 */
public enum GmailUsersLabelsApiMethod implements ApiMethod {

    CREATE(
        com.google.api.services.gmail.Gmail.Users.Labels.Create.class,
        "create",
        arg("userId", String.class),
        arg("content", com.google.api.services.gmail.model.Label.class)),

    DELETE(
        com.google.api.services.gmail.Gmail.Users.Labels.Delete.class,
        "delete",
        arg("userId", String.class),
        arg("id", String.class)),

    GET(
        com.google.api.services.gmail.Gmail.Users.Labels.Get.class,
        "get",
        arg("userId", String.class),
        arg("id", String.class)),

    LIST(
        com.google.api.services.gmail.Gmail.Users.Labels.List.class,
        "list",
        arg("userId", String.class)),

    PATCH(
        com.google.api.services.gmail.Gmail.Users.Labels.Patch.class,
        "patch",
        arg("userId", String.class),
        arg("id", String.class),
        arg("content", com.google.api.services.gmail.model.Label.class)),

    UPDATE(
        com.google.api.services.gmail.Gmail.Users.Labels.Update.class,
        "update",
        arg("userId", String.class),
        arg("id", String.class),
        arg("content", com.google.api.services.gmail.model.Label.class));

    private static final GmailUsersLabelsApiMethod[] CACHED_ENUM_VALUES = values();

    private final ApiMethod apiMethod;

    private GmailUsersLabelsApiMethod(Class<?> resultType, String name, ApiMethodArg... args) {
        this.apiMethod = new ApiMethodImpl(Labels.class, resultType, name, args);
    }

    @Override
    public String getName() { return apiMethod.getName(); }

    public static GmailUsersLabelsApiMethod fromValue(String value) throws IllegalArgumentException {
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

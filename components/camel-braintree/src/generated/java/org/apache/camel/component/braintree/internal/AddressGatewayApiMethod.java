
/*
 * Camel ApiMethod Enumeration generated by camel-api-component-maven-plugin
 */
package org.apache.camel.component.braintree.internal;

import java.lang.reflect.Method;
import java.util.List;

import com.braintreegateway.AddressGateway;

import org.apache.camel.support.component.ApiMethod;
import org.apache.camel.support.component.ApiMethodArg;
import org.apache.camel.support.component.ApiMethodImpl;

import static org.apache.camel.support.component.ApiMethodArg.arg;

/**
 * Camel {@link ApiMethod} Enumeration for com.braintreegateway.AddressGateway
 */
public enum AddressGatewayApiMethod implements ApiMethod {

    CREATE(
        com.braintreegateway.Result.class,
        "create",
        arg("customerId", String.class),
        arg("request", com.braintreegateway.AddressRequest.class)),

    DELETE(
        com.braintreegateway.Result.class,
        "delete",
        arg("customerId", String.class),
        arg("id", String.class)),

    FIND(
        com.braintreegateway.Address.class,
        "find",
        arg("customerId", String.class),
        arg("id", String.class)),

    UPDATE(
        com.braintreegateway.Result.class,
        "update",
        arg("customerId", String.class),
        arg("id", String.class),
        arg("request", com.braintreegateway.AddressRequest.class));

    private static final AddressGatewayApiMethod[] CACHED_ENUM_VALUES = values();

    private final ApiMethod apiMethod;

    private AddressGatewayApiMethod(Class<?> resultType, String name, ApiMethodArg... args) {
        this.apiMethod = new ApiMethodImpl(AddressGateway.class, resultType, name, args);
    }

    @Override
    public String getName() { return apiMethod.getName(); }

    public static AddressGatewayApiMethod fromValue(String value) throws IllegalArgumentException {
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

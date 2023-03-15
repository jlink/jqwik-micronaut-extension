package net.jqwik.micronaut.hook;

import io.micronaut.context.annotation.Property;
import net.jqwik.api.lifecycle.AroundPropertyHook;
import net.jqwik.api.lifecycle.PropertyExecutionResult;
import net.jqwik.api.lifecycle.PropertyExecutor;
import net.jqwik.api.lifecycle.PropertyLifecycleContext;
import net.jqwik.micronaut.extension.JqwikMicronautExtension;

public class AroundPropertyMicronaut extends JqwikMicronautExtension implements AroundPropertyHook {
    @Override
    public PropertyExecutionResult aroundProperty(final PropertyLifecycleContext context,
                                                  final PropertyExecutor property) {
        EXTENSION_STORE.get()
                .beforeEach(
                        context,
                        context.testInstance(),
                        context.targetMethod(),
                        context.findAnnotationsInContainer(Property.class)
                );
        return property.execute();
    }
}

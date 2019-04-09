package agorapulse.integration;

import io.micronaut.function.FunctionBean;
import java.util.function.Supplier;

@FunctionBean("agorapulse-integration")
public class AgorapulseIntegrationFunction implements Supplier<String> {

    @Override
    public String get() {
        return "agorapulse-integration";
    }
}

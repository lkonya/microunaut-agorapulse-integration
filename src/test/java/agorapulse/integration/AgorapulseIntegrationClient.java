package agorapulse.integration;

import io.micronaut.function.client.FunctionClient;
import io.reactivex.Single;

import javax.inject.Named;

@FunctionClient
public interface AgorapulseIntegrationClient {

    @Named("agorapulse-integration")
    Single<String> index();

}

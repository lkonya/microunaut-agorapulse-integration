package agorapulse.integration;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.IDynamoDBMapper;
import io.micronaut.context.ApplicationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.localstack.LocalStackContainer;

import java.time.Instant;

import static org.junit.Assert.assertEquals;

public class AgorapulseIntegrationFunctionTest {

    @Rule
    public LocalStackContainer localstack = new LocalStackContainer().withServices(LocalStackContainer.Service.DYNAMODB);
    public ApplicationContext context;

    @Before
    public void setUp() throws Exception {
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClient
                .builder()
                .withEndpointConfiguration(localstack.getEndpointConfiguration(LocalStackContainer.Service.DYNAMODB))
                .withCredentials(localstack.getDefaultCredentialsProvider())
                .build();

        IDynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);

        context = ApplicationContext.build().build();
        context.registerSingleton(AmazonDynamoDB.class, amazonDynamoDB);
        context.registerSingleton(IDynamoDBMapper.class, mapper);
        context.start();
    }

    @After
    public void tearDown() throws Exception {
        if (context != null) {
            context.close();
        }
    }

    @Test
    public void testFunction() throws Exception {
        Movie movie = new Movie("testTitle", Instant.now());
        MovieRepository movieRepository = context.getBean(MovieRepository.class);
        assertEquals(movieRepository.save(movie), movie);
    }
}

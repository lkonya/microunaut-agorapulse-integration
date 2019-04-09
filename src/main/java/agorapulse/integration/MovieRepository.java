package agorapulse.integration;

import com.agorapulse.micronaut.aws.dynamodb.annotation.Service;

@Service(Movie.class)
public interface MovieRepository {

    Movie save(Movie movie);
}

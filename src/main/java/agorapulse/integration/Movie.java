package agorapulse.integration;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.time.Instant;

@DynamoDBTable(tableName = "movie")
public class Movie {
    private String title;
    private Instant releaseDate;

    public Movie(String title, Instant releaseDate) {
        this.title = title;
        this.releaseDate = releaseDate;
    }

    @DynamoDBRangeKey
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBHashKey
    public Instant getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Instant releaseDate) {
        this.releaseDate = releaseDate;
    }
}

package com.sama.newsfeed.model;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by ksama on 4/2/16.
 */
@PrimaryKeyClass
public class FeedByUserPK implements Serializable{
    @PrimaryKeyColumn(name = "username", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    String username;

    @PrimaryKeyColumn(name = "feedid", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private UUID feedid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UUID getFeedid() {
        return feedid;
    }

    public void setFeedid(UUID feedid) {
        this.feedid = feedid;
    }
}

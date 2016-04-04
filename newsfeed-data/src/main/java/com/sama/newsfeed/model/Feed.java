package com.sama.newsfeed.model;

import com.datastax.driver.core.utils.UUIDs;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

/**
 * Created by ksama on 4/2/16.
 */
@Table(value = "feeds")
public class Feed {

    @PrimaryKey("feedid") private UUID feedid;
    @Column("feedname") private String feedName;

    public Feed(){
        feedid = UUIDs.random();
    }
    public UUID getFeedUUID() {
        return feedid;
    }

    public void setFeedUUID(UUID feedUUID) {
        this.feedid = feedUUID;
    }

    public String getFeedName() {
        return feedName;
    }

    public void setFeedName(String feedName) {
        this.feedName = feedName;
    }
}

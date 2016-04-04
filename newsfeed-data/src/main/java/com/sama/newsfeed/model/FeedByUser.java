package com.sama.newsfeed.model;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;


/**
 * Created by ksama on 4/2/16.
 */
@Table(value = "feedsbyuser")
public class FeedByUser {
    @PrimaryKey
    private FeedByUserPK feedByUserPK;
    @Column("feedname") private String feedName;


    public FeedByUserPK getFeedByUserPK() {
        return feedByUserPK;
    }

    public void setFeedByUserPK(FeedByUserPK feedByUserPK) {
        this.feedByUserPK = feedByUserPK;
    }

    public String getFeedName() {
        return feedName;
    }

    public void setFeedName(String feedName) {
        this.feedName = feedName;
    }
}

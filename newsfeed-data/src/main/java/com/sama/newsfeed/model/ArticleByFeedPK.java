package com.sama.newsfeed.model;

import com.datastax.driver.core.utils.UUIDs;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by ksama on 4/3/16.
 */
@PrimaryKeyClass
public class ArticleByFeedPK implements Serializable{
    @PrimaryKeyColumn(name = "feedid", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID feedid;

    @PrimaryKeyColumn(name = "articleid", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private UUID articleId = UUIDs.timeBased();

    public UUID getFeedid() {
        return feedid;
    }

    public void setFeedid(UUID feedid) {
        this.feedid = feedid;
    }

    public UUID getArticleId() {
        return articleId;
    }

    public void setArticleId(UUID articleId) {
        this.articleId = articleId;
    }
}

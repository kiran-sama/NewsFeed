package com.sama.newsfeed.repository;

import com.sama.newsfeed.model.ArticleByFeed;
import com.sama.newsfeed.model.FeedByUser;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.List;
import java.util.UUID;

/**
 * Created by ksama on 4/3/16.
 */
public interface ArticleByFeedRepository extends CassandraRepository<ArticleByFeed> {
    @Query("select * from articlesbyfeed where feedid = ?0 limit ?1")
    List<ArticleByFeed> getArticlesByFeed(UUID feedid,int limit);

    @Query("select * from articlesbyfeed where feedid = ?0 ")
    List<ArticleByFeed> getArticlesByFeed(UUID feedid);

}

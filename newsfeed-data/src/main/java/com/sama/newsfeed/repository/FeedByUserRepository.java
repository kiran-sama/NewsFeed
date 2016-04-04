package com.sama.newsfeed.repository;

import com.sama.newsfeed.model.FeedByUser;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.List;

/**
 * Created by ksama on 4/2/16.
 */
public interface FeedByUserRepository extends CassandraRepository<FeedByUser> {

    @Query("select * from feedsbyuser where username = ?0 limit ?1")
    List<FeedByUser> getFeedsByUser(String username,int limit);

    @Query("select * from feedsbyuser where username = ?0")
    List<FeedByUser> getFeedsByUser(String username);
}

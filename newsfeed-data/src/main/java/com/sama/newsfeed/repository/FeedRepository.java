package com.sama.newsfeed.repository;

import com.sama.newsfeed.model.Feed;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 * Created by ksama on 4/2/16.
 */
public interface FeedRepository extends CassandraRepository<Feed> {
}

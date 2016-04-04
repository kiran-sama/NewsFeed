package com.sama.newsfeed.repository;

import com.sama.newsfeed.model.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ksama on 4/2/16.
 */
public interface UserRepository extends CassandraRepository<User> {
}

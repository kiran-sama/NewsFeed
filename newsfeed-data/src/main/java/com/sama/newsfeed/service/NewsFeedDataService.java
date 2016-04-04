package com.sama.newsfeed.service;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.Clause;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.sama.newsfeed.model.*;
import com.sama.newsfeed.repository.ArticleByFeedRepository;
import com.sama.newsfeed.repository.FeedByUserRepository;
import com.sama.newsfeed.repository.FeedRepository;
import com.sama.newsfeed.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cassandra.core.ResultSetExtractor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.support.BasicMapId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ksama on 4/2/16.
 */
@Component
public class NewsFeedDataService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    FeedByUserRepository feedByUserRepository;

    @Autowired
    ArticleByFeedRepository articleByFeedRepository;

    @Autowired
    CassandraTemplate cassandraTemplate;

    public Feed addFeed(String feedName){
       Feed feed = new Feed();
        feed.setFeedName(feedName);
        feedRepository.save(feed);
        return feed;

    }

    public Feed getFeed(UUID feedId){
        Feed feed =feedRepository.findOne(BasicMapId.id("feedid", feedId));
        return feed;
    }

    public List<FeedByUser> getFeedsByUser(String username){
        return feedByUserRepository.getFeedsByUser(username);
    }

    public List<FeedByUser> getFeedsByUser(String username, int noOfResults){
        return feedByUserRepository.getFeedsByUser(username, noOfResults);
    }

    public void addFeedToUser(String username, UUID uuid){
        FeedByUser fbu = new FeedByUser();
        FeedByUserPK pk = new FeedByUserPK();
        Feed f = getFeed(uuid);
        pk.setFeedid(uuid);
        pk.setUsername(username);
        fbu.setFeedByUserPK(pk);
        fbu.setFeedName(f.getFeedName());
        feedByUserRepository.save(fbu);
    }

    public ArticleByFeed addArticleToFeed(UUID feedid, String title, String link){
        ArticleByFeed articleByFeed = new ArticleByFeed();
        ArticleByFeedPK articleByFeedPK = new ArticleByFeedPK();
        articleByFeedPK.setFeedid(feedid);
        articleByFeed.setArticleByFeedPK(articleByFeedPK);
        articleByFeed.setTitle(title);
        articleByFeed.setLink(link);
        articleByFeedRepository.save(articleByFeed);
        return articleByFeed;

    }

    public void deleteFeedFromUser(String username, UUID uuid){
        FeedByUserPK fbu = new FeedByUserPK();
        fbu.setUsername(username);
        fbu.setFeedid(uuid);
        cassandraTemplate.deleteById(FeedByUser.class, fbu);
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public User getUser(String username){
        return userRepository.findOne(BasicMapId.id("username",username));
    }

    public List<ArticleByFeed> getArticlesByFeeds(List<UUID> feedIds){
        Select builder = QueryBuilder.select("feedid", "articleid", "title", "link").from("articlesbyfeed").where(QueryBuilder.in("feedid",feedIds)).limit(20);
        return cassandraTemplate.query(builder, new ResultSetExtractor<List<ArticleByFeed>>() {
            public List<ArticleByFeed> extractData(ResultSet resultSet) throws DriverException, DataAccessException {
                List<ArticleByFeed> articleByFeedList = new ArrayList<ArticleByFeed>();
                for(Row row:resultSet.all()){
                    ArticleByFeed temp = new ArticleByFeed();
                    ArticleByFeedPK tempPK = new ArticleByFeedPK();
                    tempPK.setFeedid(row.getUUID(0));
                    tempPK.setArticleId(row.getUUID(1));
                    temp.setArticleByFeedPK(tempPK);
                    temp.setTitle(row.getString(2));
                    temp.setLink(row.getString(3));
                    articleByFeedList.add(temp);

                }
                return articleByFeedList;
            }
        });
    }
}

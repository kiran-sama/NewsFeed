package com.sama.newsfeed.controller;

import com.sama.newsfeed.json.Article;
import com.sama.newsfeed.json.Feed;
import com.sama.newsfeed.json.User;
import com.sama.newsfeed.model.ArticleByFeed;
import com.sama.newsfeed.model.FeedByUser;
import com.sama.newsfeed.service.NewsFeedDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ksama on 4/3/16.
 */
@Service
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class NewsFeedController {

    @Autowired
    NewsFeedDataService newsFeedDataService;

    @Path("user")
    @POST
    public void addUser(User user){
        com.sama.newsfeed.model.User user1 = new com.sama.newsfeed.model.User();
        user1.setUsername(user.getUsername());
        user1.setEmail(user.getEmail());
      newsFeedDataService.addUser(user1);
    }

    @Path("feed")
    @POST
    public Feed addFeed(Feed feed){
        com.sama.newsfeed.model.Feed feed1 = newsFeedDataService.addFeed(feed.getFeedName());
        feed.setUuid(feed1.getFeedUUID().toString());
        return feed;
    }

    @Path("user/{username}/feed/{feedid}/subscribe")
    @PUT
    public void subscribeUserToFeed(@PathParam("username") String username,
                                    @PathParam("feedid") String feedid){
        newsFeedDataService.addFeedToUser(username, UUID.fromString(feedid));
    }

    @Path("user/{username}/feed/{feedid}/unsubscribe")
    @PUT
    public void unsubscribeUserToFeed(@PathParam("username") String username,
                                      @PathParam("feedid") String feedid){
        newsFeedDataService.deleteFeedFromUser(username, UUID.fromString(feedid));
    }

    @Path("feed/{feedid}/articles/list")
    @GET
    public List<Article> getArticlesOfAFeed(@PathParam("feedid") String feedid){
        List<UUID> feeds = new ArrayList<UUID>();
        feeds.add(UUID.fromString(feedid));
        List<ArticleByFeed> articles = newsFeedDataService.getArticlesByFeeds(feeds);
        List<Article> result = new ArrayList<Article>();
        for(ArticleByFeed articleByFeed:articles){
             result.add(new Article(articleByFeed));
        }
        return result;
    }

    @Path("user/{username}/articles/list")
    @GET
    public List<Article> getArticlesForUser(@PathParam("username") String username){
        List<FeedByUser> feeds = newsFeedDataService.getFeedsByUser(username, 20);
        List<UUID> feedids = new ArrayList<UUID>();
        for(FeedByUser feedByUser:feeds){
            feedids.add(feedByUser.getFeedByUserPK().getFeedid());
        }
        List<ArticleByFeed> articles = newsFeedDataService.getArticlesByFeeds(feedids);
        List<Article> result = new ArrayList<Article>();
        for(ArticleByFeed articleByFeed:articles){
            result.add(new Article(articleByFeed));
        }
        return result;
    }

    @Path("feed/{feedid}/article")
    @POST
    public Article addArticleToFeed(@PathParam("feedid") String feedid, Article article){
       ArticleByFeed articleByFeed = newsFeedDataService.addArticleToFeed(UUID.fromString(feedid), article.getTitle(), article.getLink());
        article.setArticleid(articleByFeed.getArticleByFeedPK().getArticleId().toString());
        return article;
    }
}

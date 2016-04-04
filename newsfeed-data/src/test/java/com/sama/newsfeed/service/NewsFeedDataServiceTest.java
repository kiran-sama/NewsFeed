package com.sama.newsfeed.service;

import com.sama.newsfeed.config.NewsFeedConfiguration;
import com.sama.newsfeed.model.ArticleByFeed;
import com.sama.newsfeed.model.Feed;
import com.sama.newsfeed.model.FeedByUser;
import com.sama.newsfeed.model.User;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ksama on 4/2/16.
 */
public class NewsFeedDataServiceTest extends TestCase{
    NewsFeedDataService newsFeedDataService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(NewsFeedConfiguration.class);
        newsFeedDataService = (NewsFeedDataService) ctx.getBean("newsFeedDataService");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testCreateFeed(){
      Feed feed = newsFeedDataService.addFeed("TestFeed");
        assertEquals("TestFeed", newsFeedDataService.getFeed(feed.getFeedUUID()).getFeedName());

    }

    @Test
    public void testFeedByUser(){
        newsFeedDataService.addFeedToUser("sama", UUID.fromString("35d514a0-042b-4990-afb4-9420ede9430f"));
        newsFeedDataService.addFeedToUser("sama", UUID.fromString("7af2a34e-b7f1-463a-bf43-5838d98fd16a"));
        newsFeedDataService.addFeedToUser("sama", UUID.fromString("6b5325f8-1b9f-4e1e-9ba5-1a1bace6440d"));
        newsFeedDataService.addFeedToUser("sama", UUID.fromString("5e857e0e-70c1-48cb-be47-9979d2e9d361"));
    }

    @Test
    public void testUnsubscribeUserFromFeed(){
        newsFeedDataService.deleteFeedFromUser("sama", UUID.fromString("35d514a0-042b-4990-afb4-9420ede9430f"));
    }

    @Test
    public void testFeedsOfAUser(){
        List<FeedByUser> feeds = newsFeedDataService.getFeedsByUser("sama");
        System.out.println(feeds.size());
    }

    @Test
    public void testUserService(){
        User user = new User("sama");
        user.setEmail("samanitw@gmail.com");
        newsFeedDataService.addUser(user);
        User sama = newsFeedDataService.getUser("sama");
        assertEquals(user.toString(), sama.toString());
    }

    @Test
    public void testAddArticlesToFeed(){
        newsFeedDataService.addArticleToFeed(UUID.fromString("7af2a34e-b7f1-463a-bf43-5838d98fd16a"),"Title1","Link1");
        newsFeedDataService.addArticleToFeed(UUID.fromString("7af2a34e-b7f1-463a-bf43-5838d98fd16a"),"Title2","Link2");
        newsFeedDataService.addArticleToFeed(UUID.fromString("7af2a34e-b7f1-463a-bf43-5838d98fd16a"), "Title3", "Link3");
        newsFeedDataService.addArticleToFeed(UUID.fromString("7af2a34e-b7f1-463a-bf43-5838d98fd16a"), "Title4", "Link4");
        newsFeedDataService.addArticleToFeed(UUID.fromString("7af2a34e-b7f1-463a-bf43-5838d98fd16a"), "Title5", "Link5");

        newsFeedDataService.addArticleToFeed(UUID.fromString("6b5325f8-1b9f-4e1e-9ba5-1a1bace6440d"),"Title1","Link1");
        newsFeedDataService.addArticleToFeed(UUID.fromString("6b5325f8-1b9f-4e1e-9ba5-1a1bace6440d"),"Title2","Link2");
        newsFeedDataService.addArticleToFeed(UUID.fromString("6b5325f8-1b9f-4e1e-9ba5-1a1bace6440d"), "Title3", "Link3");
        newsFeedDataService.addArticleToFeed(UUID.fromString("6b5325f8-1b9f-4e1e-9ba5-1a1bace6440d"), "Title4", "Link4");
        newsFeedDataService.addArticleToFeed(UUID.fromString("6b5325f8-1b9f-4e1e-9ba5-1a1bace6440d"), "Title5", "Link5");
    }

    @Test
    public void testGetArticlesByFeeds(){
        List<UUID> feedids = new ArrayList<UUID>();
        feedids.add(UUID.fromString("7af2a34e-b7f1-463a-bf43-5838d98fd16a"));
        feedids.add(UUID.fromString("6b5325f8-1b9f-4e1e-9ba5-1a1bace6440d"));
        List<ArticleByFeed> articles = newsFeedDataService.getArticlesByFeeds(feedids);
        System.out.println(articles.size());
    }
}

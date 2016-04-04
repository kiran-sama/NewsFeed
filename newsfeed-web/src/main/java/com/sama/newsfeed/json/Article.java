package com.sama.newsfeed.json;

import com.sama.newsfeed.model.ArticleByFeed;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ksama on 4/3/16.
 */
@XmlRootElement

public class Article {
    String articleid;
    String title;
    String link;

    public Article(){}

    public Article(ArticleByFeed articleByFeed){
        articleid = articleByFeed.getArticleByFeedPK().getArticleId().toString();
        title = articleByFeed.getTitle();
        link = articleByFeed.getLink();
    }

    public String getArticleid() {
        return articleid;
    }

    public void setArticleid(String articleid) {
        this.articleid = articleid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

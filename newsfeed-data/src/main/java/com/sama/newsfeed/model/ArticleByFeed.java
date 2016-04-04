package com.sama.newsfeed.model;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.Table;

/**
 * Created by ksama on 4/2/16.
 */
@Table("articlesbyfeed")
public class ArticleByFeed {
    @PrimaryKey
    ArticleByFeedPK articleByFeedPK;

    @Column("title")
    String title;

    @Column("link")
    String link;

    public ArticleByFeedPK getArticleByFeedPK() {
        return articleByFeedPK;
    }

    public void setArticleByFeedPK(ArticleByFeedPK articleByFeedPK) {
        this.articleByFeedPK = articleByFeedPK;
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

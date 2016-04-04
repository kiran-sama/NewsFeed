# NewsFeed
We	want	to	make	a	feed	reader	system.	We	will	have	3	entities	in	the	system:	Users,	Feeds,	Articles.	It	should	
support	the	following	operations:

1. Subscribe/Unsubscribe	a	User	to	a	Feed
2. Add	Articles	to	a	Feed
3. Get	all	Feeds	a	Subscriber	is	following
4. Get	Articles	from	the	set	of	Feeds	a	Subscriber	is	following

Requirements
1. Write	a	service	with	HTTP	endpoints	that	allow	items	1-4	from	above
2. It	should	handle	multiple	concurrent	clients
3. It	should	persist	data	across	restarts

Prerequisites
1) Install JVM on your machine
2) install maven

How to run the Service
0) run cassandra and create keyspace newsfeed and create table definitions as in cassandra.schema file in source code
1) Do mvn clean install from parent project
2) Then cd newsfeed-web and execute mvn tomcat7:run command
3) This will run the service on localhost:8080
4) Base context path would be localhost:8080/newfeed-web

URLs
http://localhost:8080/newsfeed-web/user/sama/articles/list (lists 20 articles from all the feeds user sama is subscribed to)

http://localhost:8080/newsfeed-web/user/sama/feed/1b00564a-479d-46f7-b784-329f03b00df5/subscribe (subscribes user sama to a feed 1b00564a-479d-46f7-b784-329f03b00df5)

http://localhost:8080/newsfeed-web/user/sama/feed/1b00564a-479d-46f7-b784-329f03b00df5/unsubscribe (unsubscribes user sama to a feed 1b00564a-479d-46f7-b784-329f03b00df5)

http://localhost:8080/newsfeed-web//feed/1b00564a-479d-46f7-b784-329f03b00df5/articles/list (list 20 articles from the given feed)

=========================================================================================================================================

Decisions:
Chosen Cassandra DB for easy horizontal scalability. It should work well for simple schemas like what we have. It has been used in Instagram, Fasholista for similar systems.Another alternative is Redis DB but cassandra is better as its a low cost and effective solution.

Database design:
create keyspace newsfeed with replication = {'class':'SimpleStrategy','replication_factor':1};
create table users(username text PRIMARY KEY,email text);
create table feeds(feedid uuid PRIMARY KEY,feedname text);
create table feedsbyuser(username text,feedid uuid,feedname text,PRIMARY KEY(username,feedid));
create table articlesbyfeed(feedid uuid,articleid timeuuid,title text,link text,PRIMARY KEY(feedid, articleid)) WITH CLUSTERING ORDER BY (articleid DESC);

Denormalized the data so that we store all the feeds of a user along with user. Similarly all the articles of a feed or stored with feed.

Scalabality and performance:
Although we have chosen a strong datastore in cassandra, we could optimize the performance by using a inmemory cache like Redis or memcache. Caching strategey would be to store all certain no of articles feed wise.Cache size is not dependent on User base its only based on no of feeds.We could also identify popular feeds to cache them more effectively. Its essentially a pull model from user perspective getting all the articles from different feeds. One other approach is to make it a push model to push articles to user entity so that reading is faster. We have different option which we can decide based on the size of the data

User Authentication:
We can store userpasswords hashed in datastore and validae user. it can also be integrated with thirdy party systems 


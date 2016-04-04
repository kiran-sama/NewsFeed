# NewsFeed
Feed	API

Problem

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

Decisions:
Chosen Cassandra DB for easy horizontal scalability. It should work well for simple schemas like what we have. It has been used in Instagram, Fasholista for similar systems.Another alternative is Redis DB but cassandra is better as its a low cost and effective solution.

Database design 



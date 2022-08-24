CREATE TABLE news(
	news_id NUMBER PRIMARY KEY,
	title varchar(100),
	writer varchar(20),
	content clob,
	regdate DATE DEFAULT sysdate,
	hit NUMBER DEFAULT 0
);

CREATE TABLE comments(
	comments_id NUMBER PRIMARY KEY,
	detail varchar(1000),
	writedate DATE DEFAULT sysdate,
	author varchar(20),
	news_id NUMBER, --froeign KEY
	CONSTRAINT fk_news_comments FOREIGN KEY (news_id) REFERENCES news(news_id)
);


CREATE SEQUENCE seq_news
INCREMENT BY 1
START WITH 1;

CREATE SEQUENCE seq_comments
INCREMENT BY 1
START WITH 1;

delete from comments;


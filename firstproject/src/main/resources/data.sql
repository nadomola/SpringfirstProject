INSERT INTO article(title,content) VALUES ('가가가가', '1111');
INSERT INTO article(title,content) VALUES ('나나나나', '2222');
INSERT INTO article(title,content) VALUES ('다다다다', '3333');
-- article 테이블에 데이터 추가
INSERT INTO article (title, content) VALUES ('당신의 인생 영화는?','댓글 고');
INSERT INTO article (title, content) VALUES ('당신의 소울 푸드는?', '댓글 고고');
INSERT INTO article (title, content) VALUES ('당신의 취미는?', '댓글 고고고');
-- 4번 게시글의 댓글 추가
INSERT INTO comment (article_id, nickname,body) VALUES (4,'Park','해리포터');
INSERT INTO comment (article_id, nickname,body) VALUES (4,'kim','아이엠샘');
INSERT INTO comment (article_id, nickname,body) VALUES (4,'Choi','비긴어게인');
-- 5번 게시글의 댓글 추가 \
INSERT INTO comment (article_id, nickname,body) VALUES (5,'Park','치킨');
INSERT INTO comment (article_id, nickname,body) VALUES (5,'kim','피자');
INSERT INTO comment (article_id, nickname,body) VALUES (5,'Choi','주먹밥');
-- 6번 게시글의 댓글 추가
INSERT INTO comment (article_id, nickname,body) VALUES (6,'Park','조깅');
INSERT INTO comment (article_id, nickname,body) VALUES (6,'kim','발레');
INSERT INTO comment (article_id, nickname,body) VALUES (6,'Choi','유투브');
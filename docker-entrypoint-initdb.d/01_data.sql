INSERT INTO users(login, password)
VALUES ('admin', '{argon2}$argon2id$v=19$m=4096,t=3,p=1$VD2gbE9s9SvxSU3QnmeO9w$hosiwDgCWLdyZ6xrysnDg9fDE38frM65jxOj58ZkCXs');

INSERT INTO tokens("token", "user")
VALUES ('6NSb+2kcdKF44ut4iBu+dm6YLu6pakWapvxHtxqaPgMr5iRhox/HlhBerAZMILPjwnRtXms+zDfVTLCsao9nuw==', 'admin');


INSERT INTO cards("id", "owner", "number", "balance")
VALUES (1, 'admin', '**** *777', 50000),
       (2, 'vasya', '**** *888', 10000),
       (3, 'petya', '**** *999', 10000);
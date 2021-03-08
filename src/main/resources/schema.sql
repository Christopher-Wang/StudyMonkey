CREATE TABLE user
(
    userId varchar(11) NOT NULL,
    userName varchar(100) NOT NULL,
    userEmail varchar(100) DEFAULT NULL,
    PRIMARY KEY (userId)
);
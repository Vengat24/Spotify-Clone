CREATE DATABASE SPOTIFY;
USE SPOTIFY;
CREATE TABLE USER (
    EMAIL VARCHAR(30),
    USERNAME VARCHAR(20),
    PASSWORD VARCHAR(16),
    DOB DATE,
    GENDER VARCHAR(20),
    Theme INT DEFAULT 1
);
Drop table USER;

INSERT INTO USER VALUES('VENGATSRINI2410@GMAIL.COM','VENGAT','12345','2001-10-24','MALE',1);
SELECT * FROM USER;
select * from user where email = 'VENGATSRINI2410@GMAIL.COM';
SET SQL_SAFE_UPDATES = 0;
DELETE FROM user WHERE username = 'vengat' AND password NOT LIKE '12345';

CREATE TABLE FAVSONG(
	EMAIL VARCHAR(30),
    SONG1 INT DEFAULT 0,
    SONG2 INT DEFAULT 0,
    SONG3 INT DEFAULT 0,
    SONG4 INT DEFAULT 0,
    SONG5 INT DEFAULT 0,
    SONG6 INT DEFAULT 0,
    SONG7 INT DEFAULT 0,
    SONG8 INT DEFAULT 0,
    SONG9 INT DEFAULT 0,
    SONG10 INT DEFAULT 0
);
DROP TABLE FAVSONG;
SELECT * FROM FAVSONG; 
INSERT INTO FAVSONG(EMAIL) VALUES('VENGATSRINI2410@GMAIL.COM');



CREATE TABLE PLAYLIST(
	EMAIL VARCHAR(30),
    PLAYLISTNAME VARCHAR(20),
    SONG1 INT DEFAULT 0,
    SONG2 INT DEFAULT 0,
    SONG3 INT DEFAULT 0,
    SONG4 INT DEFAULT 0,
    SONG5 INT DEFAULT 0,
    SONG6 INT DEFAULT 0,
    SONG7 INT DEFAULT 0,
    SONG8 INT DEFAULT 0,
    SONG9 INT DEFAULT 0,
    SONG10 INT DEFAULT 0
);
Drop table PLAYLIST;
INSERT INTO PLAYLIST(EMAIL,PLAYLISTNAME) VALUES('VENGATSRINI2410@GMAIL.COM','VENGAT');
SELECT * FROM PLAYLIST;
DELETE FROM PLAYLIST WHERE EMAIL = 'VEN@GMAIL.COM';

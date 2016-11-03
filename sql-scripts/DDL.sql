CREATE TABLE AccessRoles
(
    email VARCHAR(254) NOT NULL,
    accessrole VARCHAR(45) DEFAULT 'voterStd' NOT NULL,
    accessroleid INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    CONSTRAINT fk_accessroles_voteremail FOREIGN KEY (email) REFERENCES Voters (email)
);
CREATE INDEX fk_accessroles_voteremail_idx ON AccessRoles (email);
CREATE TABLE Choices
(
    choiceid INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    description TEXT,
    pollid INT(11),
    CONSTRAINT fk_pollid_choices FOREIGN KEY (pollid) REFERENCES Polls (pollid)
);
CREATE INDEX fk_pollid_choices_idx ON Choices (pollid);
CREATE TABLE Polls
(
    pollid INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    title VARCHAR(45) NOT NULL,
    description TEXT,
    pollcode CHAR(8) NOT NULL,
    available TINYINT(1) DEFAULT '0' NOT NULL,
    votingopen TIMESTAMP,
    votingclosed TIMESTAMP,
    creator INT(11),
    winner INT(11) DEFAULT '-1',
    status INT(4) DEFAULT '0' NOT NULL,
    CONSTRAINT fk_voterid_polls FOREIGN KEY (creator) REFERENCES Voters (voterid)
);
CREATE INDEX fk_voterid_polls_idx ON Polls (creator);
CREATE TABLE Voters
(
    voterid INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(45),
    lastname VARCHAR(100),
    email VARCHAR(254) NOT NULL,
    securedby VARCHAR(64)
);
CREATE UNIQUE INDEX email_UNIQUE ON Voters (email);
CREATE INDEX index_email ON Voters (email);
CREATE TABLE VotersPolls
(
    voterid INT(11) NOT NULL,
    pollid INT(11) NOT NULL,
    notify TINYINT(1) DEFAULT '0' NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (pollid, voterid, notify),
    CONSTRAINT fk_voterid_voters FOREIGN KEY (voterid) REFERENCES Voters (voterid),
    CONSTRAINT fk_pollid_polls FOREIGN KEY (pollid) REFERENCES Polls (pollid)
);
CREATE INDEX fk_pollid_polls_idx ON VotersPolls (pollid);
CREATE INDEX fk_voterid_voters_idx ON VotersPolls (voterid);
CREATE TABLE Votes
(
    voteid INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    rank INT(11) NOT NULL,
    votecast TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    choiceid INT(11),
    pollid INT(11),
    voterid INT(11),
    CONSTRAINT fk_choiceid_votes FOREIGN KEY (choiceid) REFERENCES Choices (choiceid),
    CONSTRAINT fk_pollid_votes FOREIGN KEY (pollid) REFERENCES Polls (pollid),
    CONSTRAINT fk_voterid_votes FOREIGN KEY (voterid) REFERENCES Voters (voterid)
);
CREATE INDEX `fk_choice-id_choices_idx` ON Votes (choiceid);
CREATE INDEX `fk_poll-id_choices_idx` ON Votes (pollid);
CREATE INDEX `fk_voter-id_choices_idx` ON Votes (voterid);
CREATE TABLE AuthTokens
(
    Token CHAR(64) NOT NULL,
    TokenId INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    Created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);
CREATE UNIQUE INDEX AuthTokens_TokenId_uindex ON AuthTokens (TokenId);
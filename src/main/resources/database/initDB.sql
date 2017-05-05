DROP DATABASE IF EXISTS startupdb;
CREATE DATABASE IF NOT EXISTS startupdb
  DEFAULT CHARACTER SET utf8;
USE startupdb;

-- user
CREATE TABLE IF NOT EXISTS user (
  id       INT   UNSIGNED       NOT NULL  AUTO_INCREMENT,
  username VARCHAR(300) NOT NULL  DEFAULT '',
  password VARCHAR(300) NOT NULL  DEFAULT '',
  role     TINYINT      NOT NULL  DEFAULT 1,
  locked   BOOLEAN      NOT NULL  DEFAULT FALSE,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

-- startup
CREATE TABLE IF NOT EXISTS startup (
  id              INT UNSIGNED  NOT NULL  AUTO_INCREMENT,
  name            VARCHAR(300)  NOT NULL  DEFAULT '',
  description     VARCHAR(1800) NOT NULL  DEFAULT '',
  min_investment  INT           NOT NULL  DEFAULT 0,
  need_investment INT           NOT NULL  DEFAULT 0,
  user_id         INT UNSIGNED  NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user (id)
)
  ENGINE = InnoDB;

-- investment
CREATE TABLE IF NOT EXISTS investment (
  id         INT UNSIGNED NOT NULL  AUTO_INCREMENT,
  amount     INT          NOT NULL  DEFAULT 0,
  startup_id INT UNSIGNED NOT NULL  DEFAULT 0,
  user_id    INT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user (id),
  FOREIGN KEY (startup_id) REFERENCES startup (id)
)
  ENGINE = InnoDB;






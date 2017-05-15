DROP DATABASE IF EXISTS startupdb;
CREATE DATABASE IF NOT EXISTS startupdb
  DEFAULT CHARACTER SET utf8;
USE startupdb;

-- user
CREATE TABLE IF NOT EXISTS users (
  id       INT UNSIGNED           NOT NULL  AUTO_INCREMENT,
  username VARCHAR(50)           NOT NULL  DEFAULT '',
  password VARCHAR(50)           NOT NULL  DEFAULT '',
  contacts VARCHAR(300)           NOT NULL  DEFAULT '',
  role     ENUM ('ADMIN', 'USER') NOT NULL  DEFAULT 'USER',
  locked   BOOLEAN                NOT NULL  DEFAULT FALSE,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

-- startup
CREATE TABLE IF NOT EXISTS startups (
  id              INT UNSIGNED  NOT NULL  AUTO_INCREMENT,
  name            VARCHAR(300)  NOT NULL  DEFAULT '',
  description     VARCHAR(1800) NOT NULL  DEFAULT '',
  min_investment  INT           NOT NULL  DEFAULT 0,
  need_investment INT           NOT NULL  DEFAULT 0,
  author_id         INT UNSIGNED  NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (author_id) REFERENCES users (id)
)
  ENGINE = InnoDB;

-- investment
CREATE TABLE IF NOT EXISTS investments (
  id         INT UNSIGNED NOT NULL  AUTO_INCREMENT,
  amount     INT          NOT NULL  DEFAULT 0,
  startup_id INT UNSIGNED NOT NULL  DEFAULT 0,
  author_id    INT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (investor_id) REFERENCES users (id),
  FOREIGN KEY (startup_id) REFERENCES startups (id)
)
  ENGINE = InnoDB;






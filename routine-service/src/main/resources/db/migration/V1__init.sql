-- V1__init.sql
-- Schema: health_routine
SET sql_mode = 'STRICT_ALL_TABLES';

CREATE TABLE IF NOT EXISTS users (
  user_id       BIGINT NOT NULL AUTO_INCREMENT,
  email         VARCHAR(100) NOT NULL,
  username      VARCHAR(50)  NOT NULL,
  nickname      VARCHAR(50)  NOT NULL,
  password_hash VARCHAR(255) NOT NULL,
  created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_users PRIMARY KEY (user_id),
  CONSTRAINT uk_users_email    UNIQUE (email),
  CONSTRAINT uk_users_nickname UNIQUE (nickname)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS routines (
  routine_id        BIGINT      NOT NULL AUTO_INCREMENT,
  user_id           BIGINT      NOT NULL,
  routine_date      DATE        NOT NULL,
  sleep_hours       DECIMAL(3,1),
  exercise_type     VARCHAR(30),
  exercise_minutes  INT,
  meals             VARCHAR(1000),
  water_ml          INT,
  note              TEXT,
  created_at        TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at        TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_routines PRIMARY KEY (routine_id),
  CONSTRAINT fk_routines_user FOREIGN KEY (user_id)
    REFERENCES users(user_id)
    ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT uk_routines_user_date UNIQUE (user_id, routine_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX IF NOT EXISTS idx_routines_user_date ON routines (user_id, routine_date);

CREATE TABLE IF NOT EXISTS comments (
  comment_id  BIGINT     NOT NULL AUTO_INCREMENT,
  routine_id  BIGINT     NOT NULL,
  user_id     BIGINT     NOT NULL,
  content     TEXT       NOT NULL,
  created_at  TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at  TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_comments PRIMARY KEY (comment_id),
  CONSTRAINT fk_comments_routine FOREIGN KEY (routine_id)
    REFERENCES routines(routine_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_comments_user    FOREIGN KEY (user_id)
    REFERENCES users(user_id)
    ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX IF NOT EXISTS idx_comments_routine_created ON comments (routine_id, created_at);

CREATE TABLE IF NOT EXISTS likes (
  like_id     BIGINT     NOT NULL AUTO_INCREMENT,
  routine_id  BIGINT     NOT NULL,
  user_id     BIGINT     NOT NULL,
  created_at  TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT pk_likes PRIMARY KEY (like_id),
  CONSTRAINT fk_likes_routine FOREIGN KEY (routine_id)
    REFERENCES routines(routine_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_likes_user    FOREIGN KEY (user_id)
    REFERENCES users(user_id)
    ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT uk_likes_unique UNIQUE (routine_id, user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Seed
INSERT INTO users (email, username, nickname, password_hash)
VALUES
('alice@example.com', 'alice', '앨리스', '$2y$10$DEMO_HASH_ALICE'),
('bob@example.com',   'bob',   '밥',    '$2y$10$DEMO_HASH_BOB')
ON DUPLICATE KEY UPDATE updated_at = CURRENT_TIMESTAMP;

INSERT INTO routines (user_id, routine_date, sleep_hours, exercise_type, exercise_minutes, meals, water_ml, note)
VALUES
(1, DATE_SUB(CURDATE(), INTERVAL 2 DAY), 7.5, 'RUN',  30, '아:샐러드 점:현미 저:닭가슴살', 2000, '야식 참음'),
(1, DATE_SUB(CURDATE(), INTERVAL 1 DAY), 6.0, 'GYM',  45, '아:오트밀 점:샌드 저:스테이크', 1800, '웨이트 위주'),
(1, CURDATE(),                           7.0, 'WALK', 40, '아:베이글 점:비빔밥 저:연어',   2200, '컨디션 양호'),
(2, CURDATE(),                           6.5, 'ETC',  25, '아:토스트 점:샐러드 저:수프',   1500, '가볍게 운동')
ON DUPLICATE KEY UPDATE updated_at = CURRENT_TIMESTAMP;

INSERT INTO comments (routine_id, user_id, content)
VALUES
(1, 2, '루틴 관리 아주 좋네요!'),
(3, 2, '수면시간 유지 굿!')
ON DUPLICATE KEY UPDATE updated_at = CURRENT_TIMESTAMP;

INSERT INTO likes (routine_id, user_id)
VALUES (1, 2),(3, 2)
ON DUPLICATE KEY UPDATE created_at = created_at;
-- V1__init.sql
-- Schema: health_routine
SET sql_mode = 'STRICT_ALL_TABLES';

CREATE TABLE IF NOT EXISTS users (
  user_id       BIGINT NOT NULL AUTO_INCREMENT,
  email         VARCHAR(100) NOT NULL,
  username      VARCHAR(50)  NOT NULL,
  nickname      VARCHAR(50)  NOT NULL,
  password_hash VARCHAR(255) NOT NULL,
  created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_users PRIMARY KEY (user_id),
  CONSTRAINT uk_users_email    UNIQUE (email),
  CONSTRAINT uk_users_nickname UNIQUE (nickname)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS routines (
  routine_id        BIGINT      NOT NULL AUTO_INCREMENT,
  user_id           BIGINT      NOT NULL,
  routine_date      DATE        NOT NULL,
  sleep_hours       DECIMAL(3,1),
  exercise_type     VARCHAR(30),
  exercise_minutes  INT,
  meals             VARCHAR(1000),
  water_ml          INT,
  note              TEXT,
  created_at        TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at        TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_routines PRIMARY KEY (routine_id),
  CONSTRAINT fk_routines_user FOREIGN KEY (user_id)
    REFERENCES users(user_id)
    ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT uk_routines_user_date UNIQUE (user_id, routine_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX IF NOT EXISTS idx_routines_user_date ON routines (user_id, routine_date);

CREATE TABLE IF NOT EXISTS comments (
  comment_id  BIGINT     NOT NULL AUTO_INCREMENT,
  routine_id  BIGINT     NOT NULL,
  user_id     BIGINT     NOT NULL,
  content     TEXT       NOT NULL,
  created_at  TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at  TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_comments PRIMARY KEY (comment_id),
  CONSTRAINT fk_comments_routine FOREIGN KEY (routine_id)
    REFERENCES routines(routine_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_comments_user    FOREIGN KEY (user_id)
    REFERENCES users(user_id)
    ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX IF NOT EXISTS idx_comments_routine_created ON comments (routine_id, created_at);

CREATE TABLE IF NOT EXISTS likes (
  like_id     BIGINT     NOT NULL AUTO_INCREMENT,
  routine_id  BIGINT     NOT NULL,
  user_id     BIGINT     NOT NULL,
  created_at  TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT pk_likes PRIMARY KEY (like_id),
  CONSTRAINT fk_likes_routine FOREIGN KEY (routine_id)
    REFERENCES routines(routine_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_likes_user    FOREIGN KEY (user_id)
    REFERENCES users(user_id)
    ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT uk_likes_unique UNIQUE (routine_id, user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Seed
INSERT INTO users (email, username, nickname, password_hash)
VALUES
('alice@example.com', 'alice', '앨리스', '$2y$10$DEMO_HASH_ALICE'),
('bob@example.com',   'bob',   '밥',    '$2y$10$DEMO_HASH_BOB')
ON DUPLICATE KEY UPDATE updated_at = CURRENT_TIMESTAMP;

INSERT INTO routines (user_id, routine_date, sleep_hours, exercise_type, exercise_minutes, meals, water_ml, note)
VALUES
(1, DATE_SUB(CURDATE(), INTERVAL 2 DAY), 7.5, 'RUN',  30, '아:샐러드 점:현미 저:닭가슴살', 2000, '야식 참음'),
(1, DATE_SUB(CURDATE(), INTERVAL 1 DAY), 6.0, 'GYM',  45, '아:오트밀 점:샌드 저:스테이크', 1800, '웨이트 위주'),
(1, CURDATE(),                           7.0, 'WALK', 40, '아:베이글 점:비빔밥 저:연어',   2200, '컨디션 양호'),
(2, CURDATE(),                           6.5, 'ETC',  25, '아:토스트 점:샐러드 저:수프',   1500, '가볍게 운동')
ON DUPLICATE KEY UPDATE updated_at = CURRENT_TIMESTAMP;

INSERT INTO comments (routine_id, user_id, content)
VALUES
(1, 2, '루틴 관리 아주 좋네요!'),
(3, 2, '수면시간 유지 굿!')
ON DUPLICATE KEY UPDATE updated_at = CURRENT_TIMESTAMP;

INSERT INTO likes (routine_id, user_id)
VALUES (1, 2),(3, 2)
ON DUPLICATE KEY UPDATE created_at = created_at;

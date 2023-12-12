CREATE TABLE users
(
    id         VARCHAR PRIMARY KEY NOT NULL,
    created_at TIMESTAMP           NOT NULL,
    version    INT                 NOT NULL,
    name       VARCHAR             NOT NULL,
    email      VARCHAR             NOT NULL,
    password   VARCHAR             NOT NULL
);

CREATE UNIQUE INDEX user_email_unique ON users(email);

CREATE TABLE quotes
(
    id          VARCHAR PRIMARY KEY NOT NULL,
    created_at  TIMESTAMP           NOT NULL,
    modified_at TIMESTAMP,
    version     INT                 NOT NULL,
    content     VARCHAR             NOT NULL,
    author_id   VARCHAR             NOT NULL,
    FOREIGN KEY (author_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE votes
(
    id         VARCHAR PRIMARY KEY NOT NULL,
    created_at TIMESTAMP           NOT NULL,
    version    INT                 NOT NULL,
    user_id    VARCHAR             NOT NULL,
    quote_id   VARCHAR             NOT NULL,
    vote       INT                 NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (quote_id) REFERENCES quotes (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX user_vote_unique ON votes(quote_id, user_id);
CREATE TABLE users
(
    "login"    TEXT PRIMARY KEY,
    "password" TEXT NOT NULL
);

CREATE TABLE tokens
(
    "token"   TEXT PRIMARY KEY,
    "user"    TEXT        NOT NULL REFERENCES users,
    "created" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cards
(
    "id"      BIGSERIAL PRIMARY KEY,
    "owner"   TEXT    NOT NULL,
    "number"  TEXT    NOT NULL,
    "balance" BIGINT  NOT NULL DEFAULT 0,
    "active" BOOL DEFAULT TRUE
);
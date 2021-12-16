CREATE TABLE bookmark (
    id INTEGER PRIMARY KEY,
    read INTEGER DEFAULT 0,
    comment TEXT
);

CREATE TABLE book (
    id INTEGER PRIMARY KEY,
    bookmark_id INTEGER,
    author TEXT,
    title TEXT,
    isbn TEXT,
    FOREIGN KEY(bookmark_id) REFERENCES bookmark(id)
);

CREATE TABLE video (
    id INTEGER PRIMARY KEY,
    bookmark_id INTEGER,
    title TEXT,
    url TEXT,
    comment TEXT,
    FOREIGN KEY(bookmark_id) REFERENCES bookmark(id)
);

CREATE TABLE blog (
    id INTEGER PRIMARY KEY,
    bookmark_id INTEGER,
    title TEXT,
    author TEXT,
    url TEXT,
    FOREIGN KEY(bookmark_id) REFERENCES bookmark(id)
);

CREATE TABLE podcast (
    id INTEGER PRIMARY KEY,
    bookmark_id INTEGER,
    name TEXT,
    title TEXT,
    description TEXT,
    FOREIGN KEY(bookmark_id) REFERENCES bookmark(id)
);

CREATE TABLE tag (
    id INTEGER PRIMARY KEY,
    bookmark_id INTEGER,
    name TEXT,
    FOREIGN KEY(bookmark_id) REFERENCES bookmark(id)
);

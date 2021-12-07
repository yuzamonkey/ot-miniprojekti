CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    author TEXT,
    title TEXT,
    isbn TEXT
);

CREATE TABLE videos (
    id SERIAL PRIMARY KEY,
    title TEXT,
    url TEXT,
    comment TEXT
);

CREATE TABLE blogposts (
    id SERIAL PRIMARY KEY,
    title TEXT,
    author TEXT,
    url TEXT
);

CREATE TABLE podcasts (
    id SERIAL PRIMARY KEY,
    name TEXT,
    title TEXT,
    description TEXT
);

CREATE TABLE courses (
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE tags (
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE tagconnections (
    id SERIAL PRIMARY KEY,
    tag_id INTEGER REFERENCES tags DEFAULT NULL,
    book_id INTEGER REFERENCES books DEFAULT NULL,
    video_id INTEGER REFERENCES videos DEFAULT NULL,
    blog_id INTEGER REFERENCES blogposts DEFAULT NULL,
    podcast_id INTEGER REFERENCES podcasts DEFAULT NULL
);


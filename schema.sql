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
    author TEXT,
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

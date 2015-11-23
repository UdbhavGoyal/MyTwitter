# MyTwitter

This project is a twitter website which allows users to signup and login into their accounts,upload image,post tweets and follow different people and see tweets of people they follow

Setup Instructions

Edit application.properties file Write your own postgres username inside 'spring.datasource.username' and password inside 'spring.datasource.password' there

In spring.mail.username type your email id through which you want to send mail In spring.mail.password type the password of your email id

Instructions for configuring database and creating tables

Create a database named 'twitter' in postgres using following query :-

CREATE DATABASE twitter;

Type in the following : create sequence hibernate_sequence start with 1; The above line is written so that auto increment works properly

Now type in following : -

CREATE TABLE follow ( id serial NOT NULL, email character varying(256) NOT NULL, followuser character varying(256) NOT NULL, CONSTRAINT follow_pkey PRIMARY KEY (id) );

CREATE TABLE tweet ( id serial NOT NULL, tweet character varying(256) NOT NULL, email character varying(256) NOT NULL, tweettimestamp timestamp without time zone NOT NULL, CONSTRAINT tweet_pkey PRIMARY KEY (id) );

CREATE TABLE users ( email character varying(256) NOT NULL, firstname character varying(256) NOT NULL, lastname character varying(256) NOT NULL, password character varying(256) NOT NULL, imageurl character varying(256) NOT NULL, CONSTRAINT users_pkey PRIMARY KEY (email) );

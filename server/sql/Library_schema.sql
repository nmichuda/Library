drop database if exists library;
create database library;
use library;


-- create tables

create table user_roles (
    role_id int primary key auto_increment,
    role_title varchar(30) not null
);

create table authors (
	author_id int primary key auto_increment,
    author_firstName varchar(50) not null,
    author_lastName varchar(50) not null
    );


create table library_users(
    user_id int primary key auto_increment,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    username varchar(50) not null,
    password_hash varchar(250) not null,
    email varchar(250) not null,
	role_id int not null,
    constraint fk_user_role
    foreign key (role_id)
    references user_roles(role_id)
    on delete cascade
);




create table tags (
    tag_id int primary key auto_increment,
    tag_name varchar(30) not null
);


    
create table books (
	book_id int primary key auto_increment,
    book_title varchar(100) not null,
    book_isbn varchar(13),
    book_description varchar(3000),
    book_status varchar(20),
    author_id int,
    image_link varchar(300),
    author varchar(100),
    time_added datetime,
    user_id int,
    constraint fk_books_author
    foreign key (author_id)
    references authors(author_id)
    on delete set null
    
);
    
    
create table book_tags (
 tag_id int not null,
 book_id int not null,
 constraint pk_book_tag
 primary key (book_id, tag_id),
 constraint fk_tag_recipe
 foreign key (tag_id)
 references tags(tag_id)
 on delete cascade,
 constraint fk_book_tag
 foreign key (book_id)
 references books(book_id)
on delete cascade
);


create table user_books (
	user_id int not null,
    book_id int not null,
    status varchar(30) not null,
    constraint pk_book_user
    primary key (book_id, user_id),
    constraint fk_book_user
    foreign key (book_id)
    references books(book_id)
    on delete cascade,
    constraint fk_user_id
    foreign key (user_id)
    references library_users(user_id)
    on delete cascade
);




















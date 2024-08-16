  SET SQL_SAFE_UPDATES = 0;
  
	delete from authors;
    alter table authors auto_increment = 1;
    delete from book_tags;
    delete from user_books;
    delete from books;
    alter table books auto_increment = 1;
    delete from tags;
    alter table tags auto_increment = 1;
	delete from library_users;
    alter table library_users auto_increment = 1;
	delete from user_roles;
	alter table user_roles auto_increment = 1;
    
 insert into user_roles(role_title) values
        ('USER'),
        ('ADMIN');
        
insert into authors(author_firstName,author_lastName) 
	values
    ('Nick','Michuda'),
    ('Jess','Keller');
	
insert into library_users (first_name, last_name, username, password_hash, email, role_id) 
	values
    ('Bengt','Hugonin','hugonin8','$2a$04$78PLdDuy6nQB0ZMR..vcruQGsFX64gdorzHPDEZ453ZzDmGBZ.l8i','hugonin8@discuz.net',1),
	('Deeanne','Parade','parade9','$2a$04$6q6nJYv8cBzunRufEnOxBe/6oGKMnA0MAbKT2KXwWINUJHTOker7G','parade9@geocities.com',1);
    
insert into tags (tag_name)
	values
    ('Romance'),
    ('Fantasy');
    
insert into books (book_title,book_isbn,author_id,image_link)
	values
    ('Test book 1', '9781639730193', 1, '' ),
    ('Test book 2', '9781639730192', 2, '' );
    
insert into user_books (user_id, book_id, status)
values
(1,1,"Finished"),
(2,2,"Not Started");
 
 
 
SET SQL_SAFE_UPDATES = 1;

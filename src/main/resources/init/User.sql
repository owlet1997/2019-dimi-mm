create table UserRole (
id int not null,
name varchar(50) not null,
primary key(id) 
);

create table user_ (
id int,
email varchar(50),
password varchar(50) not null,
login varchar(50) not null,
name varchar(50) not null,
organization_name varchar(50),
position_name varchar(50),
avatar_img varchar(50),
role_id int not null,
primary key(id),
foreign key(role_id) references userrole(id) on delete cascade
);

create SEQUENCE user_id start with 1 increment by 1;

create or replace trigger tr_on_user_
before insert on user_
for each row
    begin
    if :new.id is null then select user_id.nextval
        into :new.id
        from dual;
    end if;
    end;
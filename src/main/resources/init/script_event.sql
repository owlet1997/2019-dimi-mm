create table COORDINATES (
                             id NUMBER(20) NOT NULL,
                             CONSTRAINT PK_geo_id PRIMARY KEY (id),
                             coordinates SDO_GEOMETRY );

create table CITY (
                      id number(10) NOT NULL,
                      CONSTRAINT PK_city_id PRIMARY KEY (id),
                      cityid varchar(20) not null,
                      name varchar2(100) not null
);

create table EVENT_TYPE (
                            id number(10) NOT NULL,
                            CONSTRAINT PK_event_type_id PRIMARY KEY (id),
                            type varchar2(100) not null
);

create table EVENT (
                       id number(10) NOT NULL,
                       CONSTRAINT PK_event_id PRIMARY KEY (id),
                       type number(10) not null,
                       CONSTRAINT FK_type_id foreign key (type) references CITY (id) ON DELETE CASCADE,
                       city number(10) not null,
                       CONSTRAINT FK_city_id foreign key (city) references CITY (id) ON DELETE CASCADE,
                       coord_id number(10),
                       CONSTRAINT FK_coord_id foreign key (coord_id) references COORDINATES (id) ON DELETE CASCADE,
                       description varchar2(255) not null,
                       time_start date not null,
                       time_end date not null,
                       name varchar2(200) not null
);

create table USER_EVENT (
                            user_id number(10) not null,
                            constraint FK_user_id foreign key (user_id) references USER_ (id),
                            event_id number(10) not null,
                            constraint FK_event_id foreign key (event_id) references EVENT (id),
                            UNIQUE (user_id, event_id),
                            show_order number(2) not null
);
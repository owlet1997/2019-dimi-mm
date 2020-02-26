create SEQUENCE event_item_id_seq start with 1;

CREATE OR REPLACE TRIGGER event_item_id_generate
  BEFORE INSERT ON event_item
  FOR EACH ROW
BEGIN
  :new.id := event_item_id_seq.nextval;
END; 

create table EVENT_ITEM(
    id number(10) NOT NULL primary key,
    parent_id number(10),
    name varchar2(50) not null,
    time date not null,
    foreign key(parent_id) REFERENCES EVENT(ID) ON DELETE CASCADE
);

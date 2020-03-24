INSERT INTO eventx.t_user(id, password, username)
VALUES (1, '12345', 'Username');

INSERT INTO eventx.t_role(id, name)
  VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');

INSERT INTO eventx.t_user_roles(user_id, roles_id)
  VALUES (1, 2);

update eventx.t_user_roles
set roles_id = 2 where user_id = 3;

select * from eventx.t_user;
select * from eventx.t_role;
select * from eventx.t_user_roles;
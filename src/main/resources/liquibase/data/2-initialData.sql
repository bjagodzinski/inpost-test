INSERT INTO public.person (id, first_name, last_name)
VALUES (nextval('person_seq'), 'Daniel'::varchar(255), 'Kubik'::varchar(255));

INSERT INTO public.person (id, first_name, last_name)
VALUES (nextval('person_seq'), 'Marek'::varchar(255), 'Wojcieszek'::varchar(255));

INSERT INTO public.person (id, first_name, last_name)
VALUES (nextval('person_seq'), 'Bartosz'::varchar(255), 'Jagodziński'::varchar(255));

INSERT INTO public.permission (id, name)
VALUES (1, 'Permission1'::varchar(255));

INSERT INTO public.permission (id, name)
VALUES (2, 'Permission2'::varchar(255));

INSERT INTO public.role (id, name)
VALUES (1, 'Role1'::varchar(255));

INSERT INTO public.role (id, name)
VALUES (2, 'Role2'::varchar(255));

INSERT INTO public.role_permission (role_id, permission_id)
VALUES (1, 1);

INSERT INTO public.role_permission (role_id, permission_id)
VALUES (2, 2);

COMMIT;

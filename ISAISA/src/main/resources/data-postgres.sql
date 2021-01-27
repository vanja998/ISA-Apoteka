insert into pharmacy(id, name, address, description) values (nextval('seq_pharmacy'), 'Apoteka Jankovic', 'Bulevar Oslobodjenja 127', 'Bukvalno nebitno');

insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled) values ('patient', nextval('seq_user'), 'dad', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Pera', 'Peric', 'Dunavska', '060111111', 'Novi Sad', 'Srbija', true);
--ovo je lozinka 123
insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled) values ('supplier', nextval('seq_user'), 'bab', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Mika', 'Mikic', 'Dunavska', '060111112', 'Novi Sad', 'Srbija', true);
insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled, pharmacy_id) values ('adminpharmacy', nextval('seq_user'), 'cac', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Jovan', 'Jovanovic', 'Dunavska', '060111113', 'Novi Sad', 'Srbija', true, 1);

INSERT INTO AUTHORITY (name) VALUES ('ROLE_PATIENT');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMINSYSTEM');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_DERMATOLOGIST');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMINPHARMACY');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PHARMACIST');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_SUPPLIER');

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 6);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (3, 4);


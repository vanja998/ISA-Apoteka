insert into pharmacy(id, name, address, description) values (nextval('seq_pharmacy'), 'Apoteka Jankovic', 'Bulevar Oslobodjenja 127', 'Bukvalno nebitno');
insert into pharmacy(id, name, address, description) values (nextval('seq_pharmacy'), 'Apoteka mala moja', 'Bulevar Oslobodjenja 128', 'dodji ako si bolestan');

insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled) values ('patient', nextval('seq_user'), 'dad', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Pera', 'Peric', 'Dunavska', '060111111', 'Novi Sad', 'Srbija', true);
insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled) values ('patient', nextval('seq_user'), 'jokjok', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Dejan', 'Deki', 'Dunavska', '060111111', 'Novi Sad', 'Srbija', true);
--ovo je lozinka 123
insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled) values ('supplier', nextval('seq_user'), 'bab', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Mika', 'Mikic', 'Dunavska', '060111112', 'Novi Sad', 'Srbija', true);
insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled, pharmacy_id) values ('adminpharmacy', nextval('seq_user'), 'cac', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Jovan', 'Jovanovic', 'Dunavska', '060111113', 'Novi Sad', 'Srbija', true, 1);

insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled, pharmacy_id) values ('pharmacist', nextval('seq_user'), 'mac', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Jovan', 'Jovanovic', 'Dunavska', '060111113', 'Novi Sad', 'Srbija', true, 1);
insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled) values ('dermatologist', nextval('seq_user'), 'kuckuc', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Jovan', 'Jovanovic', 'Dunavska', '060111113', 'Novi Sad', 'Srbija', true);
insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled, pharmacy_id) values ('adminpharmacy', nextval('seq_user'), 'avav', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Jovan', 'Jovanovic', 'Dunavska', '060111113', 'Novi Sad', 'Srbija', true, 1);

insert into "dermatologist_pharmacy" (dermatologist_id,pharmacy_id) values (5,1);
insert into "medication" (id,code,name,type_med,shape_med,ingredients,producer,prescription,notes) values( nextval('seq_medication'),'as1234','paracetamol','tableta','okrugla','otrovcina','hemofarm',true,'javi se lekaru u slucaju nuspojava');
insert into "medication_altmedication"(medication_id,altmedication_id) values(1,1);
insert into "med_pharmacies" (pharmacy_id,medication_id) values (1,1);
insert into "allergy_patient" (patient_id,medication_id) values (1,1);

INSERT INTO AUTHORITY (name) VALUES ('ROLE_PATIENT');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMINSYSTEM');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_DERMATOLOGIST');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMINPHARMACY');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PHARMACIST');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_SUPPLIER');

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 6); --??
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (3, 6);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (4, 4);


insert into pharmacy(id, name, address, description) values (nextval('seq_pharmacy'), 'Apoteka Jankovic', 'Bulevar Oslobodjenja 127', 'Bukvalno nebitno');
insert into pharmacy(id, name, address, description) values (nextval('seq_pharmacy'), 'Apoteka mala moja', 'Bulevar Oslobodjenja 128', 'dodji ako si bolestan');
insert into pharmacy(id, name, address, description) values (nextval('seq_pharmacy'), 'Apoteka Ana', 'Bulevar Aleksanda 128', 'dodji ako si bolestan');

insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled) values ('patient', nextval('seq_user'), 'dvornicdejan08@gmail.com', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Pera', 'Peric', 'Dunavska', '060111111', 'Novi Sad', 'Srbija', true);
insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled) values ('patient', nextval('seq_user'), 'jokjok', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Dejan', 'Deki', 'Dunavska', '060111111', 'Novi Sad', 'Srbija', true);
--ovo je lozinka 123
insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled) values ('supplier', nextval('seq_user'), 'bab', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Mika', 'Mikic', 'Dunavska', '060111112', 'Novi Sad', 'Srbija', true);
insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled, pharmacy_id) values ('adminpharmacy', nextval('seq_user'), 'cac', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Jovan', 'Jovanovic', 'Dunavska', '060111113', 'Novi Sad', 'Srbija', true, 1);

insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled, pharmacy_id, rating,beginofwork,endofwork) values ('pharmacist', nextval('seq_user'), 'mac', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Jovan', 'Jovanovic', 'Dunavska', '060111113', 'Novi Sad', 'Srbija', true, 1, 3.5,'11:00:00','12:00:00');
insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled) values ('dermatologist', nextval('seq_user'), 'kuckuc', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Jovan', 'Jovanovic', 'Dunavska', '060111113', 'Novi Sad', 'Srbija', true);
insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled, pharmacy_id) values ('adminpharmacy', nextval('seq_user'), 'avav', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Jovan', 'Jovanovic', 'Dunavska', '060111113', 'Novi Sad', 'Srbija', true, 1);
insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled) values ('patient', nextval('seq_user'), 'malisignali', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Nata', 'Bekv', 'Mileticeva', '060122211', 'Novi Sad', 'Srbija', true);
insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled) values ('adminsystem', nextval('seq_user'), 'va', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'tika', 'spic', 'blabla', '062111113', 'Pecenjevce', 'Srbija', true);
insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled, pharmacy_id, rating) values ('pharmacist', nextval('seq_user'), 'dara', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Dara', 'Granic', 'Dunavska', '060111113', 'Novi Sad', 'Srbija', true, 1, 5.0);

insert into "appointment" (id,patient_id,dermatologist_id,beginofappointment,endofappointment,price) values (nextval('seq_appointment'),null, 6 ,'2007-12-03 10:15:30','2007-12-03 10:16:30',5000);
insert into "dermatologist_pharmacyy" (id,beginofwork,endofwork,dermatologist_id_id,pharmacy_id_id) values (nextval('seq_dermatologist_pharmacy'),'16:00:00','17:00:00',5,1);
insert into "medication" (id,code,name,type_med,shape_med,ingredients,producer,prescription,notes) values( nextval('seq_medication'),'as1234','paracetamol','tableta','okrugla','otrovcina','hemofarm',true,'javi se lekaru u slucaju nuspojava');
insert into "medication_altmedication"(medication_id,altmedication_id) values(1,1);
insert into "med_pharmacies" (pharmacy_id,medication_id) values (1,1);
insert into "allergy_patient" (patient_id,medication_id) values (1,1);
insert into "complaint"(id,question,answered,patient_id,user_id) values(1,'Zasto dermatolog stalno kasni??',false,1,6);

INSERT INTO AUTHORITY (name) VALUES ('ROLE_PATIENT');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMINSYSTEM');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_DERMATOLOGIST');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMINPHARMACY');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PHARMACIST');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_SUPPLIER');

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (3, 6);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (6, 3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (4, 4);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (5, 5);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (8, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (9, 2);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (10, 5);




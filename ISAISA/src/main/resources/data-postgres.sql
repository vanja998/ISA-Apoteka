insert into pharmacy(id, name, address, description) values (nextval('seq_pharmacy'), 'Apoteka Jankovic', 'Bulevar Oslobodjenja 127', 'Bukvalno nebitno');
insert into pharmacy(id, name, address, description) values (nextval('seq_pharmacy'), 'Apoteka mala moja', 'Bulevar Oslobodjenja 128', 'dodji ako si bolestan');
insert into pharmacy(id, name, address, description) values (nextval('seq_pharmacy'), 'Apoteka Ana', 'Bulevar Aleksanda 128', 'dodji ako si bolestan');

--ovo je lozinka 123
insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled, penalty) values ('patient', nextval('seq_user'), 'dvornicdejan08@gmail.com', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Pera', 'Peric', 'Dunavska', '060111111', 'Novi Sad', 'Srbija', true, 0);
insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled, penalty) values ('patient', nextval('seq_user'), 'jokjok', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Dejan', 'Deki', 'Dunavska', '060111111', 'Novi Sad', 'Srbija', true, 0);
insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled, flag) values ('supplier', nextval('seq_user'), 'bab', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Mika', 'Mikic', 'Dunavska', '060111112', 'Novi Sad', 'Srbija', true,0);
insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled, pharmacy_id) values ('adminpharmacy', nextval('seq_user'), 'cac', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Jovan', 'Jovanovic', 'Dunavska', '060111113', 'Novi Sad', 'Srbija', true, 1);

insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled, pharmacy_id, rating,beginofwork,endofwork) values ('pharmacist', nextval('seq_user'), 'mac', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Jovan', 'Jovanovic', 'Dunavska', '060111113', 'Novi Sad', 'Srbija', true, 1, 3.5,'00:00:00','23:59:00');
insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled) values ('dermatologist', nextval('seq_user'), 'kuckuc', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Jovan', 'Jovanovic', 'Dunavska', '060111113', 'Novi Sad', 'Srbija', true);
insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled, pharmacy_id) values ('adminpharmacy', nextval('seq_user'), 'avav', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Jovan', 'Jovanovic', 'Dunavska', '060111113', 'Novi Sad', 'Srbija', true, 1);
insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled, penalty) values ('patient', nextval('seq_user'), 'malisignali', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Nata', 'Bekv', 'Mileticeva', '060122211', 'Novi Sad', 'Srbija', true, 0);
insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled,flag) values ('adminsystem', nextval('seq_user'), 'va', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'tika', 'spic', 'blabla', '062111113', 'Pecenjevce', 'Srbija', true,0);
insert into "users" (rolee, id, email, password, firstName, lastName, address, phone, city, country, enabled, pharmacy_id, rating) values ('pharmacist', nextval('seq_user'), 'dara', '$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou', 'Dara', 'Granic', 'Dunavska', '060111113', 'Novi Sad', 'Srbija', true, 1, 5.0);

insert into "medication" (id,code,name,type_med,shape_med,ingredients,producer,prescription,notes) values( nextval('seq_medication'),'as1234','paracetamol','tableta','okrugla','otrovcina','hemofarm',true,'javi se lekaru u slucaju nuspojava');
insert into "medication" (id,code,name,type_med,shape_med,ingredients,producer,prescription,notes) values( nextval('seq_medication'),'bb12','brufen','tableta','okrugla','otrov','hemofarm',true,'javi se lekaru u slucaju nuspojava');
insert into "medication" (id,code,name,type_med,shape_med,ingredients,producer,prescription,notes) values( nextval('seq_medication'),'pp123','analgin','tableta','okrugla','otrovcic','hemofarm',true,'javi se lekaru u slucaju nuspojava');
insert into "medication" (id,code,name,type_med,shape_med,ingredients,producer,prescription,notes) values( nextval('seq_medication'),'afeeff','andol','tableta','okrugla','otrovcina','hemofarm',true,'javi se lekaru u slucaju nuspojava');
insert into "medication" (id,code,name,type_med,shape_med,ingredients,producer,prescription,notes) values( nextval('seq_medication'),'aefefef','fervex','prasak','zrnast','otrovcina','hemofarm',true,'javi se lekaru u slucaju nuspojava');

insert into "medication_altmedication"(medication_id,altmedication_id) values(1,1);
insert into "medication_altmedication"(medication_id,altmedication_id) values(2,3);
insert into "medication_altmedication"(medication_id,altmedication_id) values(3,4);

insert into "med_pharmacies" (pharmacy_id,medication_id) values (1,1);
insert into "med_pharmacies" (pharmacy_id,medication_id) values (1,2);

insert into "allergy_patient" (patient_id,medication_id) values (1,1);


insert into "complaint"(id,question,answered,patient_id,user_id,ishospital) values(nextval('seq_complaint'),'Zasto dermatolog stalno kasni??',false,1,6,false);
insert into "complaint"(id,question,answered,patient_id,pharmacy_id,ishospital) values(nextval('seq_complaint'),'Zasto dermatolog stalno kasni??',false,1,1,true);

insert into "orderr"(id,dateDeadline,statusAdmin,admin_id) values(nextval('seq_orderr'),'2021-12-31','ceka_na_ponude',4);

insert into "orderr_medication"(id,amount,medication_id,orderr_id) values (nextval('seq_order'),5,1,1);
insert into "orderr_medication"(id,amount,medication_id,orderr_id) values (nextval('seq_order'),5,2,1);
insert into "orderr_medication"(id,amount,medication_id,orderr_id) values (nextval('seq_order'),5,3,1);

insert into "supplier_medication"(supplier_id,medication_id) values(3,1);
insert into "supplier_medication"(supplier_id,medication_id) values(3,2);
insert into "supplier_medication"(supplier_id,medication_id) values(3,3);

insert into "appointment" (id,patient_id,dermatologist_id,pharmacy_appointment_id,beginofappointment,endofappointment,price) values (nextval('seq_appointment'),null, 6 ,1,'2007-12-03 10:15:30','2007-12-03 10:16:30',5000);
insert into "appointment"(id,patient_id,dermatologist_id,pharmacy_appointment_id, beginofappointment, endofappointment, price) values (nextval('seq_appointment'), 2,6,1,'2021-02-06 00:00:00','2021-02-06 23:00:00', 300);
insert into "appointment" (id, patient_id, dermatologist_id, pharmacy_appointment_id, beginofappointment, endofappointment, price) values (nextval('seq_appointment'), null, 6, 1, '2021-02-20 16:20', '2021-02-20 16:40', 20);
insert into "appointment"(id,patient_id,dermatologist_id,pharmacy_appointment_id, beginofappointment, endofappointment, price) values (nextval('seq_appointment'), 1,6,1,'2021-02-08 00:00:00','2021-08-07 23:59:00', 300);
insert into "appointment" (id,patient_id,dermatologist_id,pharmacy_appointment_id,beginofappointment,endofappointment,price) values (nextval('seq_appointment'),1, 6,1,'2021-06-02 10:15:30','2021-06-02 10:16:30',5000);
insert into "appointment" (id,patient_id,dermatologist_id,pharmacy_appointment_id,beginofappointment,endofappointment,price) values (nextval('seq_appointment'),1, 6,1,'2021-12-03 10:15:30','2021-12-03 10:16:30',5000);
insert into "appointment" (id,patient_id,dermatologist_id,pharmacy_appointment_id,beginofappointment,endofappointment,price) values (nextval('seq_appointment'),2, 6,1,'2021-02-08 10:15:30','2021-02-08 12:16:30',5000);
insert into "appointment" (id,patient_id,dermatologist_id,pharmacy_appointment_id,beginofappointment,endofappointment,price) values (nextval('seq_appointment'),1, 6,1,'2021-02-07 10:15:30','2021-02-07 11:16:30',5000);

insert into "counseling"(id,patient_id,pharmacist_id, beginofappointment, endofappointment, price) values (nextval('seq_counseling'), 1,5, '2021-08-02 00:00:00','2021-08-02 23:59:00', 3000);
insert into "counseling"(id,patient_id,pharmacist_id, beginofappointment, endofappointment, price) values (nextval('seq_counseling'), 1,5, '2021-02-08 00:00:00','2021-02-08 23:59:00', 3000);




insert into "counseling" (id,patient_id,pharmacist_id,beginofappointment,endofappointment,price) values (nextval('seq_counseling'), null, 10, '2021-02-07 00:00:00','2021-02-07 23:59:00', 20);
insert into "counseling" (id,patient_id,pharmacist_id,beginofappointment,endofappointment,price) values (nextval('seq_counseling'), 1, 10, '2021-02-07 00:00:00','2021-02-07 23:59:00', 20);
insert into "counseling" (id,patient_id,pharmacist_id,beginofappointment,endofappointment,price) values (nextval('seq_counseling'), null, 10, '2021-02-05 00:00:00','2021-02-05 23:59:00', 20);
insert into "counseling" (id,patient_id,pharmacist_id) values (nextval('seq_counseling'),2,10);

insert into "dermatologist_pharmacyy" (id,beginofwork,endofwork,dermatologist_id,pharmacy_id) values (nextval('seq_dermatologist_pharmacy'),'00:00:00','23:59:00',6,1);
--insert into "dermatologist_pharmacyy" (id,beginofwork,endofwork,dermatologist_id,pharmacy_id) values (nextval('seq_dermatologist_pharmacy'),'16:00:00','17:00:00',6,1);

insert into "examination"(id,report,therapyduration, examinationAppointment_id, prescriptedmedication_id,newappointment_id) values (nextval('seq_examination'), 'Pacijenta boli glava', 4, 5,2, null);
insert into "examination"(id,report,therapyduration, examinationAppointment_id, prescriptedmedication_id,newappointment_id) values (nextval('seq_examination'), 'Pacijenta boli glava', 2, 2,2, null);

insert into "promotion" (id, description, validFrom, validUntil, pharmacy_id) values (nextval('seq_promotion'), 'Akcija na sve proizvode za dentalnu higijenu', '2021-02-01', '2021-02-28', 1);

insert into "promotion_patients" (patient_id, pharmacy_id) values (1, 1);

insert into "reservation"(id,patient_id,pharmacy_id,medication_id,dateofreservation,medicationtaken) values (nextval('seq_reservation'),2,1,1,'2021-12-03',false);

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




--DROP TABLE IF EXISTS t_assigned_job_question;
--DROP TABLE IF EXISTS t_saved_job;
--DROP TABLE IF EXISTS t_question_answer;
--DROP TABLE IF EXISTS t_question_option;
--DROP TABLE IF EXISTS t_question;
--DROP TABLE IF EXISTS t_candidate_references;
--DROP TABLE IF EXISTS t_candidate_documents;
--DROP TABLE IF EXISTS t_candidate_language;
--DROP TABLE IF EXISTS t_candidate_education;
--DROP TABLE IF EXISTS t_candidate_training_exp;
--DROP TABLE IF EXISTS t_candidate_project_exp;
--DROP TABLE IF EXISTS t_candidate_work_exp;
--DROP TABLE IF EXISTS t_candidate_skill;
--DROP TABLE IF EXISTS t_candidate_address;
--DROP TABLE IF EXISTS t_candidate_family;
--DROP TABLE IF EXISTS t_applicant;
--DROP TABLE IF EXISTS t_candidate_user;
--DROP TABLE IF EXISTS t_candidate_profile;
--DROP TABLE IF EXISTS t_candidate_status;
--DROP TABLE IF EXISTS t_person_type;
--DROP TABLE IF EXISTS t_marital_status;
--DROP TABLE IF EXISTS t_religion;
--DROP TABLE IF EXISTS t_hiring_status;
--DROP TABLE IF EXISTS t_job;
--DROP TABLE IF EXISTS t_company;
--DROP TABLE IF EXISTS t_employment_type;
--DROP TABLE IF EXISTS t_file;
--DROP TABLE IF EXISTS t_file_type;

CREATE TABLE t_file (
	id VARCHAR(36) NOT NULL,
	filename TEXT NOT NULL,
	file_extension VARCHAR(5) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_file ADD CONSTRAINT file_pk
	PRIMARY KEY(id);

CREATE TABLE t_candidate_status (
	id VARCHAR(36) NOT NULL,
	status_code VARCHAR(5) NOT NULL,
	status_name VARCHAR(20) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_candidate_status ADD CONSTRAINT candidate_status_pk
	PRIMARY KEY(id);
ALTER TABLE t_candidate_status ADD CONSTRAINT status_code_bk
	UNIQUE(status_code);

CREATE TABLE t_religion (
	id VARCHAR(36) NOT NULL,
	religion_code VARCHAR(5) NOT NULL,
	religion_name VARCHAR(20) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_religion ADD CONSTRAINT religion_pk
	PRIMARY KEY(id);
ALTER TABLE t_religion ADD CONSTRAINT religion_code_bk
	UNIQUE (religion_code);

CREATE TABLE t_marital_status (
	id VARCHAR(36) NOT NULL,
	marital_code VARCHAR(5) NOT NULL,
	marital_name VARCHAR(20) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_marital_status ADD CONSTRAINT marital_status_pk
	PRIMARY KEY(id);
ALTER TABLE t_marital_status ADD CONSTRAINT marital_code_bk
	UNIQUE (marital_code);

CREATE TABLE t_person_type ( 
	id VARCHAR(36) NOT NULL,
	type_code VARCHAR(5) NOT NULL,
	type_name VARCHAR(20) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_person_type ADD CONSTRAINT person_type_pk
	PRIMARY KEY(id);
ALTER TABLE t_person_type ADD CONSTRAINT type_code_bk
	UNIQUE (type_code);

CREATE TABLE t_candidate_profile (
	id VARCHAR(36) NOT NULL,
	salutation VARCHAR(4) NOT NULL,
	fullname VARCHAR(50) NOT NULL,
	gender VARCHAR(10) NOT NULL,
	experience VARCHAR(10) NOT NULL,
	expected_salary float NOT NULL,
	phone_number VARCHAR(20) NOT NULL,
	mobile_number VARCHAR(20) NOT NULL,
	nik VARCHAR(50) NOT NULL,
	birth_date date NOT NULL,
	birth_place VARCHAR(20) NOT NULL,
	marital_status_id VARCHAR(36) NOT NULL,
	religion_id VARCHAR(36) NOT NULL,
	person_type_id VARCHAR(36) NOT NULL,
	file_id VARCHAR(36) NOT NULL,
	candidate_status_id VARCHAR(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_candidate_profile ADD CONSTRAINT candidate_profile_pk
	PRIMARY KEY(id);
ALTER TABLE t_candidate_profile ADD CONSTRAINT marital_status_id_fk_t_candidate_profile
	FOREIGN KEY (marital_status_id)
	REFERENCES t_marital_status(id);
ALTER TABLE t_candidate_profile ADD CONSTRAINT religion_id_fk_t_candidate_profile
	FOREIGN KEY (religion_id)
	REFERENCES t_religion(id);
ALTER TABLE t_candidate_profile ADD CONSTRAINT person_type_id_fk_t_candidate_profile
	FOREIGN KEY (person_type_id)
	REFERENCES t_person_type(id);
ALTER TABLE t_candidate_profile ADD CONSTRAINT file_id_fk_t_candidate_profile
	FOREIGN KEY (file_id)
	REFERENCES t_file(id);
ALTER TABLE t_candidate_profile ADD CONSTRAINT candidate_status_fk_t_candidate_profile
	FOREIGN KEY(candidate_status_id)
	REFERENCES t_candidate_status(id);

CREATE TABLE t_candidate_user ( 
	id VARCHAR(36) NOT NULL,
	user_email VARCHAR(50) NOT NULL,
	user_password TEXT NOT NULL,
	profile_id VARCHAR(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_candidate_user ADD CONSTRAINT candidate_user_pk
	PRIMARY KEY(id);
ALTER TABLE t_candidate_user ADD CONSTRAINT profile_id_fk_t_candidate_user
	FOREIGN KEY (profile_id)
	REFERENCES t_candidate_profile(id);

CREATE TABLE t_candidate_family ( 
	id VARCHAR(36) NOT NULL,
	fullname VARCHAR(50) NOT NULL,
	relationship VARCHAR(10) NOT NULL,
	degree_name VARCHAR(50) NOT NULL,
	occupation VARCHAR(50) NOT NULL,
	birth_date date NOT NULL,
	birth_place VARCHAR(20) NOT NULL,
	user_id VARCHAR(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_candidate_family ADD CONSTRAINT candidate_family_pk
	PRIMARY KEY(id);
ALTER TABLE t_candidate_family ADD CONSTRAINT user_id_fk_t_candidate_family
	FOREIGN KEY (user_id)
	REFERENCES t_candidate_user(id);

CREATE TABLE t_candidate_address ( 
	id VARCHAR(36) NOT NULL,
	address TEXT NOT NULL,
	residence_type VARCHAR(10) NOT NULL,
	country VARCHAR(20) NOT NULL,
	province VARCHAR(20) NOT NULL,
	city VARCHAR(20) NOT NULL,
	postal_code VARCHAR(10) NOT NULL,
	user_id VARCHAR(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);  

ALTER TABLE t_candidate_address ADD CONSTRAINT candidate_address_pk
	PRIMARY KEY(id);
ALTER TABLE t_candidate_address ADD CONSTRAINT user_id_fk_t_candidate_address
	FOREIGN KEY (user_id)
	REFERENCES t_candidate_user(id);

CREATE TABLE t_candidate_skill ( 
	id VARCHAR(36) NOT NULL,
	skill_name text NOT NULL,
	user_id VARCHAR(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_candidate_skill ADD CONSTRAINT candidate_skill_pk
	PRIMARY KEY(id);
ALTER TABLE t_candidate_skill ADD CONSTRAINT user_id_fk_t_candidate_skill
	FOREIGN KEY (user_id)
	REFERENCES t_candidate_user(id);

CREATE TABLE t_candidate_work_exp ( 
	id VARCHAR(36) NOT NULL,
	position_name VARCHAR(30) NOT NULL,
	company_name VARCHAR(30) NOT NULL,
	address TEXT NOT NULL,
	responsibility TEXT NOT NULL,
	reason_leave TEXT NOT NULL,
	last_salary float NOT NULL,
	start_date timestamp NOT NULL,
	end_date timestamp NOT NULL,
	user_id VARCHAR(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_candidate_work_exp ADD CONSTRAINT candidate_work_exp_pk
	PRIMARY KEY(id);
ALTER TABLE t_candidate_work_exp ADD CONSTRAINT user_id_fk_t_candidate_work_exp
	FOREIGN KEY (user_id)
	REFERENCES t_candidate_user(id);

CREATE TABLE t_candidate_project_exp ( 
	id VARCHAR(36) NOT NULL,
	project_name VARCHAR(30) NOT NULL,
	project_url TEXT,
	description TEXT NOT NULL,
	start_date timestamp NOT NULL,
	end_date timestamp NOT NULL,
	user_id VARCHAR(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_candidate_project_exp ADD CONSTRAINT project_exp_pk
	PRIMARY KEY(id);
ALTER TABLE t_candidate_project_exp ADD CONSTRAINT project_exp_user_fk
	FOREIGN KEY(user_id)
	REFERENCES t_candidate_user(id);

CREATE TABLE t_candidate_training_exp ( 
	id VARCHAR(36) NOT NULL,
	organization_name VARCHAR(20) NOT NULL,
	training_name VARCHAR(20) NOT NULL,
	description TEXT NOT NULL,
	start_date timestamp NOT NULL,
	end_date timestamp NOT NULL,
	user_id VARCHAR(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_candidate_training_exp ADD CONSTRAINT training_exp_pk
	PRIMARY KEY(id);
ALTER TABLE t_candidate_training_exp ADD CONSTRAINT training_exp_user_fk
	FOREIGN KEY(user_id)
	REFERENCES t_candidate_user(id); 

CREATE TABLE t_candidate_education ( 
	id VARCHAR(36) NOT NULL,
	degree_name VARCHAR(50) NOT NULL,
	institution_name VARCHAR(50) NOT NULL,
	majors VARCHAR(50) NOT NULL,
	cGPA float NOT NULL,
	start_year date NOT NULL,
	end_year date NOT NULL,
	user_id VARCHAR(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_candidate_education ADD CONSTRAINT candidate_education_pk
	PRIMARY KEY(id);
ALTER TABLE t_candidate_education ADD CONSTRAINT candidate_education_user_fk
	FOREIGN KEY(user_id)
	REFERENCES t_candidate_user(id); 

CREATE TABLE t_candidate_language ( 
	id VARCHAR(36) NOT NULL,
	language_name VARCHAR(30) NOT NULL,
	writing_rate VARCHAR(2) NOT NULL,
	speaking_rate VARCHAR(2) NOT NULL,
	listening_rate VARCHAR(2) NOT NULL,
	user_id VARCHAR(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_candidate_language ADD CONSTRAINT candidate_language_pk
	PRIMARY KEY(id);
ALTER TABLE t_candidate_language ADD CONSTRAINT candidate_language_user_fk
	FOREIGN KEY(user_id)
	REFERENCES t_candidate_user(id); 

CREATE TABLE t_file_type (
	id VARCHAR(36) NOT NULL,
	type_code VARCHAR(5) NOT NULL,
	type_name VARCHAR(20) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_file_type ADD CONSTRAINT file_type_pk
	PRIMARY KEY(id);
ALTER TABLE t_file_type ADD CONSTRAINT file_type_bk
	UNIQUE(type_code, type_name);

CREATE TABLE t_candidate_documents ( 
	id VARCHAR(36) NOT NULL,
	doc_name VARCHAR(30) NOT NULL,
	user_id VARCHAR(36) NOT NULL,
	file_id VARCHAR(36) NOT NULL,
	file_type_id VARCHAR(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_candidate_documents ADD CONSTRAINT candidate_documents_pk
	PRIMARY KEY(id);
ALTER TABLE t_candidate_documents ADD CONSTRAINT candidate_documents_user_fk
	FOREIGN KEY(user_id)
	REFERENCES t_candidate_user(id);
ALTER TABLE t_candidate_documents ADD CONSTRAINT candidate_documents_file_fk
	FOREIGN KEY(file_id)
	REFERENCES t_file(id); 
ALTER TABLE t_candidate_documents ADD CONSTRAINT candidate_documents_file_type_fk
	FOREIGN KEY(file_type_id)
	REFERENCES t_file_type(id); 

CREATE TABLE t_candidate_references ( 
	id VARCHAR(36) NOT NULL,
	fullname VARCHAR(50) NOT NULL,
	relationship VARCHAR(10) NOT NULL,
	occupation VARCHAR(20) NOT NULL,
	phone_number VARCHAR(20) NOT NULL,
	email VARCHAR(50) NOT NULL,
	company VARCHAR(50) NOT NULL,
	description TEXT NOT NULL,
	user_id VARCHAR(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_candidate_references ADD CONSTRAINT candidate_references_pk
	PRIMARY KEY(id);
ALTER TABLE t_candidate_references ADD CONSTRAINT candidate_references_user_fk
	FOREIGN KEY(user_id)
	REFERENCES t_candidate_user(id);

CREATE TABLE t_employment_type(
	id VARCHAR(36) NOT NULL,
	employment_type_code varchar(5) NOT NULL,
	employment_type_name varchar(20) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_employment_type ADD CONSTRAINT t_employment_type_pk 
	PRIMARY KEY(id);

CREATE TABLE t_company(
	id VARCHAR(36) NOT NULL,
	company_code varchar(5) NOT NULL,
	company_name varchar(30) NOT NULL,
	address text NOT NULL,
	company_url text,
	company_phone varchar(15) NOT NULL,
	photo_id VARCHAR(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_company ADD CONSTRAINT t_company_pk 
	PRIMARY KEY(id);
ALTER TABLE t_company ADD CONSTRAINT t_company_picture_fk 
	FOREIGN KEY(photo_id)
	REFERENCES t_file(id);
	
CREATE TABLE t_job(
	id VARCHAR(36) NOT NULL,
	job_code varchar(5) NOT NULL,
	job_name varchar(30) NOT NULL,
	company_id VARCHAR(36) NOT NULL,
	start_date date NOT NULL,
	end_date date NOT NULL,
	description text NOT NULL,
	expected_salary_min int,
	expected_salary_max int,
	employment_type_id VARCHAR(36) NOT NULL,
	job_picture_id VARCHAR(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_job ADD CONSTRAINT t_job_pk 
	PRIMARY KEY(id);
ALTER TABLE t_job ADD CONSTRAINT t_company_fk 
	FOREIGN KEY(company_id)
	REFERENCES t_company(id);
ALTER TABLE t_job ADD CONSTRAINT t_employment_type_fk
	FOREIGN KEY(employment_type_id)
	REFERENCES t_employment_type(id);
ALTER TABLE t_job ADD CONSTRAINT t_job_picture_fk
	FOREIGN KEY(job_picture_id)
	REFERENCES t_file(id);

CREATE TABLE t_hiring_status(
	id VARCHAR(36) NOT NULL,
	status_code varchar(5) NOT NULL,
	status_name varchar(20) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_hiring_status ADD CONSTRAINT t_hiring_status_pk 
	PRIMARY KEY(id);
	
CREATE TABLE t_applicant(
	id VARCHAR(36) NOT NULL,
	applicant_code varchar(5) NOT NULL,
	job_id VARCHAR(36) NOT NULL,
	applied_date timestamp NOT NULL,
	status_id VARCHAR(36) NOT NULL,
	candidate_id VARCHAR(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_applicant ADD CONSTRAINT t_applicant_pk 
	PRIMARY KEY(id);
ALTER TABLE t_applicant ADD CONSTRAINT t_applicant_job_fk 
	FOREIGN KEY(job_id)
	REFERENCES t_job(id);
ALTER TABLE t_applicant ADD CONSTRAINT t_hiring_status_fk 
	FOREIGN KEY(status_id)
	REFERENCES t_hiring_status(id);
ALTER TABLE t_applicant ADD CONSTRAINT t_applicant_candidate_fk
	FOREIGN KEY(candidate_id)
	REFERENCES t_candidate_user(id);
	
CREATE TABLE t_saved_job ( 
	id VARCHAR(36) NOT NULL,
	job_id VARCHAR(36) NOT NULL,
	user_id VARCHAR(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_saved_job ADD CONSTRAINT t_saved_job_pk
	PRIMARY KEY(id);
ALTER TABLE t_saved_job ADD CONSTRAINT t_saved_job_job_fk
	FOREIGN KEY(job_id)
	REFERENCES t_job(id);
ALTER TABLE t_saved_job ADD CONSTRAINT t_saved_job_user_fk
	FOREIGN KEY(user_id)
	REFERENCES t_candidate_user(id);
	
CREATE TABLE t_question(
	id VARCHAR(36) NOT NULL,
	question_code varchar(5) NOT NULL,
	question_detail text NOT NULL,
	created_by VARCHAR(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by VARCHAR(36),
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_question ADD CONSTRAINT t_question_pk 
	PRIMARY KEY(id);

CREATE TABLE t_question_option(
	id VARCHAR(36) NOT NULL,
	option_label varchar(20) NOT NULL,
	is_correct boolean NOT NULL,
	question_id VARCHAR(36) NOT NULL,
	created_by VARCHAR(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by VARCHAR(36),
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_question_option ADD CONSTRAINT t_question_option_pk 
	PRIMARY KEY(id);
ALTER TABLE t_question_option ADD CONSTRAINT t_questionoption_fk
	FOREIGN KEY(question_id)
	REFERENCES t_question(id);

CREATE TABLE t_question_answer(
	id VARCHAR(36) NOT NULL,
	option_id VARCHAR(36) NOT NULL,
	candidate_id VARCHAR(36) NOT NULL,
	question_id VARCHAR(36) NOT NULL,
	created_by VARCHAR(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by VARCHAR(36),
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);


ALTER TABLE t_question_answer ADD CONSTRAINT t_question_answer_pk 
	PRIMARY KEY(id);
ALTER TABLE t_question_answer ADD CONSTRAINT t_option_fk
	FOREIGN KEY(option_id)
	REFERENCES t_question_option(id);
ALTER TABLE t_question_answer ADD CONSTRAINT t_candidate_fk
	FOREIGN KEY(candidate_id)
	REFERENCES t_candidate_user(id);

CREATE TABLE t_assigned_job_question(
	id VARCHAR(36) NOT NULL,
	job_id VARCHAR(36) NOT NULL,
	question_id VARCHAR(36) NOT NULL,
	created_by VARCHAR(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by VARCHAR(36),
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_assigned_job_question ADD CONSTRAINT t_assigned_job_question_pk 
	PRIMARY KEY(id);
ALTER TABLE t_assigned_job_question ADD CONSTRAINT t_assigned_job_question_job_fk
	FOREIGN KEY(job_id)
	REFERENCES t_job(id);
ALTER TABLE t_assigned_job_question ADD CONSTRAINT t_assigned_job_question_question_fk
	FOREIGN KEY(question_id)
	REFERENCES t_question(id);

SELECT * FROM t_file tf;
INSERT INTO t_file (id, filename, file_extension, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(),'ProfilePicture', '.png', 1, now(), true, 0),
	(uuid_generate_v4(),'DocumentDummy', '.pdf', 1, now(), true, 0),
	(uuid_generate_v4(),'CompanyPhoto', '.png', 1, now(), true, 0),
	(uuid_generate_v4(),'JobPhoto', '.png', 1, now(), true, 0);

SELECT * FROM t_candidate_status tcs;
INSERT INTO t_candidate_status (id, status_code, status_name, created_by, created_at, is_active, ver) VALUES	
	(uuid_generate_v4(), 'CS-01', 'Active', 1, now(), true, 0),
	(uuid_generate_v4(), 'CS-02', 'On Process', 1, now(), true, 0),
	(uuid_generate_v4(), 'CS-03', 'Blacklist', 1, now(), true, 0);

SELECT * FROM t_religion tr;
INSERT INTO t_religion (id, religion_code, religion_name, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), 'ISL', 'Islam', 1, now(), true, 0),
	(uuid_generate_v4(), 'CHR', 'Christian', 1, now(), true, 0),
	(uuid_generate_v4(), 'CHT', 'Catholic', 1, now(), true, 0),
	(uuid_generate_v4(), 'HND', 'Hindu', 1, now(), true, 0),
	(uuid_generate_v4(), 'BDH', 'Buddha', 1, now(), true, 0),
	(uuid_generate_v4(), 'OTH', 'Others', 1, now(), true, 0);

SELECT * FROM t_marital_status tms;
INSERT INTO t_marital_status (id, marital_code, marital_name, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), 'MRD', 'Married', 1, now(), true, 0),
	(uuid_generate_v4(), 'SNG', 'Single', 1, now(), true, 0);

SELECT * FROM t_person_type tpt;
INSERT INTO t_person_type (id, type_code, type_name, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), 'CND', 'Candidate', 1, now(), true, 0),
	(uuid_generate_v4(), 'EMP', 'Employee', 1, now(), true, 0);

SELECT * FROM t_candidate_profile tcp;
INSERT INTO t_candidate_profile (id, salutation, fullname, gender, experience, expected_salary, phone_number, mobile_number, nik, birth_date, birth_place, marital_status_id, religion_id, person_type_id, file_id, candidate_status_id, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), 'Mr.'	, 'Ganjar Sutrisno'	, 'Male'	, '1', '5000000'	, '08174563256', '08126354856', '35153135151515', '1995-05-07', 'Jakarta'	, '72eb9289-df4e-408b-b617-6f39e4649105', '457b3886-6c79-4be5-8fde-bbe4c02b22e2', '5fee0a8a-8eae-49a8-9efe-4b078410f1a5', 'ce1acdc2-d8e2-4dbd-9512-c79be1d189a4', 'd249f1eb-994f-4291-a268-26edbe6f64bf', 1, now(), true, 0),
	(uuid_generate_v4(), 'Mrs.'	, 'Ariana Pratiwi'	, 'Female'	, '3', '10000000'	, '08174563456', '08126309856', '35153135151987', '2001-08-08', 'Madiun'	, '72eb9289-df4e-408b-b617-6f39e4649105', '457b3886-6c79-4be5-8fde-bbe4c02b22e2', '5fee0a8a-8eae-49a8-9efe-4b078410f1a5', 'ce1acdc2-d8e2-4dbd-9512-c79be1d189a4', 'd249f1eb-994f-4291-a268-26edbe6f64bf', 1, now(), true, 0),
	(uuid_generate_v4(), 'Ms.'	, 'Putri Anggini'	, 'Female'	, '2', '7000000'	, '08176123456', '08102109856', '35789135151987', '2002-02-08', 'Tangerang'	, 'f6ee5ce8-7869-4282-9d3f-d69d7d255402', 'e50eaf58-a8ed-4b83-9512-75395e1374da', '5fee0a8a-8eae-49a8-9efe-4b078410f1a5', 'ce1acdc2-d8e2-4dbd-9512-c79be1d189a4', 'd249f1eb-994f-4291-a268-26edbe6f64bf', 1, now(), true, 0),
	(uuid_generate_v4(), 'Mr.'	, 'Robin Smith'		, 'Male'	, '1', '6000000'	, '08741123456', '08133309856', '35789555151987', '1999-09-01', 'Malang'	, 'f6ee5ce8-7869-4282-9d3f-d69d7d255402', 'fa4298cc-0faa-492e-a3f1-eb57b21a375c', '5fee0a8a-8eae-49a8-9efe-4b078410f1a5', 'ce1acdc2-d8e2-4dbd-9512-c79be1d189a4', 'd249f1eb-994f-4291-a268-26edbe6f64bf', 1, now(), true, 0),
	(uuid_generate_v4(), 'Mr.'	, 'Mario Simbiak'	, 'Male'	, '1', '8000000'	, '08789123456', '08162409856', '35789555100087', '1999-07-03', 'Surabaya'	, 'f6ee5ce8-7869-4282-9d3f-d69d7d255402', 'fa4298cc-0faa-492e-a3f1-eb57b21a375c', '5fee0a8a-8eae-49a8-9efe-4b078410f1a5', 'ce1acdc2-d8e2-4dbd-9512-c79be1d189a4', 'd249f1eb-994f-4291-a268-26edbe6f64bf', 1, now(), true, 0);

SELECT * FROM t_candidate_user tcu;
INSERT INTO t_candidate_user (id, user_email, user_password, profile_id, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), 'candidate1@email.com', 'jobportal', '8539b4c1-f3d9-47b8-918a-14615fb13755', 1, now(), true, 0),
	(uuid_generate_v4(), 'candidate2@email.com', 'jobportal', '59798613-3196-4232-bef9-7ddd139cea23', 1, now(), true, 0),
	(uuid_generate_v4(), 'candidate3@email.com', 'jobportal', '4ac7ddac-c31d-4fb9-b2e4-12eb7e156877', 1, now(), true, 0),
	(uuid_generate_v4(), 'candidate4@email.com', 'jobportal', '732ced9a-1764-409a-a39a-2dc10262fecc', 1, now(), true, 0),
	(uuid_generate_v4(), 'candidate5@email.com', 'jobportal', '77a3b3b5-b44c-48c4-9671-6dcd21775f7a', 1, now(), true, 0);
	
SELECT * FROM t_candidate_family tcf;
INSERT INTO t_candidate_family (id, fullname, relationship, degree_name, occupation, birth_date, birth_place, user_id, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), 'Lusiana Sutrisno'	, 'Istri'	, 'Sarjana (S1)', 'Manager'	, '1996-05-02', 'Jakarta', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0),
	(uuid_generate_v4(), 'Angga Sutrisno'	, 'Anak'	, 'Sarjana (S1)', 'Employee', '2001-06-03', 'Jakarta', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0),
	(uuid_generate_v4(), 'Aryon Sutrisno'	, 'Anak'	, 'SMA'			, 'Student'	, '2003-08-01', 'Kediri' , '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0);

SELECT * FROM t_candidate_address tca;
INSERT INTO t_candidate_address (id, address, residence_type, country, province, city, postal_code, user_id, created_by, created_at, is_active, ver) VALUES
	(uuid_generate_v4(), 'Jl. Menteng Atas No.21', 'Home', 'Indonesia', 'DKI Jakarta', 'Jakarta Selatan', '15115', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0);
	
SELECT * FROM t_candidate_skill tcs;
INSERT INTO t_candidate_skill (id, skill_name, user_id, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), 'Web Development'	, '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0),
	(uuid_generate_v4(), 'SQL Queries'		, '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0),
	(uuid_generate_v4(), 'Video Editing'	, '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0),
	(uuid_generate_v4(), 'Back-End Engineer', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0);

SELECT * FROM t_candidate_work_exp tcwe;
INSERT INTO t_candidate_work_exp (id, position_name, company_name, address, responsibility, reason_leave, last_salary, start_date, end_date, user_id, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), 'Full Stack Engineer', 'PT. Lawencon International', 'Pakuwon Tower, Jakarta', 'Optimize code and application for maximum speed and scalability', 'Not suitable with the salary', '7000000', '2019-01-01', '2019-05-30', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0),
	(uuid_generate_v4(), 'UI / UX', 'PT. Mentari International', 'Graha Tower, Jakarta', 'Designing User Interface for Mobile App', 'Finish the contract', '5000000', '2019-08-01', '2019-11-25', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0);

SELECT * FROM t_candidate_project_exp tcpe;
INSERT INTO t_candidate_project_exp (id, project_name, project_url, description, start_date, end_date, user_id, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), 'Assets Management System'		, 'www.github.com/assetsystem'		, 'Language: Java, TypeScript', '2019-01-05', '2019-04-05', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0),
	(uuid_generate_v4(), 'Ticket Management System'		, 'www.github.com/ticketsystem'		, 'Language: Java, TypeScript', '2019-05-05', '2019-08-05', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0),
	(uuid_generate_v4(), 'Learning Management System'	, 'www.github.com/learningsystem'	, 'Language: Java, TypeScript', '2019-08-05', '2019-10-05', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0);
	
SELECT * FROM t_candidate_training_exp tcte;
INSERT INTO t_candidate_training_exp (id, organization_name, training_name, description, start_date, end_date, user_id, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), 'PT. Google Indonesia'	, 'IT Support Training'	, 'Software & Hardware management skills', '2017-01-01', '2017-04-25', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0),
	(uuid_generate_v4(), 'PT. Dell Indonesia'	, 'UI / UX Training'	, 'Designing User Interface and creative', '2018-01-01', '2018-02-25', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0),
	(uuid_generate_v4(), 'PT. Asus Indonesia'	, 'Full Stack Training'	, 'Develop web application with Java Lan', '2018-06-01', '2018-12-25', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0);
	
SELECT * FROM t_candidate_education tce;
INSERT INTO t_candidate_education (id, degree_name, institution_name, majors, cgpa, start_year, end_year, user_id, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), 'Sarjana (S1)'	, 'Universitas Indonesia', 'Information Technology', 3.9, '2010-07-07', '2014-05-05', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0),
	(uuid_generate_v4(), 'Magister (S2)', 'Universitas Udayana', 'Information Technology', 3.7, '2015-07-07', '2017-04-06', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0);
	
SELECT * FROM t_candidate_language tcl;
INSERT INTO t_candidate_language (id, language_name, writing_rate, speaking_rate, listening_rate, user_id, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), 'English', '8', '7', '9', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0),
	(uuid_generate_v4(), 'Indonesia', '9', '7', '9', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0),
	(uuid_generate_v4(), 'Mandarin', '5', '6', '5', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0);

SELECT * FROM t_file_type tft;
INSERT INTO t_file_type (id, type_code, type_name, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), 'FCV', 'Curicullum Vitae', 1, now(), true, 0),
	(uuid_generate_v4(), 'FCC', 'Citizen Card', 1, now(), true, 0),
	(uuid_generate_v4(), 'FFC', 'Family Card', 1, now(), true, 0),
	(uuid_generate_v4(), 'FBC', 'Birth Card', 1, now(), true, 0),
	(uuid_generate_v4(), 'FTR', 'Transcript', 1, now(), true, 0);

SELECT * FROM t_file_type tft;
INSERT INTO t_file_type (id,type_code,type_name,created_by,created_at,updated_by,updated_at,is_active,ver) VALUES
	( uuid_generate_v4(),'FE-01','CURICULUM VITAE',1,NOW(),1,NOW(),TRUE,1),
	( uuid_generate_v4(),'FE-02','FAMILY CARD',1,NOW(),1,NOW(),TRUE,1),
	( uuid_generate_v4(),'FE-03','RESUME',1,NOW(),1,NOW(),TRUE,1),
	( uuid_generate_v4(),'FE-04','TRANSCRIPT',1,NOW(),1,NOW(),TRUE,1),
	( uuid_generate_v4(),'FE-05','CERTIFICATE',1,NOW(),1,NOW(),TRUE,1),
	( uuid_generate_v4(),'FE-06','CITIZEN CARD',1,NOW(),1,NOW(),TRUE,1),
	( uuid_generate_v4(),'FE-07','OTHERS',1,NOW(),1,NOW(),TRUE,1);
	
SELECT * FROM t_candidate_documents tcd;
INSERT INTO t_candidate_documents (id, doc_name, user_id, file_id, file_type_id, created_by, created_at, is_active, ver) VALUES
	(uuid_generate_v4(), 'CV_GanjarSutrisno', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', '806b3230-7e13-474f-8103-4c58a5bae715', 'fa3f81c1-4dd0-450b-97ec-40f89a914736', 1, now(), true, 0),
	(uuid_generate_v4(), 'KTP_GanjarSutrisno', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', '806b3230-7e13-474f-8103-4c58a5bae715', '6eb3977c-1ece-49db-bbff-cf5aff00db09', 1, now(), true, 0),
	(uuid_generate_v4(), 'KK_GanjarSutrisno', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', '806b3230-7e13-474f-8103-4c58a5bae715', '2f6ac514-a4ee-47a6-a7fa-2b65c44e3d5b', 1, now(), true, 0),
	(uuid_generate_v4(), 'TR_GanjarSutrisno', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', '806b3230-7e13-474f-8103-4c58a5bae715', '1dd81472-66de-4dd9-b796-e7630db27fb2', 1, now(), true, 0);

SELECT * FROM t_candidate_references tcr;
INSERT INTO t_candidate_references (id, fullname, relationship, occupation, phone_number, email, company, description, user_id, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), 'Angga Yulir', 'Uncle', 'General Manager', '08152223455', 'angga@email.com', 'PT. Lawencon International', 'My previous GM', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0),
	(uuid_generate_v4(), 'Rahmat Sutrisnno', 'Colleague', 'IT Supervisor', '08151230555', 'rahmat@email.com', 'PT. Mentari International', 'My previous Supervisor', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0),
	(uuid_generate_v4(), 'Angela Mins', 'Colleague', 'HR', '08845123455', 'angela@email.com', 'PT. Mentari International', 'My previous HR', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0);
	
SELECT * FROM t_employment_type tet;
INSERT INTO t_employment_type (id,employment_type_code,employment_type_name,created_by,created_at,updated_by,updated_at,is_active,ver) VALUES
	(uuid_generate_v4(),'ET-01','INTERN',1,NOW(),1,NOW(),TRUE,1),
	(uuid_generate_v4(),'ET-02','PART TIME',1,NOW(),1,NOW(),TRUE,1),
	(uuid_generate_v4(),'ET-03','CONTRACT',1,NOW(),1,NOW(),TRUE,1),
	(uuid_generate_v4(),'ET-04','FULL TIME',1,NOW(),1,NOW(),TRUE,1);

SELECT * FROM t_company tc;
INSERT INTO t_company (id, company_code, company_name, address, company_url, company_phone, photo_id, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), 'LWC', 'PT. Lawencon International', 'Pakuwon Tower, Jakarta', 'www.lawencon.com', '08151321554', '940f00b1-6e9b-4b00-8452-ce7c2ea47908', 1, now(), true, 0),
	(uuid_generate_v4(), 'SHP', 'PT. Shopee Indonesia', 'Pakuwon Tower, Jakarta', 'www.shopee.com', '08156541554', '940f00b1-6e9b-4b00-8452-ce7c2ea47908', 1, now(), true, 0),
	(uuid_generate_v4(), 'LWS', 'PT. Lawson International', 'Menteng Tower, Bandung', 'www.lawson.com', '08151378954', '940f00b1-6e9b-4b00-8452-ce7c2ea47908', 1, now(), true, 0),
	(uuid_generate_v4(), 'KBJ', 'PT. Kebinekaan Jaya', 'Graha Tower, Jakarta', 'www.bhineka.com', '08151300054', '940f00b1-6e9b-4b00-8452-ce7c2ea47908', 1, now(), true, 0)
	
SELECT * FROM t_job tj;
INSERT INTO t_job (id, job_code, job_name, company_id, start_date, end_date, description, expected_salary_min, expected_salary_max, employment_type_id, job_picture_id, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), 'FSD', 'Full Stack Developer', 'cf6a1139-2c88-41cc-872a-f5007f6ba81b', '2023-05-05', '2024-05-05', 'FSDeveloper Job Description', '5000000', '7000000', '78cec7e4-9e50-4c8e-ab3f-b166c6da58c3', '5ae09027-1793-4c15-baee-645be9821867', 1, now(), true, 0),
	(uuid_generate_v4(), 'JVD', 'Java Developer', 'cf6a1139-2c88-41cc-872a-f5007f6ba81b', '2023-05-05', '2024-05-05', 'Java Developer Job Description', '7000000', '10000000', '78cec7e4-9e50-4c8e-ab3f-b166c6da58c3', '5ae09027-1793-4c15-baee-645be9821867', 1, now(), true, 0),
	(uuid_generate_v4(), 'DBA', 'Database Administrator', 'cf6a1139-2c88-41cc-872a-f5007f6ba81b', '2023-05-05', '2024-05-05', 'DB Admin Job Description', '5000000', '10000000', '78cec7e4-9e50-4c8e-ab3f-b166c6da58c3', '5ae09027-1793-4c15-baee-645be9821867', 1, now(), true, 0),
	(uuid_generate_v4(), 'SLM', 'Sales Manager', 'a99c1b30-58c5-47f7-a715-bff40526577e', '2023-05-05', '2024-05-05', 'Sales Manager Job Description', '5000000', '7000000', '78cec7e4-9e50-4c8e-ab3f-b166c6da58c3', '5ae09027-1793-4c15-baee-645be9821867', 1, now(), true, 0),
	(uuid_generate_v4(), 'CSS', 'Customer Service', 'a99c1b30-58c5-47f7-a715-bff40526577e', '2023-05-05', '2024-05-05', 'CS Job Description', '6000000', '10000000', '78cec7e4-9e50-4c8e-ab3f-b166c6da58c3', '5ae09027-1793-4c15-baee-645be9821867', 1, now(), true, 0);
	
SELECT * FROM t_hiring_status ths;
INSERT INTO t_hiring_status (id, status_code, status_name, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), 'APL', 'Application', 1, now(), true, 0),
	(uuid_generate_v4(), 'ASE', 'Assesment', 1, now(), true, 0),
	(uuid_generate_v4(), 'ITV', 'Interview User', 1, now(), true, 0),
	(uuid_generate_v4(), 'MCU', 'Medical Checkup', 1, now(), true, 0),
	(uuid_generate_v4(), 'OFL', 'Offering Letter', 1, now(), true, 0),
	(uuid_generate_v4(), 'HRD', 'Hired', 1, now(), true, 0),
	(uuid_generate_v4(), 'RJC', 'Reject', 1, now(), true, 0);
	
SELECT * FROM t_applicant ta;
INSERT INTO t_applicant (id, applicant_code, job_id, applied_date, status_id, candidate_id, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), 'X23NB', '25de4c86-eadb-42e4-833c-8889a245625f', '2023-08-08', 'a588de44-842c-492f-88b2-474f57c07b41', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0),
	(uuid_generate_v4(), 'X24NB', '945833a1-c75f-4168-af1a-78bf3cb3a7ad', '2023-08-08', 'a588de44-842c-492f-88b2-474f57c07b41', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0),
	(uuid_generate_v4(), 'X25NB', '25de4c86-eadb-42e4-833c-8889a245625f', '2023-08-08', 'a588de44-842c-492f-88b2-474f57c07b41', '8e2cedb0-0d53-447f-8f43-98a9a9fdb1f1', 1, now(), true, 0);
	
SELECT * FROM t_saved_job tsj;
INSERT INTO t_saved_job (id, job_id, user_id, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), '51bffe33-f491-4475-9348-39790b35152f', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0),
	(uuid_generate_v4(), '7f73664a-89f6-410e-8e03-850fa7d2261c', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0),
	(uuid_generate_v4(), '9ec4c138-aa5f-4ac3-8465-9deb485cb5fd', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 1, now(), true, 0);

SELECT * FROM t_question tq;
INSERT INTO t_question (id, question_code, question_detail, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), 'Q1FSD', 'Which feature of OOP indicates code reusability?', 1, now(), true, 0),
	(uuid_generate_v4(), 'Q2FSD', 'When OOP concept did first came into picture?', 1, now(), true, 0),
	(uuid_generate_v4(), 'Q3FSD', 'Which was the first purely object oriented programming language developed?', 1, now(), true, 0);
	
SELECT * FROM t_question_option tqo;
INSERT INTO t_question_option (id, option_label, is_correct, question_id, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), 'A. Abstraction', false, 'fdd3df64-f885-4ec9-a71d-3e0a6e53d535', 1, now(), true, 0),
	(uuid_generate_v4(), 'B. Polymorphism', false, 'fdd3df64-f885-4ec9-a71d-3e0a6e53d535', 1, now(), true, 0),
	(uuid_generate_v4(), 'C. Encapsulation', false, 'fdd3df64-f885-4ec9-a71d-3e0a6e53d535', 1, now(), true, 0),
	(uuid_generate_v4(), 'D. Inheritance', true, 'fdd3df64-f885-4ec9-a71d-3e0a6e53d535', 1, now(), true, 0),
	(uuid_generate_v4(), 'A. 1980', false, 'e87077ca-00c3-423f-bdc5-e10edae75373', 1, now(), true, 0),
	(uuid_generate_v4(), 'B. 1995', false, 'e87077ca-00c3-423f-bdc5-e10edae75373', 1, now(), true, 0),
	(uuid_generate_v4(), 'C. 1970', true, 'e87077ca-00c3-423f-bdc5-e10edae75373', 1, now(), true, 0),
	(uuid_generate_v4(), 'D. 1993', false, 'e87077ca-00c3-423f-bdc5-e10edae75373', 1, now(), true, 0),
	(uuid_generate_v4(), 'A. Kotlin', false, 'a1d9d1c0-756b-40e5-b699-53b7add78f12', 1, now(), true, 0),
	(uuid_generate_v4(), 'B. SmallTalk', true, 'a1d9d1c0-756b-40e5-b699-53b7add78f12', 1, now(), true, 0),
	(uuid_generate_v4(), 'C. Java', false, 'a1d9d1c0-756b-40e5-b699-53b7add78f12', 1, now(), true, 0),
	(uuid_generate_v4(), 'D. C++', false, 'a1d9d1c0-756b-40e5-b699-53b7add78f12', 1, now(), true, 0);

SELECT * FROM t_question_answer tqa;
INSERT INTO t_question_answer (id, option_id, candidate_id, question_id, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), '6760e40b-7c4d-446b-b60f-99ba02e58928', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 'fdd3df64-f885-4ec9-a71d-3e0a6e53d535', 1, now(), true, 0),
	(uuid_generate_v4(), '1b3f4c30-a74a-4f5d-9e67-531ea61c86a3', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 'e87077ca-00c3-423f-bdc5-e10edae75373', 1, now(), true, 0),
	(uuid_generate_v4(), '26a339d8-bf35-4472-b12e-411d5522990b', '6e7ac1d7-0f66-4849-9449-57af4a70e2e8', 'a1d9d1c0-756b-40e5-b699-53b7add78f12', 1, now(), true, 0);

SELECT * FROM t_assigned_job_question tajq;
INSERT INTO t_assigned_job_question (id, job_id, question_id, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), '25de4c86-eadb-42e4-833c-8889a245625f', 'fdd3df64-f885-4ec9-a71d-3e0a6e53d535', 1, now(), true, 0),
	(uuid_generate_v4(), '25de4c86-eadb-42e4-833c-8889a245625f', 'e87077ca-00c3-423f-bdc5-e10edae75373', 1, now(), true, 0),
	(uuid_generate_v4(), '25de4c86-eadb-42e4-833c-8889a245625f', 'a1d9d1c0-756b-40e5-b699-53b7add78f12', 1, now(), true, 0);

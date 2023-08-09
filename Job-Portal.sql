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
	writing_rate VARCHAR(5) NOT NULL,
	speaking_rate VARCHAR(5) NOT NULL,
	listening_rate VARCHAR(5) NOT NULL,
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
	status_name varchar(20) NOT NULL
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
	question_id VARCHAR(36) NOT NULL
);

ALTER TABLE t_assigned_job_question ADD CONSTRAINT t_assigned_job_question_pk 
	PRIMARY KEY(id);
ALTER TABLE t_assigned_job_question ADD CONSTRAINT t_assigned_job_question_job_fk
	FOREIGN KEY(job_id)
	REFERENCES t_job(id);
ALTER TABLE t_assigned_job_question ADD CONSTRAINT t_assigned_job_question_question_fk
	FOREIGN KEY(question_id)
	REFERENCES t_question(id);
	
INSERT INTO t_file (id, filename, file_extension, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(),'DummyFile', '.png', 1, now(), true, 0);

INSERT INTO t_candidate_status (id, status_code, status_name, created_by, created_at, is_active, ver) VALUES	
	(uuid_generate_v4(), 'CS-01', 'Active', 1, now(), true, 0),
	(uuid_generate_v4(), 'CS-02', 'On Process', 1, now(), true, 0),
	(uuid_generate_v4(), 'CS-03', 'Blacklist', 1, now(), true, 0);

INSERT INTO t_religion (id, religion_code, religion_name, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), 'ISL', 'Islam', 1, now(), true, 0),
	(uuid_generate_v4(), 'CHR', 'Christian', 1, now(), true, 0),
	(uuid_generate_v4(), 'CHT', 'Catholic', 1, now(), true, 0),
	(uuid_generate_v4(), 'HND', 'Hindu', 1, now(), true, 0),
	(uuid_generate_v4(), 'BDH', 'Buddha', 1, now(), true, 0),
	(uuid_generate_v4(), 'OTH', 'Others', 1, now(), true, 0);

INSERT INTO t_marital_status (id, marital_code, marital_name, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), 'MRD', 'Married', 1, now(), true, 0),
	(uuid_generate_v4(), 'SNG', 'Single', 1, now(), true, 0);

INSERT INTO t_person_type (id, type_code, type_name, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), 'CND', 'Candidate', 1, now(), true, 0),
	(uuid_generate_v4(), 'EMP', 'Employee', 1, now(), true, 0);

INSERT INTO t_candidate_profile (id, salutation, fullname, gender, experience, expected_salary, phone_number, mobile_number, nik, birth_date, birth_place, marital_status_id, religion_id, person_type_id, file_id, created_by, created_at, is_active, ver) VALUES 
	(uuid_generate_v4(), 'Mr.', 'Ganjar Sutrisno', 'Male', '1', '5000000', '08174563256', '08126354856', '35153135151515', now(), 'Jakarta', 1, 1, 1, 1, 1, now(), true, 0);

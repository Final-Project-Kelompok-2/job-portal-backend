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
--DROP TABLE IF EXISTS t_blacklist;
--DROP TABLE IF EXISTS t_mcu;
--DROP TABLE IF EXISTS t_assigned_job_question;
--DROP TABLE IF EXISTS t_question_answer;
--DROP TABLE IF EXISTS t_question_detail;
--DROP TABLE IF EXISTS t_question_option;
--DROP TABLE IF EXISTS t_question;
--DROP TABLE IF EXISTS t_assesment;
--DROP TABLE IF EXISTS t_interview;
--DROP TABLE IF EXISTS t_review_detail;
--DROP TABLE IF EXISTS t_review;
--DROP TABLE IF EXISTS t_offering_letter;
--DROP TABLE IF EXISTS t_hired;
--DROP TABLE IF EXISTS t_owned_benefit;
--DROP TABLE IF EXISTS t_benefit;
--DROP TABLE IF EXISTS t_applicant;
--DROP TABLE IF EXISTS t_hiring_status;
--DROP TABLE IF EXISTS t_job;
--DROP TABLE IF EXISTS t_company;
--DROP TABLE IF EXISTS t_employment_type;
--DROP TABLE IF EXISTS t_file_type;
--DROP TABLE IF EXISTS t_user;
--DROP TABLE IF EXISTS t_profile;
--DROP TABLE IF EXISTS t_role;
--DROP TABLE IF EXISTS t_candidate_user;
--DROP TABLE IF EXISTS t_candidate_profile;
--DROP TABLE IF EXISTS t_candidate_status;
--DROP TABLE IF EXISTS t_person_type;
--DROP TABLE IF EXISTS t_marital_status;
--DROP TABLE IF EXISTS t_religion;
--DROP TABLE IF EXISTS t_file;
--DROP TABLE IF EXISTS t_file_type;
--DROP TABLE IF EXISTS t_person_type;

CREATE TABLE t_file (
	id varchar(36) NOT NULL,
	file_name text NOT NULL,
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
	id varchar(36) NOT NULL,
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
	UNIQUE (status_code);

CREATE TABLE t_religion (
	id varchar(36) NOT NULL,
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
	id varchar(36) NOT NULL,
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
	id varchar(36) NOT NULL,
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
	id varchar(36) NOT NULL,
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
	marital_status_id varchar(36) NOT NULL,
	religion_id varchar(36) NOT NULL,
	person_type_id varchar(36) NOT NULL,
	file_id varchar(36) NOT NULL,
	candidate_status_id varchar(36) NOT NULL,
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
	id varchar(36) NOT NULL,
	user_email VARCHAR(50) NOT NULL,
	user_password TEXT NOT NULL,
	profile_id varchar(36) NOT NULL,
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
	id varchar(36) NOT NULL,
	fullname VARCHAR(50) NOT NULL,
	relationship VARCHAR(10) NOT NULL,
	degree_id varchar(36) NOT NULL,
	occupation VARCHAR(50) NOT NULL,
	birth_date date NOT NULL,
	birth_place VARCHAR(20) NOT NULL,
	user_id varchar(36) NOT NULL,
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
	id varchar(36) NOT NULL,
	address TEXT NOT NULL,
	residence_type VARCHAR(10) NOT NULL,
	country VARCHAR(20) NOT NULL,
	province VARCHAR(20) NOT NULL,
	city VARCHAR(20) NOT NULL,
	postal_code VARCHAR(10) NOT NULL,
	user_id varchar(36) NOT NULL,
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
	id varchar(36) NOT NULL,
	skill_name text NOT NULL,
	user_id varchar(36) NOT NULL,
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
	id varchar(36) NOT NULL,
	position_name VARCHAR(30) NOT NULL,
	company_name VARCHAR(30) NOT NULL,
	address TEXT NOT NULL,
	responsibility TEXT NOT NULL,
	reason_leave TEXT NOT NULL,
	last_salary float NOT NULL,
	start_date timestamp NOT NULL,
	end_date timestamp NOT NULL,
	user_id varchar(36) NOT NULL,
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
	id varchar(36) NOT NULL,
	project_name VARCHAR(30) NOT NULL,
	project_url TEXT,
	description TEXT NOT NULL,
	start_date timestamp NOT NULL,
	end_date timestamp NOT NULL,
	user_id varchar(36) NOT NULL,
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
	id varchar(36) NOT NULL,
	organization_name VARCHAR(20) NOT NULL,
	training_name VARCHAR(20) NOT NULL,
	description TEXT NOT NULL,
	start_date timestamp NOT NULL,
	end_date timestamp NOT NULL,
	user_id varchar(36) NOT NULL,
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
	id varchar(36) NOT NULL,
	degree_name VARCHAR(50) NOT NULL,
	institution_name VARCHAR(50) NOT NULL,
	majors VARCHAR(50) NOT NULL,
	cGPA float NOT NULL,
	start_year date NOT NULL,
	end_year date NOT NULL,
	user_id varchar(36) NOT NULL,
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
	id varchar(36) NOT NULL,
	language_name VARCHAR(30) NOT NULL,
	writing_rate VARCHAR(5) NOT NULL,
	speaking_rate VARCHAR(5) NOT NULL,
	listening_rate VARCHAR(5) NOT NULL,
	user_id varchar(36) NOT NULL,
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
	id varchar(36) NOT NULL,
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
	id varchar(36) NOT NULL,
	doc_name VARCHAR(30) NOT NULL,
	user_id varchar(36) NOT NULL,
	file_id varchar(36) NOT NULL,
	file_type_id varchar(36) NOT NULL,
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
	id varchar(36) NOT NULL,
	fullname VARCHAR(50) NOT NULL,
	relationship VARCHAR(10) NOT NULL,
	occupation VARCHAR(20) NOT NULL,
	phone_number VARCHAR(20) NOT NULL,
	email VARCHAR(50) NOT NULL,
	company VARCHAR(50) NOT NULL,
	description TEXT NOT NULL,
	user_id varchar(36) NOT NULL,
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
	id varchar(36) NOT NULL,
	employment_type_code varchar(5) NOT NULL,
	employment_type_name varchar(20) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL

);
ALTER TABLE t_employment_type ADD CONSTRAINT t_employment_type_pk PRIMARY KEY(id);
ALTER TABLE t_employment_type ADD CONSTRAINT employment_type_code_bk
	UNIQUE (employment_type_code);





CREATE TABLE t_role(
	id varchar(36) NOT NULL,
	role_code varchar(5) NOT NULL,
	role_name varchar(10) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_role ADD CONSTRAINT t_role_pk PRIMARY KEY(id);
ALTER TABLE t_role ADD CONSTRAINT t_role_code_bk
UNIQUE (role_code);

CREATE TABLE t_profile(
	id varchar(36) NOT NULL,
	full_name varchar(50) NOT NULL,
	photo_id varchar(36) NOT NULL,
	phone_number varchar(13) NOT NULL,
	address text NOT NULL,
	person_type_id varchar(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_profile ADD CONSTRAINT t_profile_pk PRIMARY KEY(id);
ALTER TABLE t_profile ADD CONSTRAINT t_profile_photo_fk 
FOREIGN KEY(photo_id)
REFERENCES t_file(id);
ALTER TABLE t_profile ADD CONSTRAINT t_person_type_fk
FOREIGN KEY(person_type_id)
REFERENCES t_person_type(id);



CREATE TABLE t_user(
	id varchar(36) NOT NULL,
	user_email varchar(50) NOT NULL,
	user_password text NOT NULL,
	profile_id varchar(36) NOT NULL,
	role_id varchar(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
	
);

/*USER*/
ALTER TABLE t_user ADD CONSTRAINT t_user_pk PRIMARY KEY(id);
ALTER TABLE t_user ADD CONSTRAINT t_profile_fk
FOREIGN KEY(profile_id)
REFERENCES t_profile(id);
ALTER TABLE t_user ADD CONSTRAINT t_role_fk
FOREIGN KEY(role_id)
REFERENCES t_role(id);

CREATE TABLE t_company(
	id varchar(36) NOT NULL,
	company_code varchar(5) NOT NULL,
	company_name varchar(30) NOT NULL,
	address text NOT NULL,
	company_url text,
	company_phone varchar(15) NOT NULL,
	photo_id varchar(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_company ADD CONSTRAINT t_company_pk PRIMARY KEY(id);
ALTER TABLE t_company ADD CONSTRAINT t_company_picture_fk 
FOREIGN KEY(photo_id)
REFERENCES t_file(id);
ALTER TABLE t_company ADD CONSTRAINT t_company_bk
UNIQUE (company_code)




CREATE TABLE t_job(
	id varchar(36) NOT NULL,
	job_code varchar(5) NOT NULL,
	job_name varchar(30) NOT NULL,
	company_id varchar(36) NOT NULL,
	hr_id varchar(36) NOT NULL,
	pic_id varchar(36) NOT NULL,
	start_date date NOT NULL,
	end_date date NOT NULL,
	description text NOT NULL,
	expected_salary_min int,
	expected_salary_max int,
	employment_type_id varchar(36) NOT NULL,
	job_picture_id varchar(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_job ADD CONSTRAINT t_job_pk PRIMARY KEY(id);

ALTER TABLE t_job ADD CONSTRAINT t_job_bk
UNIQUE (job_code);

ALTER TABLE t_job ADD CONSTRAINT t_company_fk 
FOREIGN KEY(company_id)
REFERENCES t_company(id);

ALTER TABLE t_job ADD CONSTRAINT t_hr_fk
FOREIGN KEY(hr_id)
REFERENCES t_user(id);

ALTER TABLE t_job ADD CONSTRAINT t_pic_fk
FOREIGN KEY(pic_id)
REFERENCES t_user(id);

ALTER TABLE t_job ADD CONSTRAINT t_employment_type_fk
FOREIGN KEY(employment_type_id)
REFERENCES t_employment_type(id);

ALTER TABLE t_job ADD CONSTRAINT t_job_picture_fk
FOREIGN KEY(job_picture_id)
REFERENCES t_file(id);

CREATE TABLE t_hiring_status(
	id varchar(36) NOT NULL,
	status_code varchar(5) NOT NULL,
	status_name varchar(20) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_hiring_status ADD CONSTRAINT t_hiring_status_pk PRIMARY KEY(id);
ALTER TABLE t_hiring_status ADD CONSTRAINT t_hiring_status_bk
UNIQUE(status_code);
CREATE TABLE t_applicant(
	id varchar(36) NOT NULL,
	applicant_code varchar(5) NOT NULL,
	job_id varchar(36) NOT NULL,
	applied_date timestamp NOT NULL,
	status_id varchar(36) NOT NULL,
	candidate_id varchar(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_applicant ADD CONSTRAINT t_applicant_pk PRIMARY KEY(id);
ALTER TABLE t_applicant ADD CONSTRAINT t_applicant_job_fk 
FOREIGN KEY(job_id)
REFERENCES t_job(id);
ALTER TABLE t_applicant ADD CONSTRAINT t_hiring_status_fk 
FOREIGN KEY(status_id)
REFERENCES t_hiring_status(id);
ALTER TABLE t_applicant ADD CONSTRAINT t_applicant_bk
UNIQUE (applicant_code);



CREATE TABLE t_question(
	id varchar(36) NOT NULL,
	question_code varchar(5) NOT NULL,
	question_detail text NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_question ADD CONSTRAINT t_question_pk PRIMARY KEY(id);
ALTER TABLE t_question ADD CONSTRAINT t_question_bk UNIQUE(question_code);



CREATE TABLE t_question_option(
	id varchar(36) NOT NULL,
	option_label varchar(20) NOT NULL,
	is_correct boolean NOT NULL,
	question_id varchar(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_question_option ADD CONSTRAINT t_question_option_pk PRIMARY KEY(id);
ALTER TABLE t_question_option ADD CONSTRAINT t_questionoption_fk
FOREIGN KEY(question_id)
REFERENCES t_question(id);


CREATE TABLE t_question_answer(
	id varchar(36) NOT NULL,
	option_id varchar(36) NOT NULL,
	candidate_id varchar(36) NOT NULL,
	question_id varchar(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_question_answer ADD CONSTRAINT t_question_answer_pk PRIMARY KEY(id);

ALTER TABLE t_question_answer ADD CONSTRAINT t_option_fk
FOREIGN KEY(option_id)
REFERENCES t_question_option(id);

ALTER TABLE t_question_answer ADD CONSTRAINT t_candidate_fk
FOREIGN KEY(candidate_id)
REFERENCES t_candidate_user(id);

CREATE TABLE t_assigned_job_question(
	id varchar(36) NOT NULL,
	job_id varchar(36) NOT NULL,
	question_id varchar(36) NOT NULL
);

ALTER TABLE t_assigned_job_question ADD CONSTRAINT t_assigned_job_question_pk PRIMARY KEY(id);
ALTER TABLE t_assigned_job_question ADD CONSTRAINT t_assigned_job_question_job_fk
FOREIGN KEY(job_id)
REFERENCES t_job(id);
ALTER TABLE t_assigned_job_question ADD CONSTRAINT t_assigned_job_question_question_fk
FOREIGN KEY(question_id)
REFERENCES t_question(id);

CREATE TABLE t_review(
	id varchar(36) NOT NULL,
	notes text,
	score float,
	applicant_id varchar(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL

);
ALTER TABLE t_review ADD CONSTRAINT t_review_pk PRIMARY KEY(id);

CREATE TABLE t_review_detail(
	id varchar(36) NOT NULL,
	review_id varchar(36) NOT NULL,
	question_id varchar(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
); 
ALTER TABLE t_review_detail ADD CONSTRAINT t_review_detail_fk PRIMARY KEY(id);
ALTER TABLE t_review_detail ADD CONSTRAINT t_review_id_fk 
FOREIGN KEY(review_id)
REFERENCES t_review(id);




CREATE TABLE t_assesment(
	id varchar(36) NOT NULL,
	assesment_date timestamp NOT NULL,
	assesment_location varchar(50) NOT NULL,
	applicant_id varchar(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_assesment ADD CONSTRAINT t_assesment_pk PRIMARY KEY(id);
ALTER TABLE t_assesment ADD CONSTRAINT t_applicant_fk 
FOREIGN KEY(applicant_id)
REFERENCES t_applicant(id);

CREATE TABLE t_interview(
	id varchar(36) NOT NULL,
	interview_date timestamp NOT NULL,
	interview_location varchar(50) NOT NULL,
	applicant_id varchar(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_interview ADD CONSTRAINT t_interview_pk PRIMARY KEY(id);
ALTER TABLE t_interview ADD CONSTRAINT t_interview_applicant_fk
FOREIGN KEY(applicant_id)
REFERENCES t_applicant(id);


CREATE TABLE t_mcu(
	id varchar(36) NOT NULL,
	file_id varchar(36) NOT NULL,
	applicant_id varchar(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_mcu ADD CONSTRAINT t_mcu_pk PRIMARY KEY (id);
ALTER TABLE t_mcu ADD CONSTRAINT t_mcu_file_fk 
FOREIGN KEY (file_id)
REFERENCES t_file(id);

ALTER TABLE t_mcu  ADD CONSTRAINT t_mcu_applicant_fk
FOREIGN KEY(applicant_id)
REFERENCES t_applicant(id);


CREATE TABLE t_benefit(
	id varchar(36) NOT NULL,
	benefit_code varchar(5) NOT NULL,
	benefit_name varchar(20) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_benefit ADD CONSTRAINT t_benefit_pk PRIMARY KEY(id);
ALTER TABLE t_benefit ADD CONSTRAINT t_benefit_bk UNIQUE(benefit_code);

CREATE TABLE t_owned_benefit(
	id varchar(36) NOT NULL,
	benefit_id varchar(36) NOT NULL,
	job_id varchar(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_owned_benefit ADD CONSTRAINT t_owned_benefit_pk PRIMARY KEY(id);
ALTER TABLE t_owned_benefit ADD CONSTRAINT t_owned_benefit_benefit_fk 
FOREIGN KEY(benefit_id)
REFERENCES t_benefit(id);
ALTER TABLE t_owned_benefit ADD CONSTRAINT t_owned_benefit_job_fk 
FOREIGN KEY(job_id)
REFERENCES t_job(id);

CREATE TABLE t_offering_letter(
	id varchar(36) NOT NULL,
	location varchar(50) NOT NULL,
	salary int ,
	benefit_id varchar(36) NOT NULL,
	applicant_id varchar(36) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_offering_letter ADD CONSTRAINT t_offering_letter_pk PRIMARY KEY(id);

ALTER TABLE t_offering_letter ADD CONSTRAINT t_offering_letter_benefit_fk 
FOREIGN KEY(benefit_id)
REFERENCES t_benefit(id);

ALTER TABLE t_offering_letter ADD CONSTRAINT t_offering_letter_applicant_fk
FOREIGN KEY(applicant_id)
REFERENCES t_applicant(id);

CREATE TABLE t_hired(
	id varchar(36) NOT NULL,
	applicant_id varchar(36) NOT NULL,
	start_date timestamp NOT NULL,
	end_date timestamp,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_hired ADD CONSTRAINT t_hired_pk PRIMARY KEY(id);
ALTER TABLE t_hired ADD CONSTRAINT t_hired_applicant_fk
FOREIGN KEY(applicant_id)
REFERENCES t_applicant(id);

CREATE TABLE t_blacklist(
	id varchar(36) NOT NULL,
	email varchar(50) NOT NULL,
	notes text NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_blacklist  ADD CONSTRAINT t_blacklist_pk PRIMARY KEY(id);


INSERT INTO t_role (id,role_code,role_name,created_by,created_at,updated_by,updated_at,is_active,ver)
VALUES
( uuid_generate_v4(),'R-001','ADMIN',1,NOW(),1,NOW(),TRUE,1),
( uuid_generate_v4(),'R-002','HR',1,NOW(),1,NOW(),TRUE,1),
( uuid_generate_v4(),'R-003','CANDIDATE',1,NOW(),1,NOW(),TRUE,1),
( uuid_generate_v4(),'R-004','PIC',1,NOW(),1,NOW(),TRUE,1);


INSERT INTO t_employment_type (id,employment_type_code,employment_type_name,created_by,created_at,updated_by,updated_at,is_active,ver)
VALUES
( uuid_generate_v4(),'ET-01','INTERN',1,NOW(),1,NOW(),TRUE,1),
( uuid_generate_v4(),'ET-02','PART TIME',1,NOW(),1,NOW(),TRUE,1),
( uuid_generate_v4(),'ET-03','CONTRACT',1,NOW(),1,NOW(),TRUE,1),
( uuid_generate_v4(),'ET-04','FULL TIME',1,NOW(),1,NOW(),TRUE,1);

INSERT INTO t_file_type (id,type_code,type_name,created_by,created_at,updated_by,updated_at,is_active,ver)
VALUES
( uuid_generate_v4(),'FE-01','CURICULUM VITAE',1,NOW(),1,NOW(),TRUE,1),
( uuid_generate_v4(),'FE-02','FAMILY CARD',1,NOW(),1,NOW(),TRUE,1),
( uuid_generate_v4(),'FE-03','RESUME',1,NOW(),1,NOW(),TRUE,1),
( uuid_generate_v4(),'FE-04','TRANSCRIPT',1,NOW(),1,NOW(),TRUE,1),
( uuid_generate_v4(),'FE-05','CERTIFICATE',1,NOW(),1,NOW(),TRUE,1),
( uuid_generate_v4(),'FE-06','CITIZEN CARD',1,NOW(),1,NOW(),TRUE,1),
( uuid_generate_v4(),'FE-07','OTHERS',1,NOW(),1,NOW(),TRUE,1);

INSERT INTO t_person_type (id,type_code,type_name,created_by,created_at,updated_by,updated_at,is_active,ver)
VALUES
( uuid_generate_v4(),'PT-01','CANDIDATE',1,NOW(),1,NOW(),TRUE,1),
( uuid_generate_v4(),'PT-02','EMPLOYEE',1,NOW(),1,NOW(),TRUE,1);
insert into t_file(id,file_name,file_extension,created_by,created_at,updated_by,updated_at,is_active,ver)
values 
(uuid_generate_v4(),'asdasdasdd','jpg',1,NOW(),1,NOW(),TRUE,1);
insert into t_profile (id,full_name,photo_id,phone_number,address,person_type_id,created_by,created_at,updated_by,updated_at,is_active,ver)
values 
(uuid_generate_v4(),'ADMIN','93744fdd-8967-46fc-bb45-7527a189d864','10298301','BEKASI','bcc4d4fa-5e2a-4c7f-bb82-68dd0d4c5052',1,NOW(),1,NOW(),TRUE,1);
insert into t_user (id,user_email,user_password,profile_id,role_id,created_by,created_at,updated_by,updated_at,is_active,ver)
VALUES
( uuid_generate_v4(),'ADMIN@GMAIL.COM','123','98cf70ef-0ffe-4be1-8407-0c8840eab6e7','44c40380-4f30-4fbd-8f78-9e1b43c68334',1,NOW(),1,NOW(),TRUE,1);


SELECT uuid_generate_v4();
 CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
--job, company, employment type, applicant

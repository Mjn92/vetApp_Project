
-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS mydb ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS mydb ;
USE mydb ;

-- -----------------------------------------------------
-- Table `mydb`.`vet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS vet ;

CREATE TABLE IF NOT EXISTS vet (
  vet_id INT            PRIMARY KEY		AUTO_INCREMENT,
  vet_name VARCHAR(50)    UNIQUE	not null
  );


-- -----------------------------------------------------
-- Table `mydb`.`customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS customer ;

CREATE TABLE IF NOT EXISTS customer (
  customer_id INT primary KEY	AUTO_INCREMENT,
  custormer_name VARCHAR(45) NOT NULL unique
  );


-- -----------------------------------------------------
-- Table `mydb`.`pet info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS pet_info ;

CREATE TABLE IF NOT EXISTS pet_info (
  pet_id INT primary KEY	AUTO_INCREMENT,
  pet_name VARCHAR(45) ,
  pet_speices VARCHAR(45) ,
  pet_type varchar(45) ,
  pet_age INT ,
  pet_weight INT ,
  customer_id INT,
  INDEX fk_pet_info_customer1_idx (customer_id ASC),
  CONSTRAINT fk_pet_info_customer
    FOREIGN KEY (customer_id)
    REFERENCES mydb.customer (customer_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`calander appointment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS calander_appointment ;

CREATE TABLE IF NOT EXISTS calander_appointment  (
  calander_id INT NOT NULL	AUTO_INCREMENT,
  pet_visit_date DATE not NULL,
  pet_vist_time TIME NOT NULL,
  customer_id INT NOT NULL,
  pet_id INT NOT NULL,
  PRIMARY KEY (calander_id, customer_id, pet_id),
  UNIQUE INDEX calander_id_UNIQUE (calander_id ASC) ,
  INDEX fk_calander_appointment_cus_idx (customer_id ASC) ,
  INDEX fk_calander_appointment_pet_info1_idx (pet_id ASC) ,
  CONSTRAINT fk_calander_appointment_customer
    FOREIGN KEY (customer_id)
    REFERENCES mydb.customer (customer_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_calander_appointment_pet_info
    FOREIGN KEY (pet_id)
    REFERENCES mydb.pet_info (pet_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)


-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `payments` DEFAULT CHARACTER SET utf8 ;
USE `payments` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payments`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `role` ENUM('user', 'admin') NOT NULL,
  `login` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `payments`.`credit_request` (
  `credit_request_id` INT NOT NULL AUTO_INCREMENT,
  `money_amount` BIGINT NOT NULL,
  `date` TIMESTAMP NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`credit_request_id`),
  INDEX `fk_credit_request_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_credit_request_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `payments`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payments`.`account` (
  `account_id` INT NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(45) NOT NULL,
  `balance` BIGINT NOT NULL,
  `validity_date` TIMESTAMP NOT NULL,
  `rate` DECIMAL(5,4) NOT NULL,
  `accrued_interest` DECIMAL(7,4) NOT NULL,
  `limit` BIGINT NOT NULL,
  `account_type` ENUM('CHECKING', 'CREDIT', 'DEPOSIT') NOT NULL,
  `creation_date` TIMESTAMP NOT NULL,
  PRIMARY KEY (`account_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`operation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payments`.`operation` (
  `operation_id` INT NOT NULL AUTO_INCREMENT,
  `recipient` VARCHAR(45) NOT NULL,
  `operation_type` ENUM('TRANSFER', 'BILLS_PAYMENT', 'LOAN_PAYMENT') NOT NULL,
  `account_id` INT NOT NULL,
  `date` TIMESTAMP NOT NULL,
  PRIMARY KEY (`operation_id`, `account_id`),
  INDEX `fk_operation_account1_idx` (`account_id` ASC),
  CONSTRAINT `fk_operation_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `payments`.`account` (`account_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`user_has_account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payments`.`user_has_account` (
  `user_id` INT NOT NULL,
  `account_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `account_id`),
  INDEX `fk_user_has_account_account1_idx` (`account_id` ASC),
  INDEX `fk_user_has_account_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_account_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `payments`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_account_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `payments`.`account` (`account_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

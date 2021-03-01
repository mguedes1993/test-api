-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema test
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `test` ;

-- -----------------------------------------------------
-- Schema test
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `test` DEFAULT CHARACTER SET utf8 ;
USE `test` ;

-- -----------------------------------------------------
-- Table `test`.`TB_BRANDS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test`.`TB_BRANDS` (
  `ID` BIGINT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(200) NOT NULL,
  `CREATED` TIMESTAMP NOT NULL,
  `UPDATED` TIMESTAMP NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `UDX_BRANDS_NAME` (`NAME` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test`.`TB_MODELS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test`.`TB_MODELS` (
  `ID` BIGINT NOT NULL AUTO_INCREMENT,
  `BRAND_ID` BIGINT NOT NULL,
  `NAME` VARCHAR(200) NOT NULL,
  `CREATED` TIMESTAMP NOT NULL,
  `UPDATED` TIMESTAMP NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `UDX_MODELS_NAME` (`NAME` ASC) VISIBLE,
  INDEX `IDX_MODELS_BRAND` (`BRAND_ID` ASC) VISIBLE,
  CONSTRAINT `FK_MODELS_BRANDS`
    FOREIGN KEY (`BRAND_ID`)
    REFERENCES `test`.`TB_BRANDS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test`.`TB_VEHICLES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test`.`TB_VEHICLES` (
  `ID` BIGINT NOT NULL AUTO_INCREMENT,
  `MODEL_ID` BIGINT NOT NULL,
  `YEAR` INT NOT NULL,
  `SOLD` BIT NULL,
  `DESCRIPTION` TEXT NULL,
  `CREATED` TIMESTAMP NOT NULL,
  `UPDATED` TIMESTAMP NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `IDX_VEHICLES_MODEL` (`MODEL_ID` ASC) INVISIBLE,
  CONSTRAINT `FK_VEHICLES_MODELS`
    FOREIGN KEY (`MODEL_ID`)
    REFERENCES `test`.`TB_MODELS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

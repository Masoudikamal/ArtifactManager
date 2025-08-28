DROP DATABASE IF EXISTS Funn;
CREATE DATABASE Funn;
USE Funn;

CREATE TABLE Person (
                        Id INT PRIMARY KEY,
                        Navn VARCHAR(100),
                        Tlf VARCHAR(15),
                        E_post VARCHAR(100)
);

CREATE TABLE Museum (
                        Id INT PRIMARY KEY,
                        Navn VARCHAR(100),
                        Sted VARCHAR(100)
);

CREATE TABLE Funn (
                      Id INT PRIMARY KEY,
                      Funnsted VARCHAR(100),
                      Finner_id INT,
                      Funntidspunkt DATE,
                      Antatt_AArstall INT,
                      Museum_id INT,
                      FOREIGN KEY (Museum_id) REFERENCES Museum(Id)
);

CREATE TABLE Mynt (
                      Id INT PRIMARY KEY,
                      Funnsted VARCHAR(100),
                      Finner_id INT,
                      Funntidspunkt DATE,
                      Antatt_AArstall INT,
                      Museum_id INT,
                      Diameter INT,
                      Metall VARCHAR(50),
                      FOREIGN KEY (Museum_id) REFERENCES Museum(Id)
);

CREATE TABLE Smykke (
                        Id INT PRIMARY KEY,
                        Funnsted VARCHAR(100),
                        Finner_id INT,
                        Funntidspunkt DATE,
                        Antatt_AArstall INT,
                        Museum_id INT,
                        Type VARCHAR(50),
                        Verdiestimat INT,
                        Filnavn VARCHAR(100),
                        FOREIGN KEY (Museum_id) REFERENCES Museum(Id)
);

CREATE TABLE Vaapen (
                        Id INT PRIMARY KEY,
                        Funnsted VARCHAR(100),
                        Finner_id INT,
                        Funntidspunkt DATE,
                        Antatt_AArstall INT,
                        Museum_id INT,
                        Type VARCHAR(50),
                        Materiale VARCHAR(50),
                        Vekt INT,
                        FOREIGN KEY (Museum_id) REFERENCES Museum(Id)
);

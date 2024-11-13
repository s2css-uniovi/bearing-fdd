create database PreLoadedDatasets;
USE PreLoadedDatasets;

CREATE TABLE IF NOT EXISTS dataset (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    shaft_frequency DOUBLE,
    sampling_frequency INT,
    carga DOUBLE,
    bearing_type VARCHAR(255),
    bpfo DOUBLE,
    bpfi DOUBLE,
    bsf DOUBLE,
    ftf DOUBLE,
    min_to_check INT,
    max_to_check INT,
    files_added INT
);

INSERT INTO dataset (nombre, shaft_frequency, sampling_frequency, carga, bearing_type, bpfo, bpfi, bsf, ftf, min_to_check, max_to_check, files_added) VALUES
('IMS1', 33.33, 20480, 26.7, 'Rexnord ZA-2115', 236, 297, 278, 15, 0, 2156, 1),
('IMS2', 33.33, 20480, 26.7, 'Rexnord ZA-2115', 236, 297, 278, 15, 0, 984, 1),
('IMS3', 33.33, 20480, 26.7, 'Rexnord ZA-2115', 236, 297, 278, 15, 0, 6324, 1),
('XJTU2-1', 37.5, 25600, 11, 'LDK UER204', 112.19, 178.94, 75.21, 14.2, 0, 491, 1),
('XJTU2-3', 37.5, 25600, 11, 'LDK UER204', 112.19, 178.94, 75.21, 14.2, 0, 533, 1),
('XJTU3-1', 40, 25600, 10, 'LDK UER204', 123.2, 196.49, 82.58, 15.40, 0, 2538, 1),
('XJTU3-4', 40, 25600, 10, 'LDK UER204', 123.2, 196.49, 82.58, 15.40, 0, 1515, 1);

select * from dataset;

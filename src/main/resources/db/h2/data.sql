INSERT INTO doctors VALUES (1, 'James', 'Carter');
INSERT INTO doctors VALUES (2, 'Helen', 'Leary');
INSERT INTO doctors VALUES (3, 'Linda', 'Douglas');
INSERT INTO doctors VALUES (4, 'Rafael', 'Ortega');
INSERT INTO doctors VALUES (5, 'Henry', 'Stevens');
INSERT INTO doctors VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'neuropathist');
INSERT INTO specialties VALUES (2, 'vertebrologist');
INSERT INTO specialties VALUES (3, 'orthopedist');

INSERT INTO doctor_specialties VALUES (2, 1);
INSERT INTO doctor_specialties VALUES (3, 2);
INSERT INTO doctor_specialties VALUES (3, 3);
INSERT INTO doctor_specialties VALUES (4, 2);
INSERT INTO doctor_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'healthy');
INSERT INTO types VALUES (2, 'kyphosis');
INSERT INTO types VALUES (3, 'flat back');
INSERT INTO types VALUES (4, 'swayback');
INSERT INTO types VALUES (5, 'forward head');

INSERT INTO users VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023');
INSERT INTO users VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749');
INSERT INTO users VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763');
INSERT INTO users VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198');
INSERT INTO users VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765');
INSERT INTO users VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654');
INSERT INTO users VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387');
INSERT INTO users VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683');
INSERT INTO users VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435');
INSERT INTO users VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487');

INSERT INTO diseases VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO diseases VALUES (2, 'Basil', '2012-08-06', 2, 2);
INSERT INTO diseases VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO diseases VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO diseases VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO diseases VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO diseases VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO diseases VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO diseases VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO diseases VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO diseases VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO diseases VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO diseases VALUES (13, 'Sly', '2012-06-08', 1, 10);

INSERT INTO visits VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits VALUES (4, 7, '2013-01-04', 'spayed');
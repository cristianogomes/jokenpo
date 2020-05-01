INSERT INTO TOOL VALUES (1, 'pedra');
INSERT INTO TOOL VALUES (2, 'papel');
INSERT INTO TOOL VALUES (3, 'tesoura');
INSERT INTO TOOL VALUES (4, 'lagarto');
INSERT INTO TOOL VALUES (5, 'spock');

--Ação pedra
INSERT INTO ACTION (id, description, source_id, target_id) VALUES (1, 'quebra', 1, 3);
INSERT INTO ACTION (id, description, source_id, target_id) VALUES (2, 'esmaga', 1, 4);

--Ação papel
INSERT INTO ACTION (id, description, source_id, target_id) VALUES (3, 'cobre', 2, 1);
INSERT INTO ACTION (id, description, source_id, target_id) VALUES (4, 'refuta', 2, 5);

--Ação tesoura
INSERT INTO ACTION (id, description, source_id, target_id) VALUES (5, 'corta', 3, 2);
INSERT INTO ACTION (id, description, source_id, target_id) VALUES (6, 'mata', 3, 4);

--Ação lagarto
INSERT INTO ACTION (id, description, source_id, target_id) VALUES (7, 'come', 4, 2);
INSERT INTO ACTION (id, description, source_id, target_id) VALUES (8, 'envenena', 4, 5);

--Ação spock
INSERT INTO ACTION (id, description, source_id, target_id) VALUES (9, 'vaporiza', 5, 1);
INSERT INTO ACTION (id, description, source_id, target_id) VALUES (10, 'quebra', 5, 3);
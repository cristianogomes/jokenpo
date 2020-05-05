INSERT INTO TOOL VALUES (1000, 'pedra');
INSERT INTO TOOL VALUES (2000, 'papel');
INSERT INTO TOOL VALUES (3000, 'tesoura');
INSERT INTO TOOL VALUES (4000, 'lagarto');
INSERT INTO TOOL VALUES (5000, 'spock');

--Ação pedra
INSERT INTO ACTION (id, description, source_id, target_id) VALUES (1000, 'quebra', 1000, 3000);
INSERT INTO ACTION (id, description, source_id, target_id) VALUES (2000, 'esmaga', 1000, 4000);

--Ação papel
INSERT INTO ACTION (id, description, source_id, target_id) VALUES (3000, 'cobre', 2000, 1000);
INSERT INTO ACTION (id, description, source_id, target_id) VALUES (4000, 'refuta', 2000, 5000);

--Ação tesoura
INSERT INTO ACTION (id, description, source_id, target_id) VALUES (5000, 'corta', 3000, 2000);
INSERT INTO ACTION (id, description, source_id, target_id) VALUES (6000, 'mata', 3000, 4000);

--Ação lagarto
INSERT INTO ACTION (id, description, source_id, target_id) VALUES (7000, 'come', 4000, 2000);
INSERT INTO ACTION (id, description, source_id, target_id) VALUES (8000, 'envenena', 4000, 5000);

--Ação spock
INSERT INTO ACTION (id, description, source_id, target_id) VALUES (9000, 'vaporiza', 5000, 1000);
INSERT INTO ACTION (id, description, source_id, target_id) VALUES (10000, 'quebra', 5000, 3000);

INSERT INTO PLAYER (id, name) VALUES (1000, 'Player 1000');
INSERT INTO PLAYER (id, name) VALUES (2000, 'Player 2000');
INSERT INTO PLAYER (id, name) VALUES (3000, 'Player 3000');
INSERT INTO PLAYER (id, name) VALUES (4000, 'Player 4000');
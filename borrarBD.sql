DELETE FROM tapplications;
DELETE FROM tseats;
DELETE FROM ttrips;
DELETE FROM tusers;
INSERT INTO "PUBLIC"."TUSERS"
( "EMAIL", "LOGIN", "NAME", "PASSWORD", "STATUS", "SURNAME" )
VALUES ( 'test2', 'test2', 'test2', 'test',1 , 'test2');
INSERT INTO "PUBLIC"."TUSERS"
(  "EMAIL", "LOGIN", "NAME", "PASSWORD", "STATUS", "SURNAME" )
VALUES (  'test3', 'test3', 'test3', 'test',1 , 'test3');
DROP TABLE IF EXISTS perdoruesit ;

CREATE TABLE perdoruesit ( id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, emri VARCHAR(100) NOT NULL, mosha integer,gjinia varchar(50));
DROP TABLE IF EXISTS puna ;
CREATE TABLE puna ( id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,user_id integer, emri VARCHAR(100) NOT NULL, paga decimal);
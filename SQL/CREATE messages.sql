DROP TABLE IF EXISTS v_messages;

CREATE TABLE v_messages (
    id int GENERATED ALWAYS AS IDENTITY,
    sender int NOT NULL,
    receiver int NOT NULL,
    dateTimeStamp TIMESTAMP WITH TIME ZONE,
    content VARCHAR(500),

    PRIMARY KEY (id)
);

ALTER TABLE v_messages ALTER COLUMN dateTimeStamp SET DEFAULT CURRENT_TIMESTAMP;

SELECT * FROM v_messages WHERE sender = ? AND receiver = ? ORDER BY dateTimeStamp;
INSERT INTO v_messages (sender, receiver, content) VALUES ( ?, ?, ? );

DROP TABLE IF EXISTS v_messages;

SELECT * FROM v_messages;

TRUNCATE TABLE v_messages;

DELETE FROM v_messages WHERE id BETWEEN 10 and 18;
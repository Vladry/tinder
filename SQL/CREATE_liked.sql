DROP TABLE IF EXISTS v_liked;

CREATE TABLE IF NOT EXISTS v_liked
(
    id          integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    isLike     boolean,
    who_id      integer,
    whom_id     integer
);

SELECT isLike, who_id, whom_id FROM v_liked;
TRUNCATE v_liked;


SELECT * FROM "v_liked" WHERE who_id = ? AND whom_id = ?;
UPDATE "v_liked" SET islike = ? WHERE who_id = ? AND whom_id = ?;
INSERT INTO "v_liked" (isLike, who_id, whom_id) VALUES(?, ?, ?);

SELECT whom_id from "v_liked" WHERE who_id = ? AND isLike = true;

SELECT whom_id FROM "v_liked" WHERE isLike = true;
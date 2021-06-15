SELECT
    tableb.*
FROM
    (
        SELECT
            ROWNUM AS rnum,
            tablea.*
        FROM
            (
                SELECT
                    *
                FROM
                    tbl_member
                ORDER BY
                    reg_date DESC
            ) tablea
        WHERE
            ROWNUM <= ( 1 * 5 ) + 5
    ) tableb
WHERE
    tableb.rnum > 1 * 5
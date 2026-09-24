CREATE VIEW
    iloan_group
    (
        id,
        name,
        collateral_id,
        group_val,
        outstanding,
        rownum
    ) AS
SELECT
    t.id,
    t.name,
    t.collateral_id,
    t.group_val,
    t.outstanding,
    t.rownum
FROM
    (
        SELECT
            iloan_reappraise.id,
            iloan_reappraise.name,
            iloan_reappraise.collateral_id,
            iloan_reappraise.group_val,
            iloan_reappraise.outstanding,
            row_number() OVER (PARTITION BY iloan_reappraise.collateral_id ORDER BY iloan_reappraise.outstanding DESC) AS rownum
        FROM
            iloan_reappraise
        WHERE
            ((
                    iloan_reappraise.group_val IS NULL)
            OR  ((
                        iloan_reappraise.group_val)::text = ''::text)
            OR  ((
                        iloan_reappraise.group_val)::text = (iloan_reappraise.collateral_id)::text))) t
WHERE
    (
        t.rownum = 1);
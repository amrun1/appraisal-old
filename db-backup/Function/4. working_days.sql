CREATE OR REPLACE FUNCTION public.working_days(
    date,
    date)
  RETURNS integer AS
$BODY$
SELECT COUNT(days)::INT
    FROM generate_series($1, $2, '1 day') AS days
    WHERE EXTRACT(DOW FROM days) NOT IN(0, 6);
$BODY$
  LANGUAGE sql IMMUTABLE STRICT
  COST 100;
ALTER FUNCTION public.working_days(date, date)
  OWNER TO postgres;

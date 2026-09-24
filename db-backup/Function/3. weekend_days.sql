CREATE OR REPLACE FUNCTION public.weekend_days(
    date,
    date)
  RETURNS integer AS
$BODY$
SELECT COUNT(days)::INT
    FROM generate_series($1, $2, '1 day') AS days
    WHERE EXTRACT(DOW FROM days) IN(0, 6);
$BODY$
  LANGUAGE sql VOLATILE
  COST 100;
ALTER FUNCTION public.weekend_days(date, date)
  OWNER TO postgres;

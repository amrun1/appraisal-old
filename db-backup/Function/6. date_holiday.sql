CREATE OR REPLACE FUNCTION public.date_holiday(
    t_start timestamp without time zone,
    t_end timestamp without time zone)
  RETURNS SETOF record AS
$BODY$
 select tanggal from
  (SELECT h.tanggal AS tanggal
   FROM holiday h,
   ( SELECT a_1.tanggal AS tgl,
     working_days(a_1.tanggal, a_1.tanggal) AS wd
     FROM holiday a_1) f
     WHERE date($1) <= h.tanggal AND h.tanggal <= date(case when $2 is not null then $2else now()::timestamp end) AND f.wd = 1 AND h.tanggal = f.tgl) AS holiday
      $BODY$
  LANGUAGE sql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION public.date_holiday(timestamp without time zone, timestamp without time zone)
  OWNER TO postgres;

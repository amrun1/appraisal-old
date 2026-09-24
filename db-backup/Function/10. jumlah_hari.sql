CREATE OR REPLACE FUNCTION public.jumlah_hari(
    t_start timestamp without time zone,
    t_end timestamp without time zone)
  RETURNS bigint AS
$BODY$
  select working_days.wd - holiday.count - weekend.w
  from (select ceil(date_part('epoch', timestamp_mi(case when $2 is not null then $2 else now()::timestamp end, f_is_next_day($1))) / 3600 / 24)::integer as wd) AS working_days,
  (SELECT count(h.tanggal) AS count
                   FROM holiday h,
                    ( SELECT a_1.tanggal AS tgl,
                            working_days(a_1.tanggal, a_1.tanggal) AS wd
                           FROM holiday a_1) f
                  WHERE date($1) <= h.tanggal AND h.tanggal <= date(case when $2 is not null then $2 else now()::timestamp end) AND f.wd = 1 AND h.tanggal = f.tgl) AS holiday,
  (select weekend_days(date(f_is_next_day($1)), date(case when $2 is not null then $2 else now()::timestamp end)) as w ) AS weekend
  $BODY$
  LANGUAGE sql VOLATILE
  COST 100;
ALTER FUNCTION public.jumlah_hari(timestamp without time zone, timestamp without time zone)
  OWNER TO postgres;

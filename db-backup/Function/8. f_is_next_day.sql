CREATE OR REPLACE FUNCTION public.f_is_next_day(t_start timestamp without time zone)
  RETURNS timestamp without time zone AS
$BODY$
	select case when EXTRACT(DOW FROM date(days)) = 6 then f_find_holiday_cot(days::timestamp + interval '2 day')
	when EXTRACT(DOW FROM date(days)) = 0 then f_find_holiday_cot(days::timestamp + interval '1 day') else f_find_holiday_cot(days::timestamp) end oke
	from (select case when $1::time>f_parameter('COT')::time then (date($1) + INTERVAL '1 day')+f_parameter('COT_NEXT_DAY')::time
			when $1 in (  select tanggal from date_holiday($1, null) as da(tanggal date)) then (date($1) + INTERVAL '1 day')+f_parameter('COT_NEXT_DAY')::time  else $1 end days)  dayss
	$BODY$
  LANGUAGE sql VOLATILE
  COST 100;
ALTER FUNCTION public.f_is_next_day(timestamp without time zone)
  OWNER TO postgres;
CREATE OR REPLACE FUNCTION public.f_find_holiday_cot(t_start timestamp without time zone)
  RETURNS timestamp without time zone AS
$BODY$
DECLARE
    a record;
BEGIN
FOR a IN  
select * from date_holiday(t_start, now()::timestamp) as da(tanggal date) order by tanggal 
LOOP
	IF(a.tanggal = t_start::date) THEN
		t_start := t_start + INTERVAL '1 day';		
	END IF;
	if (EXTRACT(DOW FROM t_start) = 0) then
		t_start := t_start + INTERVAL '1 day';
	END IF;
	if (EXTRACT(DOW FROM t_start) = 6) then
		t_start := t_start + INTERVAL '2 day';
	END IF;
	IF(t_start::date < a.tanggal) THEN
		RETURN t_start;
	END IF;    
END LOOP;
RETURN t_start;    -- return final result
END;
$BODY$
  LANGUAGE plpgsql STABLE
  COST 100;
ALTER FUNCTION public.f_find_holiday_cot(timestamp without time zone)
  OWNER TO postgres;

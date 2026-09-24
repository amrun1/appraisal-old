CREATE OR REPLACE FUNCTION public.save_to_log(
	sourceid text,
	source text,
	status text,
	description text)
    RETURNS text
    LANGUAGE 'plpgsql'
    COST 100.0

AS $function$

BEGIN
  INSERT INTO received_ws_log (id, sourceid, source, status, description, date_time) VALUES (NEXTVAL('received_ws_log_id_seq'),sourceid,source,status,description,NOW());
  RETURN description;
  COMMIT;
END;

$function$;

ALTER FUNCTION public.save_to_log(text, text, text, text)
    OWNER TO postgres;

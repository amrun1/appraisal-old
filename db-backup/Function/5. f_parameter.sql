CREATE OR REPLACE FUNCTION public.f_parameter(param character varying)
  RETURNS character varying AS
$BODY$
	select value from system_params where code = $1
$BODY$
  LANGUAGE sql VOLATILE
  COST 100;
ALTER FUNCTION public.f_parameter(character varying)
  OWNER TO postgres;
CREATE OR REPLACE FUNCTION public.f_sla_approval(id_umum bigint)
  RETURNS bigint AS
$BODY$
	select case when sum(diff) is not null then sum(ceil(date_part('epoch', diff)/3600)-(weekend*24)-(holiday*24))::int8 else 0 end from sla_approval where id_umum = $1
	$BODY$
  LANGUAGE sql VOLATILE
  COST 100;
ALTER FUNCTION public.f_sla_approval(bigint)
  OWNER TO postgres;

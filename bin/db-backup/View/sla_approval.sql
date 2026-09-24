CREATE OR REPLACE VIEW public.sla_approval AS 
 SELECT awal.tanggalawal,
        CASE
            WHEN akhir.tanggalakhir IS NULL THEN now()::timestamp without time zone
            ELSE akhir.tanggalakhir
        END AS tanggalakhir,
        CASE
            WHEN akhir.tanggalakhir IS NULL THEN now()::timestamp without time zone
            ELSE akhir.tanggalakhir
        END - awal.tanggalawal AS diff,
    awal.id_umum,
    ( SELECT count(h.tanggal) AS count
           FROM holiday h,
            ( SELECT a_1.tanggal AS tgl,
                    working_days(a_1.tanggal, a_1.tanggal) AS wd
                   FROM holiday a_1) f
          WHERE date(awal.tanggalawal) <= h.tanggal AND h.tanggal <= date(
                CASE
                    WHEN akhir.tanggalakhir IS NULL THEN now()::timestamp without time zone
                    ELSE akhir.tanggalakhir
                END) AND f.wd = 1 AND h.tanggal = f.tgl) AS holiday,
    weekend_days(date(f_is_next_day(awal.tanggalawal)), date(
        CASE
            WHEN akhir.tanggalakhir IS NULL THEN now()::timestamp without time zone
            ELSE akhir.tanggalakhir
        END)) AS weekend
   FROM ( SELECT colls1.tanggalawal,
            colls1.groups,
            colls1.id_umum
           FROM ( SELECT
                        CASE
                            WHEN (coll1.nomor % 2::bigint) = 1 THEN coll1.tanggal
                            ELSE NULL::timestamp without time zone
                        END AS tanggalawal,
                    (coll1.nomor - 1) / 2 AS groups,
                    coll1.id_umum
                   FROM ( SELECT row_number() OVER (ORDER BY task_flow_history.tanggal) AS nomor,
                            task_flow_history.catatan,
                            task_flow_history.param,
                            task_flow_history.tanggal,
                            task_flow_history.id_data_umum AS id_umum
                           FROM task_flow_history
                          WHERE task_flow_history.param IS NOT NULL AND (task_flow_history.param = ANY (ARRAY[9::bigint, 11::bigint, 12::bigint]))
                          ORDER BY task_flow_history.tanggal) coll1) colls1
          WHERE colls1.tanggalawal IS NOT NULL) awal
     LEFT JOIN ( SELECT colls1.tanggalakhir,
            colls1.groups,
            colls1.id_umum
           FROM ( SELECT
                        CASE
                            WHEN (coll1.nomor % 2::bigint) = 0 THEN coll1.tanggal
                            ELSE NULL::timestamp without time zone
                        END AS tanggalakhir,
                    (coll1.nomor - 1) / 2 AS groups,
                    coll1.id_umum
                   FROM ( SELECT row_number() OVER (ORDER BY task_flow_history.tanggal) AS nomor,
                            task_flow_history.catatan,
                            task_flow_history.param,
                            task_flow_history.tanggal,
                            task_flow_history.id_data_umum AS id_umum
                           FROM task_flow_history
                          WHERE task_flow_history.param IS NOT NULL AND (task_flow_history.param = ANY (ARRAY[9::bigint, 11::bigint, 12::bigint]))
                          ORDER BY task_flow_history.tanggal) coll1) colls1
          WHERE colls1.tanggalakhir IS NOT NULL) akhir ON awal.groups = akhir.groups AND awal.id_umum = akhir.id_umum AND akhir.id_umum IS NOT NULL;

ALTER TABLE public.sla_approval
  OWNER TO postgres;

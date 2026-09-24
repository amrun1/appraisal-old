CREATE OR REPLACE VIEW public.pending_jobs_by_user AS 
 SELECT x.nama,
    x.posisi,
    sum(x.bd5) AS bd5,
    sum(x.bd4) AS bd4,
    sum(x.bd3) AS bd3,
    sum(x.bd2) AS bd2,
    sum(x.bd1) AS bd1,
    sum(x.bd0) AS bd0
   FROM ( SELECT o.nama,
            o.posisi,
                CASE
                    WHEN (o.working_days - o.weekend::double precision - o.holiday::double precision) > 4::double precision THEN sum(o.id)
                    ELSE 0::numeric
                END AS bd5,
                CASE
                    WHEN (o.working_days - o.weekend::double precision - o.holiday::double precision) = 4::double precision THEN sum(o.id)
                    ELSE 0::numeric
                END AS bd4,
                CASE
                    WHEN (o.working_days - o.weekend::double precision - o.holiday::double precision) = 3::double precision THEN sum(o.id)
                    ELSE 0::numeric
                END AS bd3,
                CASE
                    WHEN (o.working_days - o.weekend::double precision - o.holiday::double precision) = 2::double precision THEN sum(o.id)
                    ELSE 0::numeric
                END AS bd2,
                CASE
                    WHEN (o.working_days - o.weekend::double precision - o.holiday::double precision) = 1::double precision THEN sum(o.id)
                    ELSE 0::numeric
                END AS bd1,
                CASE
                    WHEN (o.working_days - o.weekend::double precision - o.holiday::double precision) <= 0::double precision THEN sum(o.id)
                    ELSE 0::numeric
                END AS bd0
           FROM ( SELECT a.nama_pic AS nama,
                    b.posisi,
                    weekend_days(date(f_is_next_day(b.submit_date)), date(now()::timestamp without time zone)) AS weekend,
                    sum(ceil(date_part('epoch'::text, timestamp_mi(now()::timestamp without time zone, f_is_next_day(b.submit_date))) / 3600::double precision / 24::double precision)) AS working_days,
                    ( SELECT count(h.tanggal) AS count
                           FROM holiday h,
                            ( SELECT a_1.tanggal AS tgl,
                                    working_days(a_1.tanggal, a_1.tanggal) AS wd
                                   FROM holiday a_1) f
                          WHERE date(b.submit_date) <= h.tanggal AND h.tanggal <= date(now()::timestamp without time zone) AND f.wd = 1 AND h.tanggal = f.tgl) AS holiday,
                    count(b.id) AS id
                   FROM data_detail a
                     LEFT JOIN data_umum b ON a.id_data_umum = b.id
                  WHERE (b.posisi::text <> ALL (ARRAY['DROP'::character varying::text, 'User_TL'::character varying::text, 'CLOSE'::character varying::text])) AND b.submit_date IS NOT NULL AND a.nama_pic IS NOT NULL
                  GROUP BY a.nama_pic, b.posisi, b.submit_date, b.id) o
          GROUP BY o.nama, o.posisi, o.working_days, o.id, o.weekend, o.holiday) x
  GROUP BY x.nama, x.posisi
  ORDER BY x.nama DESC, x.posisi;

ALTER TABLE public.pending_jobs_by_user
  OWNER TO postgres;

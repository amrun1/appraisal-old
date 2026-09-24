CREATE OR REPLACE VIEW public.pending_jobs_by_customers AS 
 SELECT isi.no_order AS orders,
    isi.nama,
    isi.alamat,
    isi.kota,
    isi.posisi,
    (isi.working_days - isi.weekend::double precision - isi.holiday::double precision)::integer AS proses
   FROM ( SELECT b.no_order,
            b.nama,
            a.alamat,
            a.kode_kota AS kota,
            b.posisi,
            weekend_days(date(f_is_next_day(b.submit_date)), date(now()::timestamp without time zone)) AS weekend,
            sum(ceil(date_part('epoch'::text, timestamp_mi(now()::timestamp without time zone, f_is_next_day(b.submit_date))) / 3600::double precision / 24::double precision)) AS working_days,
            ( SELECT count(h.tanggal) AS count
                   FROM holiday h,
                    ( SELECT a_1.tanggal AS tgl,
                            working_days(a_1.tanggal, a_1.tanggal) AS wd
                           FROM holiday a_1) f
                  WHERE date(b.submit_date) <= h.tanggal AND h.tanggal <= date(now()::timestamp without time zone) AND f.wd = 1 AND h.tanggal = f.tgl) AS holiday
           FROM data_detail a
             LEFT JOIN data_umum b ON a.id_data_umum = b.id
          WHERE (b.posisi::text <> ALL (ARRAY['DROP'::character varying::text, 'User_TL'::character varying::text, 'CLOSE'::character varying::text])) AND b.submit_date IS NOT NULL AND a.nama_pic IS NOT NULL
          GROUP BY b.posisi, b.submit_date, b.no_order, b.nama, a.alamat, a.kode_kota
          ORDER BY b.no_order DESC) isi
  ORDER BY ((isi.working_days - isi.weekend::double precision - isi.holiday::double precision)::integer) DESC;

ALTER TABLE public.pending_jobs_by_customers
  OWNER TO postgres;

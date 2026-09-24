-- Function: public.submit_order(text, text, numeric, numeric, numeric, numeric, text, text, text, text, text, text, text, numeric, text, text, text, text, text, text, text, text, text, text, text, text, integer)

-- DROP FUNCTION public.submit_order(text, text, numeric, numeric, numeric, numeric, text, text, text, text, text, text, text, numeric, text, text, text, text, text, text, text, text, text, text, text, text, integer);

CREATE OR REPLACE FUNCTION public.submit_order(
    IN source text,
    IN aclid text,
    IN generaldataorderid numeric,
    IN masterofcustomerstatusid numeric,
    IN masterofcollateralstatusid numeric,
    IN masterofappraisaltypeid numeric,
    IN createdbyuserid text,
    IN name text,
    IN contactpersonname text,
    IN contactpersonphone text,
    IN notes text,
    IN plafondmarketing text,
    IN reporttype text,
    IN masterofassettypeid numeric,
    IN address text,
    IN residential_apartement text,
    IN clustertower text,
    IN block_floor text,
    IN "number" text,
    IN rt1 text,
    IN rw1 text,
    IN zipcode text,
    IN region text,
    IN district text,
    IN city_regency text,
    IN province text,
    IN totalfile integer,
    OUT status integer,
    OUT message text,
    OUT orderid numeric,
    OUT orderdetailid numeric)
  RETURNS record AS
$BODY$
DECLARE
	vCount integer := 0;
	vTemp integer := 0;
	V_ID_HEADER BIGINT;
	vState TEXT :='INSERT';
	vJenisAppraisal TEXT := '';
	vBranch NUMERIC;
	vNoOrder TEXT;
BEGIN
	status :=0;
	message := 'Success';
	IF totalfile = 0 THEN
		status :=3;
		message := SAVE_TO_LOG(aclid,source,to_char(status,'0'),'Total file must be greater than 0.');
	ELSE
		SELECT COUNT(ID) INTO vCount from STATUS_NASABAH WHERE ID = masterofcustomerstatusid;
		IF vCount = 0 THEN
			status :=3;
			message := SAVE_TO_LOG(aclid,source,to_char(status,'0'),'Data Master of Customer Status Id Not Valid');
		ELSE
			SELECT COUNT(ID) INTO vCount FROM status_jaminan WHERE ID = masterofcollateralstatusid;
			IF vCount = 0 THEN
				status :=3;
				message := SAVE_TO_LOG(aclid,source,to_char(status,'0'),'Data Master of Collateral Status Id Not Valid');
			ELSE
				SELECT  COUNT(ID) INTO vCount FROM JENIS_APPRAISAL WHERE ID = masterofappraisaltypeid;
				IF vCount = 0 THEN
					status :=3;
					message := SAVE_TO_LOG(aclid,source,to_char(status,'0'),'Data Master of Appraisal Type Id Not Valid');
				ELSE
					SELECT COUNT(user_id) INTO vCount  FROM PERSON WHERE user_id = createdbyuserid;
					IF vCount = 0 THEN
						status :=3;
						message := SAVE_TO_LOG(aclid,source,to_char(status,'0'),'Data User Id Not Valid');
					ELSE
							SELECT COUNT(ID) INTO vCount  FROM JENIS_AKTIVA WHERE ID = masterofassettypeid;
						IF vCount = 0 THEN
							status :=3;
							message := SAVE_TO_LOG(aclid,source,to_char(status,'0'),'Data Master of Asset Type Id Not Valid');
						ELSE
							SELECT COUNT(KODE) INTO vCount  FROM KODE_POS WHERE KODE = zipcode;
							IF vCount = 0 THEN
								status :=3;
								message := SAVE_TO_LOG(aclid,source,to_char(status,'0'),'Data Master of ZipCode Not Valid');
							ELSE
								SELECT source_status INTO vTemp FROM DATA_UMUM WHERE NO_ORDER = TO_CHAR(COALESCE(generaldataorderid,0),'fm9999999999999') AND id_source = aclid ;
								IF vTemp IS NULL  THEN
									SELECT source_status INTO vTemp FROM DATA_UMUM WHERE id_source = aclid;
								END IF ;
								IF generaldataorderid IS NOT NULL AND reporttype <> 'New' AND vTemp IS NULL THEN
									select id into V_ID_HEADER from data_umum where no_order = TO_CHAR(COALESCE(generaldataorderid,0),'fm9999999999999');
-- 									V_ID_HEADER := copy_data_debitur_fix(203,'Marketing','');
									IF V_ID_HEADER IS NOT NULL THEN
										V_ID_HEADER := CAST(copy_data_debitur_fix(cast(V_ID_HEADER AS INTEGER),createdbyuserid,'') AS BIGINT);
										UPDATE data_umum SET id_source = aclid,jenis_laporan = reporttype, source_status = 0,task_list='App_Admin',posisi = 'App_Admin' WHERE ID = V_ID_HEADER;
										UPDATE data_detail  SET blok_lantai=block_Floor, kode_kota=city_Regency, cluster_tower=clustertower, kode_kecamatan=district, perumahan_apartment=residential_Apartement, alamat=address,nomor=number, kode_propinsi=province, rt=rt1, rw=rw1, kode_kelurahan=region, kode_pos=zipcode, total_file_attach=totalfile WHERE id_data_umum = V_ID_HEADER;
										select id into orderdetailid from data_detail where id_data_umum = V_ID_HEADER;
										SELECT no_order into orderId FROM DATA_UMUM WHERE id = V_ID_HEADER;
										DELETE from data_attact where id_data_detail = orderdetailid;
										vState := 'COPY';
									ELSE
										vState := 'SKIPCOPY';
									END IF;
								ELSEIF vTemp IS NULL AND generaldataorderid IS NULL  THEN
									vState := 'INSERT';
								ELSEIF vTemp = 6 OR vTemp = 0 THEN
									vState := 'UPDATE';
--                                                                          DELETE FROM data_attact WHERE id_data_detail = (SELECT ID FROM data_detail WHERE id_data_umum = (SELECT ID FROM DATA_UMUM WHERE NO_ORDER = TO_CHAR(COALESCE(generaldataorderid,0),'fm9999999999999')));
                                                                        DELETE FROM data_attact WHERE id_data_detail = (SELECT ID FROM data_detail WHERE id_data_umum = (SELECT ID FROM DATA_UMUM WHERE ID_SOURCE = aclid));
								ELSE
									vState := 'SKIP';
								END IF;
								IF masterofappraisaltypeid = 3 THEN
									vJenisAppraisal := 'Eksternal';
									masterofappraisaltypeid:=2;
								ELSE
									vJenisAppraisal := 'Internal';
								END IF;
								select COALESCE(cabang_id,app_cabang_id) into vBranch from person_roles where user_id = createdbyuserid and person_level = 'MARKETING';
								IF vState = 'INSERT' THEN
									vNoOrder:=GENERATE_NO_ORDER();
									INSERT INTO data_umum(id, created_by, created_date, catatan, nama_contact_person, telp_contact_person, nama, plafond_marketing, posisi, task_list, jenis_laporan, jenis_appraisal_id, status_jaminan_id, status_nasabah_id, retention, no_order,  kategori_appraisal, source, id_source,appraisal_cabang_id,source_status,cabang_id)
									VALUES(NEXTVAL('DATA_UMUM_SEQ'),createdbyuserid,NOW(),notes,contactpersonname,contactpersonphone,name,plafondmarketing,'App_Admin',
									'App_Admin',reporttype,masterofappraisaltypeid,masterofcollateralstatusid,masterofcustomerstatusid,0,vNoOrder,vJenisAppraisal,source,aclId,1,0,vBranch);
									V_ID_HEADER := CURRVAL('DATA_UMUM_SEQ');
									INSERT INTO data_detail(id, blok_lantai, kode_kota, cluster_tower, kode_kecamatan, perumahan_apartment, alamat, nomor, kode_propinsi, rt, rw, kode_kelurahan, kode_pos,  id_data_umum, id_jenis_aktiva,total_file_attach)
									VALUES(NEXTVAL('DATA_DETAIL_SEQ'), block_Floor, city_Regency, clustertower, district, residential_Apartement, address, number, province, rt1, rw1, region, zipcode, V_ID_HEADER, masterofassettypeid,totalfile);
									orderId:= CAST(vNoOrder AS NUMERIC);
									orderDetailId:=CURRVAL('DATA_DETAIL_SEQ');
									INSERT INTO task_flow_history(id, catatan, dari_person, id_data_umum, tanggal) VALUES (NEXTVAL('task_flow_history_seq'),'TL Submit ke Cabang Appraisal',createdbyuserid,V_ID_HEADER,NOW());
									message := SAVE_TO_LOG(aclid,source,to_char(status,'0'),'Success');
								ELSEIF vState = 'UPDATE' THEN
									UPDATE data_umum SET updated_by=createdbyuserid, updated_date=NOW(), catatan = notes, nama_contact_person = contactpersonname, telp_contact_person = contactpersonphone, nama = name, plafond_marketing = plafondmarketing , posisi = 'App_Admin', task_list = 'App_Admin',jenis_laporan=reporttype, jenis_appraisal_id=masterofappraisaltypeid,status_jaminan_id=masterofcollateralstatusid, status_nasabah_id=masterofcustomerstatusid WHERE NO_ORDER = CAST(COALESCE(generaldataorderid,0) as TEXT);
									UPDATE data_detail  SET blok_lantai=block_Floor, kode_kota=city_Regency, cluster_tower=clustertower, kode_kecamatan=district, perumahan_apartment=residential_Apartement, alamat=address,nomor=number, kode_propinsi=province, rt=rt1, rw=rw1, kode_kelurahan=region, kode_pos=zipcode, total_file_attach=totalfile WHERE id_data_umum = (SELECT ID FROM DATA_UMUM WHERE NO_ORDER = CAST(COALESCE(generaldataorderid,0) as TEXT));
									orderId:= generaldataorderid;
									select id into orderdetailid from data_detail where id_data_umum = (SELECT ID FROM DATA_UMUM WHERE NO_ORDER = CAST(COALESCE(generaldataorderid,0) as TEXT));
									IF generaldataorderid  IS NULL THEN
										SELECT no_order into orderId FROM DATA_UMUM WHERE id_source = aclid;
										select id into orderdetailid from data_detail where id_data_umum = (SELECT ID FROM DATA_UMUM WHERE id_source = aclid);
									END IF;
									message := SAVE_TO_LOG(aclid,source,to_char(status,'0'),'Success');
								ELSEIF vState = 'COPY' THEN
									message := SAVE_TO_LOG(aclid,source,to_char(status,'0'),'Success');
								ELSEIF vState = 'SKIPCOPY' THEN
									status :=3;
									message := SAVE_TO_LOG(aclid,source,to_char(status,'0'),'Data not Exist.');
								ELSE
									status :=3;
									message := SAVE_TO_LOG(aclid,source,to_char(status,'0'),'Data Already Exist.');
									orderId:= generaldataorderid;
									select id into orderdetailid from data_detail where id_data_umum = (SELECT ID FROM DATA_UMUM WHERE NO_ORDER = CAST(COALESCE(generaldataorderid,0) as TEXT));
								END IF ;
							END IF;
						END IF;
					END IF;
				END IF;
			END IF;
		END IF;
	END IF ;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION public.submit_order(text, text, numeric, numeric, numeric, numeric, text, text, text, text, text, text, text, numeric, text, text, text, text, text, text, text, text, text, text, text, text, integer)
  OWNER TO postgres;

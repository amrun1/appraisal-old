CREATE OR REPLACE FUNCTION public.submit_attachment(
	namafile text,
	extension text,
	orderdatadetailid numeric,
	masterjenisdokumenid numeric,
	attachment bytea,
	attachid numeric,
	OUT attachmentid numeric,
	OUT completestatus boolean,
	OUT message text,
	OUT status integer)
    RETURNS record
    LANGUAGE 'plpgsql'
    COST 100.0

AS $function$

DECLARE
	vCount integer:= 0;
  vTemp integer:=0;
	vAktivaDetail numeric;
	vAktivaDokumen numeric;
BEGIN
	status:=0;
	message:= 'Success';
	completestatus:=FALSE;
	SELECT COUNT(ID) INTO vCount FROM data_attact WHERE id_data_detail = orderdatadetailid;
	SELECT total_file_attach INTO vTemp FROM data_detail WHERE id = orderdatadetailid;
	IF vCount = vTemp AND  attachid IS NULL THEN
		status:=1;
		message:= 'Failed To Save Attachment. Attachment already complete. ';
	ELSE
		SELECT ID_JENIS_AKTIVA INTO vAktivaDetail FROM DATA_DETAIL WHERE ID = orderdatadetailid;
		SELECT ID_JENIS_AKTIVA INTO vAktivaDokumen FROM JENIS_DOKUMEN WHERE ID = masterjenisdokumenid;
		IF vAktivaDokumen <> vAktivaDetail THEN
			status:=1;
			message:= 'Failed To Save Attachment. Document and Asset Type not matched. ';
		ELSE
			SELECT COUNT(ID) INTO vCount from JENIS_DOKUMEN WHERE ID = masterjenisdokumenid;
			IF vCount = 0 THEN
				status:=1;
				message:= 'Failed To Save Attachment. Document Type Not Registerd';
			ELSE
				SELECT COUNT(ID) INTO vCount FROM DATA_DETAIL WHERE ID = orderdatadetailid;
				IF vCount = 0 THEN
					status:=1;
					message:= 'Failed To Save Attachment. Data Detail not Exist.';
				ELSE
					IF (lower(extension) <> 'jpeg' AND lower(extension) <> 'pdf' AND lower(extension) <> 'tif' AND lower(extension) <> 'jpg') THEN
						status:=1;
						message:= 'Failed To Save Attachment. Extension file not allowed.';
					ELSE
						IF attachid IS NOT NULL THEN
							SELECT COUNT(ID) INTO vCount FROM DATA_ATTACT WHERE ID = attachid;
							IF vCount = 0 THEN
								status:=1;
								message:= 'Failed To Save Attachment. Data Attachment not Exist.';
							ELSE
								UPDATE DATA_ATTACT
								SET ext=extension, file=attachment, nama=namaFile, id_data_detail=orderdatadetailid, id_jenis_dokumen=masterjenisdokumenid WHERE id = attachid;
								status:=0;
								message:= 'Success';
								attachmentid:=attachid;
								SELECT COUNT(ID) INTO vCount FROM data_attact WHERE id_data_detail = orderdatadetailid;
								SELECT total_file_attach INTO vTemp FROM data_detail WHERE id = orderdatadetailid;
								IF vCount = vTemp THEN
									completestatus:=TRUE;
								ELSE
									completestatus:=FALSE;
								END IF;
							END IF;
						ELSE
							INSERT INTO DATA_ATTACT (id, ext, file, nama, id_data_detail, id_jenis_dokumen)  VALUES (NEXTVAL('DATA_ATTACT_SEQ'),extension,attachment,namaFile,orderdatadetailid,masterjenisdokumenid);
							status:=0;
							message:= 'Success';
							attachmentid:= CURRVAL('DATA_ATTACT_SEQ');
							SELECT COUNT(ID) INTO vCount FROM data_attact WHERE id_data_detail = orderdatadetailid;
							SELECT total_file_attach INTO vTemp FROM data_detail WHERE id = orderdatadetailid;
							IF vCount = vTemp THEN
								completestatus:=TRUE;
								UPDATE data_umum SET source_status = 1 WHERE ID = (SELECT ID_DATA_UMUM FROM DATA_DETAIL WHERE ID = orderdatadetailid);
							ELSE
								completestatus:=FALSE;
							END IF;
						END IF;
					END IF;
				END IF;
			END IF;
		END IF;
	END IF;
END;

$function$;

ALTER FUNCTION public.submit_attachment(text, text, numeric, numeric, bytea, numeric)
    OWNER TO postgres;

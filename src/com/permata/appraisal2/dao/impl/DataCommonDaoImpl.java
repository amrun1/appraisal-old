package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.DataCommonDao;
import com.permata.appraisal2.dao.common.*;
import com.permata.appraisal2.dto.*;
import com.permata.appraisal2.model.Branch;
import com.permata.appraisal2.model.DataCommon;
import com.permata.appraisal2.model.StatusJaminan;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public class DataCommonDaoImpl extends DaoBaseImpl<DataCommon, Long> implements DataCommonDao {


    protected Criteria createCriteria(DataCommon example, OrderSpec orderSpec, String... excludedProperties) {
        Criteria criteria = createCriteria(example.getClass(), orderSpec);

            excludedProperties = new String[]{"createdBy","adminBy","valueBy","surveyBy","name","orderNumber", "sourceStatus"};
        if (example != null) {
            Example ex = ExampleHelper.createExample(example, excludedProperties);
            criteria.add(ex.ignoreCase());


            List<CriteriaVisitor<DataCommon>> v = getVisitors();
            for (CriteriaVisitor<DataCommon> criteriaVisitor : v) {
                criteriaVisitor.visit(criteria, example, excludedProperties);
            }
        }
        try {



            if (example.getCreatedBy() != null ) {
                criteria.add(Restrictions.ne("createdBy", example.getCreatedBy()));
            }

            if (example.getAdminBy() != null ) {
                criteria.add(Restrictions.ne("adminBy", example.getAdminBy()));
            }

            if (example.getValueBy() != null ) {
                criteria.add(Restrictions.ne("valueBy", example.getValueBy()));
            }

            if (example.getSurveyBy() != null){
                criteria.add(Restrictions.ne("surveyBy", example.getSurveyBy()));

//
            }

            if (example.getName() != null){
                criteria.add(Restrictions.ilike("name", example.getName(),MatchMode.ANYWHERE));
            }

            if (example.getOrderNumber() != null){
                criteria.add(Restrictions.ilike("orderNumber", example.getOrderNumber(),MatchMode.ANYWHERE));
            }



            if (example.getBranch() != null ) {
                logger.debug("example.getBranch().getId(): "+example.getBranch().toString());
                criteria.add(Restrictions.eq("branch.id", example.getBranch().getId()));
            }

            if (example.getMarketingBranch()!=null){
                logger.debug("example.getMarketingBranch().getId(): "+example.getMarketingBranch().toString());
                criteria.add(Restrictions.eq("marketingBranch.id", example.getMarketingBranch().getId()));
            }

            if (example.getSourceStatus() != null) {
                logger.debug("example.getSourceStatus() : "+example.getSourceStatus());
                if(example.getSourceStatus() == 1) {
                    criteria.add(Restrictions.ge("sourceStatus", 1));
                } else {
                    criteria.add(Restrictions.eq("sourceStatus", example.getSourceStatus()));
                }
            }

        } catch (Exception e) {
        }
        return criteria;
    }

    public List<PendingJobsByUsersDto> findPendingJobsByUsers(){
        String hql = "select nama, posisi, bd5, bd4, bd3, bd2,bd1, bd0 from pending_jobs_by_user";
        return getSession().createSQLQuery(hql).setResultTransformer(Transformers.aliasToBean(PendingJobsByUsersDto.class)).list();
    }

    public List<PendingJobsByUsersDto> findPendingJobsByCustomers(){
        String hql = "select orders, nama, alamat, kota, posisi, proses from pending_jobs_by_customers";
        return getSession().createSQLQuery(hql).setResultTransformer(Transformers.aliasToBean(PendingJobsByUsersDto.class)).list();
    }

//    public List<ViewReportJenisJaminanDto> getStatusJaminan(Timestamp submitDate, Timestamp closedDate){
//        String hql ="select groupMasterCabang, statusJaminan, tglGroupOrder, cast(countsjmnan as INT ) sumOrder, cast(to_char(tglGroupOrder, 'MM') as INT ) bulan,  cast(to_char(tglGroupOrder, 'yyyy') as INT ) tahun from ( " +
//                "select b.nama groupMasterCabang, c.nama statusJaminan, to_date(to_char(a.submit_date, 'yyyy MM'), 'yyyy MMM') tglGroupOrder, count(a.status_jaminan_id) as countsjmnan  " +
//                "from data_umum a " +
//                "inner join cabang b on a.cabang_id = b.id " +
//                "inner join status_jaminan c on a.status_jaminan_id = c.id " +
//                "where a.created_date is not null and a.status_jaminan_id is not null and  a.cabang_id is not null and a.submit_date BETWEEN '"+submitDate+"' and '"+closedDate+"' " +
////                "where a.created_date is not null and a.status_jaminan_id is not null and  a.cabang_id is not null " +
//                "group by b.id,c.id, to_date(to_char(a.submit_date, 'yyyy MM'), 'yyyy MMM') " +
//                "order by to_date(to_char(a.submit_date, 'yyyy MM'), 'yyyy MMM'), b.id, c.id desc) x";
//        return getSession().createSQLQuery(hql).setResultTransformer(Transformers.aliasToBean(ViewReportJenisJaminanDto.class)).list();
//    }
    
    public List<Object[]> getReportStatusJaminan(String columnName, String crossTabColumn, Timestamp submitDate, Timestamp closedDate, String[] groupMonth, List<StatusJaminanDto> statusJaminanDtoList) {
        String insideSQL = "";
        int counter = 1;
        for(StatusJaminanDto statusJaminanDto:statusJaminanDtoList) {
            for(int i=0;i<groupMonth.length;i++) {
                if(counter>1) insideSQL += " UNION ALL ";
                insideSQL += " (SELECT " +
                    " coalesce(TO_CHAR(a.submit_date, 'yyyyMM'), '"+groupMonth[i]+"') || '__" + statusJaminanDto.getName() + "' AS statusJaminan," +
                    "            b.nama groupMasterCabang," +
                    "            COUNT(a.status_jaminan_id)  AS countsjmnan" +
                    "        FROM" +
                    "            cabang b" +
                    "        LEFT OUTER JOIN" +
                    "            data_umum a" +
                    "        ON" +
                    "            b.id = a.cabang_id" +
                    "        AND a.created_date IS NOT NULL" +
                    "        AND a.status_jaminan_id IS NOT NULL" +
                    "        AND a.cabang_id IS NOT NULL" +
                    "        AND a.submit_date BETWEEN '"+submitDate+"' and '"+closedDate+"'" +
                    "        AND TO_CHAR(a.submit_date, 'yyyyMM') = '"+groupMonth[i]+"'" +
                    "        AND A.status_jaminan_id = " + statusJaminanDto.getId() +
                    "        GROUP BY" +
                    "            b.id," +
                    "            a.status_jaminan_id," +
                    "            TO_CHAR(a.submit_date, 'yyyyMM')" +
                    "        ORDER BY" +
                    "        TO_CHAR(a.submit_date, 'yyyyMM')," +
                    "            b.id," +
                    "            a.status_jaminan_id " +
                    "            DESC) ";
                counter++;
            }
        }
        String sql = "select StatusJaminan, "+columnName+" as total from crosstab($$" + insideSQL + " Order by 1,2 $$) as ct (StatusJaminan text, "+crossTabColumn+")";
        return getSession().createSQLQuery(sql).list();
    }

    public List<TypeObjectViewDto> getTypeObject(Timestamp submitDate, Timestamp closedDate) {
        String hql = "select nama grouptl, cast(sum(con) as INT) count, cast(sum(counteksternal) as INT) counteksternal, cast(sum(countinternal) as INT) countinternal, cast(sum(countindikasi) as INT) countindikasi, obj jenisaktiva from (   \n" +
                "                select nama, sum(co) con, case kat when 'Eksternal' then sum(co) else 0 end counteksternal, case kat when 'Internal' then sum(co) else 0 end countinternal, case kat when 'Indikasi' then sum(co) else 0 end countindikasi, obj, closed_date   \n" +
                "                from( select cbng.nama, count(umum.status_jaminan_id) co, lpa.kategori_appraisal kat, aktiva.nama obj, lpa.id_jenis_aktiva id_aktiva, umum.closed_date  \n" +
                "                from lpa_umum lpa   \n" +
                "                inner join data_umum umum on umum.id=lpa.id_data_umum   \n" +
                "                inner join cabang cbng on umum.cabang_id = cbng.id    \n" +
                "                inner join jenis_aktiva aktiva on aktiva.id = lpa.id_jenis_aktiva   \n" +
                "                where lpa.kategori_appraisal = 'Internal' and umum.closed_date is not null and umum.cabang_id is not null and umum.closed_date BETWEEN '"+submitDate+"' and '"+closedDate+"'  \n" +
                "                group by lpa.kategori_appraisal, aktiva.nama, cbng.nama, lpa.id_jenis_aktiva, umum.closed_date   \n" +
                "                union all   \n" +
                "                select cbng.nama , count(umum.status_jaminan_id), lpa.kategori_appraisal, aktiva.nama, lpa.id_jenis_aktiva id_aktiva, umum.closed_date   \n" +
                "                from lpa_umum lpa   \n" +
                "                inner join data_umum umum on umum.id=lpa.id_data_umum   \n" +
                "                inner join cabang cbng on umum.cabang_id = cbng.id    \n" +
                "                inner join jenis_aktiva aktiva on aktiva.id = lpa.id_jenis_aktiva   \n" +
                "                where lpa.kategori_appraisal = 'Eksternal'  and umum.closed_date is not null and umum.cabang_id is not null and umum.closed_date BETWEEN '"+submitDate+"' and '"+closedDate+"'   \n" +
                "                group by lpa.kategori_appraisal, aktiva.nama, cbng.nama, lpa.id_jenis_aktiva, umum.closed_date   \n" +
                "                union all   \n" +
                "                select cbng.nama, count(umum.status_jaminan_id), lpa.kategori_appraisal, aktiva.nama, lpa.id_jenis_aktiva id_aktiva, umum.closed_date   \n" +
                "                from lpa_umum lpa   \n" +
                "                inner join data_umum umum on umum.id=lpa.id_data_umum   \n" +
                "                inner join cabang cbng on umum.cabang_id = cbng.id    \n" +
                "                inner join jenis_aktiva aktiva on aktiva.id = lpa.id_jenis_aktiva   \n" +
                "                where lpa.kategori_appraisal = 'Indikasi' and umum.closed_date is not null and umum.cabang_id is not null and umum.closed_date BETWEEN '"+submitDate+"' and '"+closedDate+"'    \n" +
                "                group by lpa.kategori_appraisal, aktiva.nama, cbng.nama, lpa.id_jenis_aktiva, umum.closed_date) d   \n" +
                "                group by id_aktiva, nama, obj, kat, closed_date) e\n" +
                "                group by nama, obj     \n" +
                "                order by obj ";
        return getSession().createSQLQuery(hql).setResultTransformer(Transformers.aliasToBean(TypeObjectViewDto.class)).list();
    }

    public List<DataCommon> getIntraDay(Timestamp submitDate, Timestamp closedDate){
//        String hql = "from DataCommon where createdDate between "+submitDate+" and "+closedDate+" and  createdDate is not null and marketingBranch.id is not null and collateralStatus.id is not null order by createdDate asc ";
        String hql = "from DataCommon where submitDate is not null and marketingBranch.id is not null and collateralStatus.id is not null and posisi <> 'User_TL' and submitDate between '"+submitDate+"' and '"+closedDate+"' order by submitDate asc ";
        return createQuery(hql).list();
    }

//    public List<Branch> namaCabang(){
//        String hql ="from branch"
//    }

    @Override
    public int updateRejectToMarketing(DataCommon dataCommonId) {
        String hql = "update TaskHistory set param=99 where dataCommon=? ";
        return createQuery(hql).setParameter(0, dataCommonId).executeUpdate();
    }

    public PaginatedList<DataCommonTableDto> findForTables(PagingInfo pagingInfo, DataCommonTableDto filter) {
        DataCommon dataCommon = new DataCommon();
        BeanUtils.copyProperties(filter, dataCommon);
        // OrderSpec.create("orderNumber", OrderSpec.OrderType.asc)
        Criteria criteria = createCriteria(dataCommon, null);

        criteria.setProjection(Projections.property("id"));
        criteria.setProjection(Projections.property("orderNumber"));

        String hql = "select a.id as id, a.orderNumber as noOrder, a.name as namaDebitur, a.appraisalType.name as jenisAppraisal, " +
                "a.appraisalTypeCategory as kategoriAppraisal, a.dataDetail.alamat as lokasi, a.dataDetail.province as propinsi, " +
                "a.dataDetail.subDistrict as kelurahan, a.dataDetail.district as kecamatan, a.dataDetail.city as kota, " +
                "a.position as posisi, a.typeReport as jenisLaporan, a.dataDetail.assetType.name as jenisAktiva, " +
                "a.dataDetail.assetType.categoryType.name as kategoriAktiva, c.lpaNumber as noLaporan, a.marketingBranch.name as cabangBisnis," +
                "d.name as cabangAppraisal, a.createdBy as userId, p.name as namaMarketing " +
                " from DataCommon a left join a.dataDetail b left join b.lpaUmum c left join a.branch d, Person p " +
                " where p.userId = a.createdBy ";

        String hql2=" from DataCommon a left join a.dataDetail b left join b.lpaUmum c left join a.branch d, Person p " +
                " where p.userId = a.createdBy ";

        if (StringUtils.isNotBlank(filter.getNoOrder())) {
            hql += String.format("AND lower(a.orderNumber) like '%s' ", "%" + filter.getNoOrder().toLowerCase() + "%");
            hql2 += String.format("AND lower(a.orderNumber) like '%s' ", "%" + filter.getNoOrder().toLowerCase() + "%");
        }
        if (StringUtils.isNotBlank(filter.getNamaDebitur())) {
            hql += String.format("AND lower(a.name) like '%s' ", "%" + filter.getNamaDebitur().toLowerCase() + "%");
            hql2 += String.format("AND lower(a.name) like '%s' ", "%" + filter.getNamaDebitur().toLowerCase() + "%");
        }
        if (StringUtils.isNotBlank(filter.getLokasi())) {
            hql += String.format("AND lower(a.dataDetail.alamat) like '%s' ", "%" + filter.getLokasi().toLowerCase() + "%");
            hql2 += String.format("AND lower(a.dataDetail.alamat) like '%s' ", "%" + filter.getLokasi().toLowerCase() + "%");
        }
        if (StringUtils.isNotBlank(filter.getPosisi())) {
            hql += String.format("AND a.position = '%s' ", filter.getPosisi());
            hql2 += String.format("AND a.position = '%s' ", filter.getPosisi());
        }
        if (StringUtils.isNotBlank(filter.getNoLaporan())) {
            hql += String.format("AND c.lpaNumber like '%s' ", "%" +filter.getNoLaporan()+"%" );
            hql2 += String.format("AND c.lpaNumber like '%s' ", "%" +filter.getNoLaporan()+"%" );
        }
        if (StringUtils.isNotBlank(filter.getUserId())) {
            hql += String.format("AND lower(a.createdBy) = '%s' ", filter.getUserId().toLowerCase());
            hql2 += String.format("AND lower(a.createdBy) = '%s' ", filter.getUserId().toLowerCase());
        }
        if (StringUtils.isNotBlank(filter.getNamaMarketing())) {
            hql += String.format("AND lower(p.name) = '%s' ", filter.getNamaMarketing().toLowerCase());
            hql2 += String.format("AND lower(p.name) = '%s' ", filter.getNamaMarketing().toLowerCase());
        }
        if (StringUtils.isNotBlank(filter.getCabangBisnis())) {
            hql += String.format("AND a.marketingBranch.id = '%s' ", filter.getCabangBisnis());
            hql2 += String.format("AND a.marketingBranch.id = '%s' ", filter.getCabangBisnis());
        }
        if (StringUtils.isNotBlank(filter.getCabangAppraisal())) {
            hql += String.format("AND d.id = '%s' ", filter.getCabangAppraisal());
            hql2 += String.format("AND d.id = '%s' ", filter.getCabangAppraisal());
        }
        if (StringUtils.isNotBlank(filter.getJenisAktiva())) {
            hql += String.format("AND b.assetType.id = '%s' ", filter.getJenisAktiva());
            hql2 += String.format("AND b.assetType.id = '%s' ", filter.getJenisAktiva());
        }
        if (StringUtils.isNotBlank(filter.getKategoriAktiva())) {
            hql += String.format("AND b.assetType.categoryType.id = '%s' ", filter.getKategoriAktiva());
            hql2 += String.format("AND b.assetType.categoryType.id = '%s' ", filter.getKategoriAktiva());
        }
        if (StringUtils.isNotBlank(filter.getJenisAppraisal())) {
            hql += String.format("AND a.appraisalType.id = '%s' ", filter.getJenisAppraisal());
            hql2 += String.format("AND a.appraisalType.id = '%s' ", filter.getJenisAppraisal());
        }
        if (StringUtils.isNotBlank(filter.getSourceId())) {
            if (StringUtils.equalsIgnoreCase(filter.getSourceId(), "null")) {
                hql += String.format("and a.sourceId is null ", filter.getJenisAppraisal());
                hql2 += String.format("and a.sourceId is null ", filter.getJenisAppraisal());
            } else if (StringUtils.equalsIgnoreCase(filter.getSourceId(), "null_inquiry")){
                hql += String.format("and (a.sourceId is null or  a.sourceStatus>0)", filter.getJenisAppraisal());
                hql2 += String.format("and (a.sourceId is null or a.sourceStatus>0)", filter.getJenisAppraisal());
            }
        }
        String sort =" order by a.orderNumber desc  ";
        return find(pagingInfo, createQuery(hql+ sort), createQuery("select count(a.id) " + hql2) , DataCommonTableDto.class);
    }

    @Override
    public DataCommon getByLpaId(Long lpaId) {
        String hql = "from DataCommon a where a.dataDetail.id=(select b.detail.id from LpaUmum b where b.id=?)";
        return (DataCommon) createQuery(hql).setParameter(0, lpaId).uniqueResult();
    }


    @Override
    public Long copyDebiturFromProcedure(Long id, String createdBy,String colleteralId) {
         SQLQuery query = (SQLQuery)
        getSession().createSQLQuery("select * FROM copy_data_debitur_fix(:idUmum,:createdBy,:colateral)").setParameter("idUmum",id.intValue()).setParameter("createdBy",createdBy).setParameter("colateral",colleteralId);
     List result = query.list();
     if (result!=null && !result.isEmpty()){
         Integer i = (Integer) result.get(0);
         logger.info("hasil Procedure: "+i.longValue());
         return i.longValue();
     }
        return null;
    }



    @Override
    public Integer getJumlahHariPenyelesaian(Date dateSubmit, Date dateClose) {
           SQLQuery query = (SQLQuery)
                getSession().createSQLQuery("select * FROM jumlah_hari(:dateSubmit,:dateClose)").setParameter("dateSubmit",dateSubmit).setParameter("dateClose",dateClose);
        List result = query.list();
        if (result!=null && !result.isEmpty()){
            BigInteger hasil =(BigInteger) result.get(0);
            Integer i = hasil.intValue();
            logger.info("hasil Lama Penyelesaian: "+i);
            return i;
        }
        return 0;
    }

    public PaginatedList<DataCommon> findForTableAdmin (PagingInfo pgInfo, DataCommon filter, OrderSpec order) {

        return find(pgInfo, createQuery("QUERY", order), createQuery("QUERY COUNT"), null);
    }

    @Override
    public PaginatedList<DataCommon> findOrderPending(PagingInfo pgInfo, DataCommon filter, OrderSpec order) {
        Criteria criteria = createCriteria(filter, order);
        String hql = " from DataCommon a  where a.taskList<>a.position";
//        criteria.add(Restrictions.sqlRestriction("task_List<>posisi "));

        if(StringUtils.isNotBlank(filter.getOrderNumber())){
            hql += " and lower(a.orderNumber) like '%"+filter.getOrderNumber()+"%' ";
        }
        if(StringUtils.isNotBlank(filter.getName())){
            hql += " and lower(a.name) like '%" + filter.getName().toLowerCase() + "%'";
        }
        if(StringUtils.isNotBlank(filter.getPosition())){
            hql += " and lower(a.position) like '%" + filter.getPosition().toLowerCase() + "%'";
        }
        if(StringUtils.isNotBlank(filter.getTaskList())){
            hql += " and lower(a.taskList) like '%" + filter.getTaskList().toLowerCase() + "%'";
        }
        if(filter.getCreatedDate()!=null && filter.getClosedDate()!=null) {
            hql += " and a.createdDate between '"+filter.getCreatedDate()+"' and '"+filter.getClosedDate()+"'" ;
        }

        String sort = " order by a.orderNumber desc";
//        return find(pgInfo, criteria, order, null);
        return find(pgInfo, createQuery(hql + sort), createQuery("select count(*) " + hql), null);
    }


}
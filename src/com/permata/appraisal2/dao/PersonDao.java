package com.permata.appraisal2.dao;

import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.common.LabelValueId;
import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.model.Person;

import java.util.Date;
import java.util.List;

public interface PersonDao extends DaoBase<Person, String> {
    public List<LabelValue<String, String>> findLvSurveyor();

    public List<LabelValue<String, String>> personMarketing();

    public List<LabelValueId<String, Boolean, String>> personValuerWithId();

    public List<LabelValue<String, String>> findMarketing(Long branchId);

    public List<LabelValue<String,String>> findUserAppraisal(Enum divisi);


    public void active(String userId,boolean action) throws Exception;

    public void inActive(String userId,boolean action) throws Exception;

    public void undelete(String userId) throws Exception;


    public PaginatedList<Person> findDelete(PagingInfo pagingInfo, String userId);

    public PaginatedList<Person> findByAppBranch(PagingInfo pagingInfo, Long id);

    public PaginatedList<Person> findByBranch(PagingInfo pagingInfo, Long id);
    PaginatedList<Person> getPersonActvity(Person sample, PagingInfo pagingInfo, OrderSpec orderSpec);

}
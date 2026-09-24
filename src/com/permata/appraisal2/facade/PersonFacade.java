package com.permata.appraisal2.facade;

import com.permata.appraisal2.common.LabelValue;

import com.permata.appraisal2.common.LabelValueId;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.PersonDto;
import com.permata.appraisal2.dto.PersonLoginDto;
import com.permata.appraisal2.dto.PersonRoleDto;
import com.permata.appraisal2.model.Person;
import com.permata.appraisal2.model.PersonLevel;

import java.util.Date;
import java.util.List;

public interface PersonFacade {

    public PersonDto save(PersonDto dto);

    Person save(Person person);

    public PersonRoleDto saveRole(PersonRoleDto dto);


    public void delete(String userId, String deletedBy) throws Exception;

    public void undelete(String userId) throws Exception;

    public void active(String userId,boolean act,String activeBy,Date activeDated) throws Exception ;

    public void inActive(String userId,boolean act,String inActivedBy,Date inActiveDated) throws Exception ;

    public void deleteRole(Long id) throws Exception;

    public PaginatedList<PersonDto> find(PersonDto filter, PagingInfo pagingInfo);

    public PaginatedList<PersonDto> findDelete(PersonDto filter, PagingInfo pagingInfo);

    public PaginatedList<PersonRoleDto> findRole(String userId, PagingInfo pagingInfo);

    public PaginatedList<PersonDto> findByAppBranch(Long id, PagingInfo pagingInfo);

    public PaginatedList<PersonDto> findByBranch(Long id, PagingInfo pagingInfo);

    public PersonDto get(String userId);

    public PersonRoleDto getRole(Long id);

    public PersonLoginDto getPersonLogin(String userId);

    public List<LabelValue<String, String>> findLvSurveyor();

    public List<LabelValue<String, String>> personMarketing();

    public List<LabelValueId<String, Boolean, String>> personValuerWithId();

    public List<LabelValue<String, String>> findMarketing(Long branchId);

    public List<LabelValue<String,String>> findUserAppraisal(Enum divisi);

//    public PaginatedList<AppraisalBranchDto> find(PersonDto dto, PagingInfo pagingInfo);
public boolean existsRole(String userId, PersonLevel level,Long id);

    public boolean existsUserid(String userid);
}

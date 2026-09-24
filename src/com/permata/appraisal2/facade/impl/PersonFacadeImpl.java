package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.common.LabelValueId;
import com.permata.appraisal2.dao.*;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.PersonDto;
import com.permata.appraisal2.dto.PersonLoginDto;
import com.permata.appraisal2.dto.PersonRoleDto;
import com.permata.appraisal2.facade.PersonFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.Person;
import com.permata.appraisal2.model.PersonLevel;
import com.permata.appraisal2.model.PersonRole;
import com.permata.appraisal2.model.SecurityLog;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PersonFacadeImpl extends FacadeBase<PersonDto, Person> implements PersonFacade {

    @Autowired
    private PersonDao dao;
    @Autowired
    private PersonRoleDao personRoleDao;
    @Autowired
    private BranchDao branchDao;
    @Autowired
    private AppraisalBranchDao appraisalBranchDao;


    @Autowired
    private SecurityLogDao securityLogDao;

    @Override
    @Transactional
    public PersonDto save(PersonDto dto) {
        return toWrapper(dao.save(toEntity(dto)));
    }

    @Override
    @Transactional
    public Person save(Person person) {
        return dao.save(person);
    }

    @Override
    public boolean existsUserid(String userid) {
        Person person = dao.get(userid);
        if(person != null){
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void delete(String userId, String deletedBy) throws Exception {
        Person p = dao.delete(userId);

    }



    @Override
    @Transactional
    public void undelete(String userId) throws Exception {
        dao.undelete(userId);
    }


    @Override
    @Transactional
    public void active(String userId,boolean act,String activedBy,Date activeDated) throws Exception {
        dao.active(userId,act);
    }


    @Override
    @Transactional
    public void inActive(String userId,boolean act,String inActivedBy,Date inActiveDated) throws Exception {
        dao.inActive(userId, act);
    }


    @Override
    public PaginatedList<PersonDto> findDelete(PersonDto filter, PagingInfo pagingInfo) {
        PaginatedList<Person> pgList = dao.findDelete(pagingInfo, filter.getUserId());
        return pgList.cloneAndUpdateList(toListWrapperPersonDeleted(pgList.getDataList()));
    }



    @Override
    public PaginatedList<PersonDto> find(PersonDto filter, PagingInfo pagingInfo) {
        PaginatedList<Person> pgList = dao.find(pagingInfo, toEntitySearch(filter));
//        PaginatedList<Person> pgList = dao.getPersonActvity(toEntity(filter), pagingInfo, null);
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }

    @Override
    public PaginatedList<PersonDto> findByAppBranch(Long id, PagingInfo pagingInfo){
        PaginatedList<Person> pgList = dao.findByAppBranch(pagingInfo, id);
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }

//    @Override
//    public PaginatedList<PersonRoleDto> findByBranch(Long id, PagingInfo pagingInfo){
//        PaginatedList<PersonRole> pgList = personRoleDao.findByBranch(pagingInfo, id);
//        return pgList.cloneAndUpdateList(toPersonRoleDto(pgList.getDataList()));
//    }

    @Override
    public PaginatedList<PersonDto> findByBranch(Long id, PagingInfo pagingInfo){
        PaginatedList<Person> pgList = dao.findByBranch(pagingInfo, id);
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }

    @Override
    public PaginatedList<PersonRoleDto> findRole(String userId, PagingInfo pagingInfo) {
        PaginatedList<PersonRole> pgList = personRoleDao.findByUserId(pagingInfo, userId);
        return pgList.cloneAndUpdateList(toPersonRoleDto(pgList.getDataList()));
    }

//    @Override
//    public PaginatedList<PersonDto> findByBranch(Long id, PagingInfo pagingInfo) {
//        PaginatedList<Person> pgList = dao.findByBranch(pagingInfo, id);
//        logger.info("dao.findByBranch(pagingInfo, id) " + pgList.getTotalRowCount());
////        for (int i= 0 ; i< pgList.getTotalRowCount();i++){
////            logger.info("dao.findByBranch(pagingInfo, id) "+pgList.getDataList().get(i));
////        }
//        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
//    }

    @Override
    public List<LabelValue<String, String>> findLvSurveyor() {
        return dao.findLvSurveyor();
    }

    @Override
    public List<LabelValue<String, String>> personMarketing() {
        return dao.personMarketing();
    }

    @Override
    public List<LabelValue<String, String>> findMarketing(Long branchId){
        return dao.findMarketing(branchId);
    }

    @Override
    public List<LabelValue<String,String>> findUserAppraisal(Enum divisi){
        return dao.findUserAppraisal(divisi);
    }

    @Override
    public List<LabelValueId<String, Boolean, String>> personValuerWithId(){
        return  dao.personValuerWithId();
    }

//    public PaginatedList<PersonDto> find(PersonDto dto, PagingInfo pagingInfo) {
//        PaginatedList<Person> pgList = dao.find(pagingInfo, toEntity(dto));
//        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
//    }

    @Override
    public PersonLoginDto getPersonLogin(String userId) {
        Person person = dao.get(userId);
        PersonLoginDto personLogin = new PersonLoginDto();
        BeanUtils.copyProperties(person, personLogin, "roles");

        for (PersonRole role : person.getRoles()) {
            PersonRoleDto dto = new PersonRoleDto();
            if (role.getLevel().equals(PersonLevel.MARKETING)) {
                dto.setBranchId(role.getBranch().getId());
                dto.setBranchName(role.getBranch().getName());
                if (role.getBranch().getGroup() != null) {
                    dto.setGroupId(role.getBranch().getGroup().getId());
                    dto.setGroupName(role.getBranch().getGroup().getName());
                }
            } else {
                dto.setLimitApproval(role.getLimitApproval());
                if(role.getAppBranch() != null) {
                    dto.setAppBranchId(role.getAppBranch().getId());
                    dto.setAppBranchName(role.getAppBranch().getName());
                }
            }
            dto.setLevel(role.getLevel());
            dto.setId(role.getId());
            personLogin.addRole(dto);
        }
        return personLogin;
    }

    @Override
    public PersonDto get(String userId) {
        Person person = dao.get(userId);
        return toWrapper(person);
    }

    @Override
    @Transactional
    public PersonRoleDto saveRole(PersonRoleDto dto) {
        return toPersonRoleDto(personRoleDao.save(toEntityPersonRole(dto)));
    }

    @Override
    @Transactional
    public void deleteRole(Long id) throws Exception {
        personRoleDao.delete(id);
    }

    @Override
    public PersonRoleDto getRole(Long id) {
        return toPersonRoleDto(personRoleDao.get(id));
    }



    private String[] enclose(String[] ss,String quote) {
        String[] strings = new String[ss.length];
        for (int i = 0; i < ss.length; i++) {
            strings[i] = enclose22(ss[i],quote);
        }
        return strings;
    }

    private String enclose22(String s,String q) {
        return q + s + q;
    }

    @Override
    public PersonDto toWrapper(Person e) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        PersonDto w = new PersonDto();
        BeanUtils.copyProperties(e, w,"deletedBy");
        w.setUpdate(true);
        w.setStatusAktif("AKTIF");

        if (e.getLastLogin()!=null){

            w.setDateJsonDate(format.format(e.getLastLogin()));
        }

        if (e.getInactive()!=null){
            if (e.getInactive()){
                w.setStatusAktif("TIDAK AKTIF");
            }

        }


        if (e.getDeleted()!=null && e.getDeleted()==1){
            w.setStatusDelete("DELETED");
        }




        if (e.getRoles()!=null && !e.getRoles().isEmpty()){
           int size = e.getRoles().size();
           String[] level = new String[size];
            for (int i = 0; i <size ; i++) {
                level[i]=e.getRoles().get(i).getLevel().toString();
            }
            w.setUserRole(StringUtils.join(enclose(level, ""), ", "));
        }


        SecurityLog s = securityLogDao.getLogDetail(e.getUserId(),61l);
            if (s != null){
                w.setDeletedBy(s.getKeterangan());
             //   w.setDeletedDateString(format.format(s.getDateTime());
            }


        //Buat validasi di LogSecurity biar gk ada tulisan 'null'
        if (e.getName()==null){
            w.setName("");
        }

        if (e.getEmail()==null){
            w.setEmail("");
        }

        if (e.getHp()==null){
            w.setHp("");
        }

        if (e.getHpAtasan()==null){
            w.setHpAtasan("");
        }

        if (e.getPhone()==null){
            w.setPhone("");
        }

        return w;
    }

    @Override
    public Person toEntity(PersonDto w) {
        Person e = new Person();
        if (StringUtils.isNotBlank(w.getUserId())) {
            e = dao.get(w.getUserId());
        }
        if (e == null) {
            e = new Person();
        }
        BeanUtils.copyProperties(w, e);
        return e;
    }

    public Person toEntitySearch(PersonDto w) {
        Person e = new Person();
        BeanUtils.copyProperties(w, e);
        return e;
    }

    private List<PersonRoleDto> toPersonRoleDto(List<PersonRole> listE) {
        List<PersonRoleDto> listW = new ArrayList<PersonRoleDto>();
        for (PersonRole e : listE) {
            listW.add(toPersonRoleDto(e));
        }
        return listW;
    }


    private PersonRoleDto toPersonRoleDto(PersonRole e) {
        PersonRoleDto w = new PersonRoleDto();
        BeanUtils.copyProperties(e, w);
        w.setUserId(e.getPerson().getUserId());
        if (e.getBranch() != null) {
            w.setBranchId(e.getBranch().getId());
            w.setBranchName(e.getBranch().getName());
            if (e.getBranch().getGroup() != null) {
                w.setGroupId(e.getBranch().getGroup().getId());
                w.setGroupName(e.getBranch().getGroup().getName());
            }
        }else{
            w.setBranchName("");
        }
        if (e.getAppBranch() != null) {
            w.setAppBranchId(e.getAppBranch().getId());
            w.setAppBranchName(e.getAppBranch().getName());
        }else{
            w.setAppBranchName("");
        }

        if (e.getLimitApproval()==null){
            w.setLimitApproval(BigDecimal.ZERO);
        }
        return w;
    }

    private PersonRole toEntityPersonRole(PersonRoleDto w) {
        String[] lvArr = new String[]{"Appraisal Admin","Appraisal Approval","Appraisal Valuer","Appraisal Surveyor"};
        PersonRole e = new PersonRole();
        if (w.getId() != null) {
            e = personRoleDao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);



        if (w.getBranchId() != null) {
            e.setBranch(branchDao.get(w.getBranchId()));
        }
        if (w.getAppBranchId() != null) {
            e.setAppBranch(appraisalBranchDao.get(w.getAppBranchId()));
        }


        if (!w.getLevel().getValue().equals("Marketing")){
            e.setBranch(null);
        }

        if (!Arrays.asList(lvArr).contains(w.getLevel().getValue())){
            e.setAppBranch(null);
        }

        e.setPerson(dao.get(w.getUserId()));
        return e;
    }

    @Override
    public boolean existsRole(String userId, PersonLevel level, Long id) {
        PaginatedList<PersonRole> pgList = personRoleDao.findByUserId(PagingInfo.createToRetrieveAll(), userId);
        for (PersonRole r : pgList.getDataList()) {
            if (r.getLevel().equals(level)  && r.getId()!=id) {
                return true;
            }
        }
        return false;
    }

    private List<PersonDto> toListWrapperPersonDeleted(List<Person> listE) {
        List<PersonDto> listDto = new ArrayList<PersonDto>();
        for (Person e : listE) {
            listDto.add(toDeletedPersonDto(e));
        }
        return listDto;
    }

    private PersonDto toDeletedPersonDto(Person e) {
        PersonDto w = new PersonDto();
        BeanUtils.copyProperties(e, w);
        SecurityLog secLog = new SecurityLog();
        secLog.setUserAffected(e.getUserId());
        secLog.setLogCode(new Long(7));
        List<SecurityLog> secLogs = securityLogDao.find(secLog, OrderSpec.create("id", OrderSpec.OrderType.desc));
        if(secLogs.size()>0) {
            SecurityLog log = secLogs.get(0);
            w.setDeletedBy(log.getPerson().getName());
            w.setDeletedDate(log.getDateTime());
        }
        return w;
    }

}

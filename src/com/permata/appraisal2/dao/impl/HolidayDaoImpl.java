package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.HolidayDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.HolidayDto;
import com.permata.appraisal2.model.Holiday;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class HolidayDaoImpl extends DaoBaseImpl<Holiday, Long> implements HolidayDao {
    @Override
    public PaginatedList<Holiday> find(PagingInfo pagingInfo, HolidayDto filter, OrderSpec order){
        Criteria criteria = createCriteria(Holiday.class, order);
        if(StringUtils.isNotBlank(filter.getKeterangan())) {
            criteria.add(Restrictions.ilike("keterangan", filter.getKeterangan(), MatchMode.ANYWHERE));
        }
        if(filter.getTanggal()!=null && filter.getTanggal_end()!=null) {
            criteria.add(Restrictions.between("tanggal", filter.getTanggal(), filter.getTanggal_end()));
        }

        return find(pagingInfo, criteria, order, null);
    }

    @Override
    public Long findDuplicate(Holiday holiday) {
        Criteria criteria = createCriteria(Holiday.class);
        if(holiday.getId()!=null) {
            criteria.add(Restrictions.and(Restrictions.eq("tanggal", holiday.getTanggal()), Restrictions.ne("id", holiday.getId())));
        } else {
            criteria.add(Restrictions.eq("tanggal", holiday.getTanggal()));
        }
        return getRowCount(criteria);
    }
}
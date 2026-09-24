package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.TaskHistoryDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.model.TaskHistory;
import org.springframework.stereotype.Repository;

@Repository
public class TaskHistoryDaoImpl extends DaoBaseImpl<TaskHistory, Long> implements TaskHistoryDao {
}

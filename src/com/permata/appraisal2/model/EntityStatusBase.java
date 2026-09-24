package com.permata.appraisal2.model;

import com.permata.appraisal2.dao.common.SoftDeleteSupport;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class EntityStatusBase<ID extends Serializable> extends EntityBase<ID> implements SoftDeleteSupport {

	public static final Integer DEFAULT = null;
	public static final Integer YES = 1;
	public static final Integer NO = 0;
	public static final Integer UNKNOWN = 3;

	private static final long serialVersionUID = -5786093558442175191L;
	// private String statusData;
	private Integer deleted;

//  @Column(name = "STATUS_DATA")
//	public String getStatusData() {
//		return statusData;
//	}
//
//	public void setStatusData(String statusData) {
//		this.statusData = statusData;
//	}

	@Override
	@Column(name = "DELETED", length = 1)
	public Integer getDeleted() {
		return deleted;
	}

	@Override
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
}
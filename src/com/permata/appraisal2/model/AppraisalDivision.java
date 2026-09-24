package com.permata.appraisal2.model;

import com.permata.appraisal2.common.AppConstans;

import javax.persistence.*;

@Entity
@Table(name = AppConstans.HibernateConstanstan.PREFIX_DB_NAME + "APPRAISAL_DIVISI")
public class AppraisalDivision extends EntityBase<Long> {

	private static final long serialVersionUID = 6865659869356867138L;
	private Long id;
	private String name;
	private AppraisalBranch branch;

	@Id
	@GeneratedValue(generator = "APPRAISAL_DIVISI_SEQ", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "APPRAISAL_DIVISI_SEQ", sequenceName = "APPRAISAL_DIVISI_SEQ", initialValue = 1, allocationSize = 1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NAMA", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "APPRAISAL_CABANG_ID")
	public AppraisalBranch getBranch() {
		return branch;
	}

	public void setBranch(AppraisalBranch branch) {
		this.branch = branch;
	}

}
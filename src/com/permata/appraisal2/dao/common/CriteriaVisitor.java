package com.permata.appraisal2.dao.common;

import org.hibernate.Criteria;

import java.io.Serializable;

public interface CriteriaVisitor<ET extends Serializable> {
	public Criteria visit(final Criteria sourceCriteria, ET sample, String... excludedProperties);
}
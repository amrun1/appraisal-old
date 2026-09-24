package com.permata.appraisal2.dao.common;

import com.permata.appraisal2.model.EntityBase;

import java.io.Serializable;
import java.util.List;

public interface DaoBase<ET extends EntityBase<ID>, ID extends Serializable> {
	public ET get(ID id);

	public ET save(ET entity);

	public ET delete(ID id);

	public List<ET> find(ET sample, String... excludedProperties);

	public List<ET> find(ET sample, OrderSpec orderSpec, String... excludedProperties);

	public PaginatedList<ET> find(PagingInfo pagingInfo, ET sample, String... excludedProperties);

	public PaginatedList<ET> find(PagingInfo pagingInfo, ET sample, OrderSpec orderSpec, String... excludedProperties);

	public PaginatedList<ET> findExact(PagingInfo pagingInfo, ET sample, String... excludedProperties);

	public List<ET> findExact(ET sample, String... excludedProperties);

	public boolean isExists(ID id);

	public boolean detach(ET sample);
}
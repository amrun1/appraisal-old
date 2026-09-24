package com.permata.appraisal2.dao.common;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;

import java.io.Serializable;

public class PagingInfo implements Serializable {

	private static final long serialVersionUID = 1393248197357128553L;

	private static final int DEFAULT_PAGE_SIZE = 10;

	private int currentPage;

	private int pageCount;

	private int pageSize;

	private boolean retrieveAll = false;

	// private int firstRowIndex;
	// private int lastRowIndex;

	public PagingInfo() {

	}

	private PagingInfo(boolean retrieveAll) {
		this.retrieveAll = retrieveAll;
	}

	public PagingInfo(int currentPage) {
		this(currentPage, DEFAULT_PAGE_SIZE);
	}

	public PagingInfo(int currentPage, int pageSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getFirstRowIndex() {
		return (getCurrentPage() - 1) * getPageSize();
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setRetrieveAll(boolean retriveAll) {
		this.retrieveAll = retriveAll;
	}

	public static PagingInfo create() {
		return create("1", DEFAULT_PAGE_SIZE);
	}

	public static PagingInfo create(String page) {
		return create(page, DEFAULT_PAGE_SIZE);
	}

	public static PagingInfo create(String page, int pageSize) {
		int pageNum = !StringUtils.isEmpty(page) ? Integer.parseInt(page) : 1;
		if (pageNum < 1)
			pageNum = 1;
		return new PagingInfo(pageNum, pageSize);
	}

	public static PagingInfo createToRetrieveAll() {
		return new PagingInfo(true);
	}

	public boolean isRetrieveAll() {
		return retrieveAll;
	}

	public int getLastRowIndex() {
		if (retrieveAll) {
			return 1000;
		}
		return getFirstRowIndex() + getPageSize() - 1;
	}

	/**
	 * @see Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof PagingInfo)) {
			return false;
		}
		PagingInfo rhs = (PagingInfo) object;
		return new EqualsBuilder().appendSuper(super.equals(object)).append(this.pageCount, rhs.pageCount)
				.append(this.pageSize, rhs.pageSize).append(this.currentPage, rhs.currentPage).isEquals();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PagingInfo [currentPage=");
		builder.append(currentPage);
		builder.append(", pageCount=");
		builder.append(pageCount);
		builder.append(", pageSize=");
		builder.append(pageSize);
		builder.append(", retrieveAll=");
		builder.append(retrieveAll);
		builder.append("]");
		return builder.toString();
	}

}
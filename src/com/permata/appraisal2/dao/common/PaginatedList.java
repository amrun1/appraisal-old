package com.permata.appraisal2.dao.common;

import com.permata.appraisal2.common.DataTables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PaginatedList<T> implements Serializable {

    private static final long serialVersionUID = -6663331206626673025L;

    private List<T> dataList;

    private PagingInfo pagingInfo;

    private Long totalRowCount;

    public PaginatedList() {
    }

    public static <T> PaginatedList<T> createEmptyList() {
        return new PaginatedList<T>(new ArrayList<T>(), PagingInfo.create(null));
    }

    public boolean isEmpty() {
        return dataList.isEmpty();
    }

    public PaginatedList(PagingInfo pagingInfo) {
        this(new ArrayList<T>(), pagingInfo, 0L);
    }

    public PaginatedList(List<T> dataList, PagingInfo pagingInfo) {
        this(dataList, pagingInfo, new Long(dataList.size()));
    }

    public PaginatedList(List<T> dataList, PagingInfo pagingInfo, Long totalRowCount) {
        this.dataList = dataList;
        this.pagingInfo = pagingInfo;
        setTotalRowCount(totalRowCount);
    }

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public Long getTotalRowCount() {
        return totalRowCount;
    }

    public <D> PaginatedList<D> cloneAndUpdateList(List<D> newList) {
        return new PaginatedList<D>(newList, getPagingInfo(), getTotalRowCount());
    }

    public void setTotalRowCount(Long totalRowCount) {
        this.totalRowCount = totalRowCount;
        if (pagingInfo.isRetrieveAll())
            pagingInfo.setPageCount(totalRowCount.intValue());
        else
            pagingInfo.setPageCount((int) (Math.ceil((float) totalRowCount / pagingInfo.getPageSize())));

    }

    public DataTables<T> cloneToDataTable() {
        DataTables<T> dataTables = new DataTables<T>();
        dataTables.setAaData(getDataList());
        dataTables.setiTotalDisplayRecords(getTotalRowCount());
        dataTables.setiTotalRecords(getTotalRowCount());
        return dataTables;
    }
}
package com.cg.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PagedResult {

    private int page;			// 当前页数
    private int total;			// 总页数
    private long records;		// 总记录数
    private List<?> rows;		// 每行显示的内容

    public PagedResult() {
        this.page = 1;
        this.total = 0;
        this.records = 0;
        this.rows = new ArrayList<>();
    }

    public PagedResult(int page, int total, long records, List<?> rows) {
        this.page = page;
        this.total = total;
        this.records = records;
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public int getTotal() {
        return total;
    }

    public long getRecords() {
        return records;
    }

    public List<?> getRows() {
        return rows;
    }

    public PagedResult setPage(int page) {
        this.page = page;
        return this;
    }

    public PagedResult setTotal(int total) {
        this.total = total;
        return this;
    }

    public PagedResult setRecords(long records) {
        this.records = records;
        return this;
    }

    public PagedResult setRows(List<?> rows) {
        this.rows = rows;
        return this;
    }

    public static Integer countTotal(Integer records, Integer pageSize) {
        return  (records / pageSize) + ((records % pageSize) != 0 ? 1 : 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PagedResult that = (PagedResult) o;
        return page == that.page &&
                total == that.total &&
                records == that.records &&
                Objects.equals(rows, that.rows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, total, records, rows);
    }

    @Override
    public String toString() {
        return "PagedGridResult{" +
                "page=" + page +
                ", total=" + total +
                ", records=" + records +
                ", rows=" + rows +
                '}';
    }
}

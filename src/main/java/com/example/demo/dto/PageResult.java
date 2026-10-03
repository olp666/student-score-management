package com.example.demo.dto;

import java.util.List;

public class PageResult<T> {

    private final List<T> records;
    private final long total;
    private final int page;
    private final int size;
    private final int totalPages;

    public PageResult(List<T> records,
                      long total,
                      int page,
                      int size,
                      int totalPages) {
        this.records = records;
        this.total = total;
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
    }

    public List<T> getRecords() {
        return records;
    }

    public long getTotal() {
        return total;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
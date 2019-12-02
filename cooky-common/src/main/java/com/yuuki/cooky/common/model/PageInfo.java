package com.yuuki.cooky.common.model;

import lombok.Data;

@Data
public class PageInfo<T> {

    /**
     * 排序字符串
     * eg: sort=field1,-field2
     */
    private String sort;
    /**
     * 当前页
     */
    private int cur;
    /**
     * 每页条数
     */
    private int size;

    public void setPage(int cur) {
        if (cur == 0) {
            cur = 1;
        }
        this.cur = cur;
    }

    public void setLimit(int size) {
        if (size == 0) {
            size = 10;
        }
        this.size = size;
    }
}

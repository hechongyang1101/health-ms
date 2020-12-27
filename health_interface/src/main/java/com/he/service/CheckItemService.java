package com.he.service;

import com.he.entity.PageResult;
import com.he.pojo.CheckItem;

public interface CheckItemService {
    public void add(CheckItem checkItem);

    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

}

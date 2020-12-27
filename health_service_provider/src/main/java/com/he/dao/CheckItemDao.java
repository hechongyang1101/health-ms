package com.he.dao;

import com.github.pagehelper.Page;
import com.he.pojo.CheckItem;

public interface CheckItemDao {
    public void add(CheckItem checkItem);

    public Page<CheckItem> selectByCondition(String queryString);
}

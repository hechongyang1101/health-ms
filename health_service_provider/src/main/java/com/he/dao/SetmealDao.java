package com.he.dao;

import com.github.pagehelper.Page;
import com.he.pojo.Setmeal;

import java.util.Map;

public interface SetmealDao {
    public void add(Setmeal setmeal);

    public void setSetmealAndCheckGroup(Map<String, Integer> map);

    public Page<Setmeal> selectByCondition(String queryString);
}

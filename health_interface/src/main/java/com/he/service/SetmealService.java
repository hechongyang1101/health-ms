package com.he.service;

import com.he.entity.PageResult;
import com.he.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealService {
    public void add(Setmeal setmeal, Integer[] checkgroupIds);

    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    public List<Setmeal> findAll();

    public Setmeal findById(int id);

    public List<Map<String, Object>> findSetmealCount();
}

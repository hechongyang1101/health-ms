package com.he.service;

import com.he.entity.PageResult;
import com.he.pojo.CheckGroup;

import java.util.List;

/**
 * 检查组服务接口
 */
public interface CheckGroupService {
    public void add(CheckGroup checkGroup,Integer[] checkitemIds);

    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    public CheckGroup findById(Integer id);

    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    public void edit(CheckGroup checkGroup, Integer[] checkitemIds);

    public List<CheckGroup> findAll();
}
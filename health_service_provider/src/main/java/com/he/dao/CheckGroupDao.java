package com.he.dao;
import com.github.pagehelper.Page;
import com.he.pojo.CheckGroup;
import com.he.pojo.CheckItem;

import java.util.List;
import java.util.Map;
/**
 * 持久层Dao接口
 */
public interface CheckGroupDao {
    public void add(CheckGroup checkGroup);

    public void setCheckGroupAndCheckItem(Map map);

    public Page<CheckGroup> selectByCondition(String queryString);

    public CheckGroup findById(Integer id);

    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    public void deleteAssociation(Integer id);

    public void edit(CheckGroup checkGroup);

    public List<CheckGroup> findAll();
}

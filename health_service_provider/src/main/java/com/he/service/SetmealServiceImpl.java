package com.he.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.he.constant.RedisConstant;
import com.he.dao.CheckGroupDao;
import com.he.dao.SetmealDao;
import com.he.entity.PageResult;
import com.he.pojo.CheckGroup;
import com.he.pojo.Setmeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 体检套餐服务实现类
 */
@Service
@Transactional
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealDao setmealDao;

    @Autowired
    private JedisPool jedisPool;
    //新增套餐
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealDao.add(setmeal);
        if(checkgroupIds != null && checkgroupIds.length > 0){
            setSetmealAndCheckGroup(setmeal.getId(),checkgroupIds);
        }
        //将图片名称保存到Redis
        savePic2Redis(setmeal.getImg());
    }

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<Setmeal> page = setmealDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public List<Setmeal> findAll() {
        return setmealDao.findAll();
    }

    @Override
    public Setmeal findById(int id) {
        return setmealDao.findById(id);
    }

    //将图片名称保存到Redis
    private void savePic2Redis(String pic){
        Jedis jedis = jedisPool.getResource();
        jedis.sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,pic);
        jedis.close();
    }
    //绑定套餐和检查组的多对多关系
    private void setSetmealAndCheckGroup(Integer id, Integer[] checkgroupIds) {
        for (Integer checkgroupId : checkgroupIds) {
            Map<String,Integer> map = new HashMap<>();
            map.put("setmealId",id);
            map.put("checkgroupId",checkgroupId);
            setmealDao.setSetmealAndCheckGroup(map);
        }
    }
}


package com.he.jobs;

import com.he.constant.RedisConstant;
import com.he.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * 自定义Job，实现定时清理垃圾图片
 */
public class ClearImgJob {
    @Autowired
    private JedisPool jedisPool;
    public void clearImg(){
        Jedis jedis = jedisPool.getResource();
        //根据Redis中保存的两个set集合进行差值计算，获得垃圾图片名称集合
        Set<String> set =
                jedis.sdiff(RedisConstant.SETMEAL_PIC_RESOURCES,
                        RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        if(set != null){
            for (String picName : set) {
                System.out.println("定时任务从七牛云删除图片");
                //删除七牛云服务器上的图片
                QiniuUtils.deleteFileFromQiniu(picName);
                //从Redis集合中删除图片名称
                System.out.println("定时任务从redis删除图片");
                jedis.srem(RedisConstant.SETMEAL_PIC_RESOURCES,picName);
            }
        }
        jedis.close();
    }
}

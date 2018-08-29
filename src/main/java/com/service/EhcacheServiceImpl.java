package com.service;

import com.pojo.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EhcacheServiceImpl implements EhcacheService{
    //value的值和ehcache.xml中的配置保持一致
    @Cacheable(value = "HelloWorldCache",key = "#param")
    @Override
    public String getTimestamp(String param) {
        Long timestamp=System.currentTimeMillis();
        System.out.println("调用的是方法"+timestamp);
        return timestamp.toString();
    }
    @Cacheable(value = "HelloWorldCache",key = "#key")
    @Override
    public String getDataFromDB(String key) {
        System.out.println("从数据库中获取数据...");
        return key+":"+String.valueOf(Math.round(Math.random()*1000000));
    }

    //@CacheEvict表明所修饰的方法是用来删除失效或无用的缓存数据。
    @CacheEvict(value = "HelloWorldCache",key = "#key")
    @Override
    public void removeDataAtDB(String key) {
        System.out.println("从数据库中删除数据...");
    }

    @CachePut(value = "HelloWorldCache",key = "#key")
    @Override
    public String refreshData(String key) {
        System.out.println("模拟从数据库加载数据");
        return key+"::"+String.valueOf(Math.random()*1000000);
    }

    @Cacheable(value="UserCache", key="'user:' + #userId")
    @Override
    public User findById(String userId) {
        System.out.println("模拟从数据库中查询数据");
        return (User) new User("1", "mengdee");
    }



    @Cacheable(value="UserCache", condition="#userId.length()<12")
    @Override
    public boolean isReserved(String userId) {
        System.out.println(userId.length());
        System.out.println("UserCache:"+userId);
        return false;
    }

    //清除掉UserCache中某个指定key的缓存
    //@CacheEvict表明所修饰的方法是用来删除失效或无用的缓存数据。
    @CacheEvict(value="UserCache",key="'user:' + #userId")
    @Override
    public void removeUser(String userId) {
        System.out.println("UserCache remove:"+ userId);
    }

    //清除掉UserCache中全部的缓存
    @CacheEvict(value="UserCache", allEntries=true)
    @Override
    public void removeAllUser() {
        System.out.println("UserCache delete all");
    }


}

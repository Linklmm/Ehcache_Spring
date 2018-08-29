package com.test;

import com.pojo.User;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheTest {
    public static void main(String[] args) {
        //1.创建缓存管理器
        CacheManager cacheManager=CacheManager.create("./src/main/resources/ehcache.xml");
        //2.获取缓存对象
        Cache cache=cacheManager.getCache("HelloWorldCache");
        //3.创建元素
        Element element=new Element("key1","value1");
        //4.将元素添加到缓存
        cache.put(element);
        //5.获取缓存
        Element value=cache.get("key1");
        System.out.println(value);
        System.out.println(value.getObjectValue());

        //6.删除元素
        cache.remove("key1");

        User user=new User("201808280953","杨迪");
        Element element2=new Element("yangdi",user);
        cache.put(element2);
        Element value2=cache.get("yangdi");
        User user2= (User) value2.getObjectValue();
        System.out.println(user2);
        System.out.println(user2.getUserid());

        System.out.println(cache.getSize());
        //7.刷新缓存
        cache.flush();
        //8.关闭缓存管理器
        cacheManager.shutdown();
    }
}

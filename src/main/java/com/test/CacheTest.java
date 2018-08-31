package com.test;

import com.pojo.User;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
/**
 * 每个CacheManager都管理着多个Cache。而每个Cache都以一种类Hash的方式，关联着多个Element。
 * 而Element则是我们用于存放要缓存内容的地方
 * 有两种模式来创建CacheManager,单例(Singleton)和多例(Instance)
 * 单例创建CacheManager是指通过CacheManager.getInstance()方法或者无参的构造函数
 * CacheManager创建一个CacheManager对象ex：CacheManager manager=new CacheManager();manager.addCache("指定的缓存name");
 * */
public class CacheTest {
    public static void main(String[] args) {
        //1.创建缓存管理器
        // 根据ehcache.xml配置文件新建出一个CacheManager
        CacheManager cacheManager=CacheManager.create("./src/main/resources/ehcache.xml");
        //2.获取缓存对象，每个缓存的name属性进行区分
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

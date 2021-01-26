package com.shiro.common.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;


/**
 * @Author jianglanglang
 * @Date 2021/1/22 14:13
 * @Description EhCache缓存工具类
 */
public class EhCacheUtil {
    private static final Logger log = LoggerFactory.getLogger(EhCacheUtil.class);

    private static URL url;
    private static final String path = "/config/ehcache-shiro.xml";
    private static CacheManager cacheManager;
    private static EhCacheUtil ehCacheUtil;

    /**
     * 获得缓存配置管理
     * @param path
     */
    private EhCacheUtil(String path) {
        url = getClass().getResource(path);
        cacheManager = CacheManager.create(url);
    }

    /**
     * 初始化缓存管理类
     * @return
     */
    public static EhCacheUtil getInstance() {
        if (ehCacheUtil== null) {
            ehCacheUtil= new EhCacheUtil(path);
        }
        return ehCacheUtil;
    }

    /**
     * 获得一个Cache 没有则创建一个。
     * @param cacheName
     * @return
     */
    private static Cache getCache(String cacheName){
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null){
            cacheManager.addCache(cacheName);
            cache = cacheManager.getCache(cacheName);
            cache.getCacheConfiguration().setEternal(true);
        }
        return cache;
    }

    /**
     * 添加缓存数据
     * @param cacheName
     * @param key
     * @param value
     */
    public static void put(String cacheName, String key, Object value) {
        Element element = new Element(key, value);
        getCache(cacheName).put(element);
    }

    /**
     * 获取缓存数据
     * @param cacheName
     * @param key
     * @return
     */
    public static Object get(String cacheName, String key) {
        Element element = getCache(cacheName).get(key);
        return element == null ? null : element.getObjectValue();
    }

    /**
     * 删除缓存数据
     * @param cacheName
     * @param key
     */
    public static void remove(String cacheName, String key) {
        getCache(cacheName).remove(key);
    }

    /**
     *  删除所有缓存数据
     * @param cacheName
     */
    public static void removeAll(String cacheName) {
        getCache(cacheName).removeAll();
    }
}
package com.shiro.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * @Author jianglanglang
 * @Date 2021/1/15 16:52
 * @Description
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.shiro.logical.*.dao")
public class MyBatisPlusConfig {

    /**
     * mybatisPlus分页插件
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }


    /**
     * PageHelper分页插件配置
     * @return
     */
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        // 默认值为 false，该参数对使用 RowBounds 作为分页参数时有效。
        properties.setProperty("offsetAsPageNum","true");
        // 默认值为 false，RowBounds是否进行count查询
        properties.setProperty("rowBoundsWithCount","true");
        // 默认是true，如果不设置为false，分页的时候如果翻到没有数据的页数的时候，返回最后有数据的那一页
        properties.setProperty("reasonable","false");
        // 配置mysql数据库的方言
        properties.setProperty("dialect","mysql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }


}
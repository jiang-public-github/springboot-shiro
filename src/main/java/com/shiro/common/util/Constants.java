package com.shiro.common.util;

import java.io.Serializable;

/**
 * @Author jianglanglang
 * @Date 2020/11/10 10:38
 * @Description 常量管理类
 */
public class Constants implements Serializable {

    private static final long serialVersionUID = 113323427779853001L;

    /**
     * 用户admin默认编号
     */
    public static final String USER_ID = "8888aa69a79b474f84d977d0460aa900";

    /**
     * 接口访问秘钥
     */
    public static final String SECRET_KEY = "001e974b64e04e70bf5751a3311ac00c";

    /**
     * 删除标记 0-正常，1-删除
     */
    public static final String DEL_FLAG_TRUE = "1";
    public static final String DEL_FLAG_FALSE = "0";

    /**
     * 接口返回格式 1-列表，2-饼状图，3-柱状图，4-折线图
     */
    public static final int RETURN_TYPE_1 = 1;
    public static final int RETURN_TYPE_2 = 2;
    public static final int RETURN_TYPE_3 = 3;
    public static final int RETURN_TYPE_4 = 4;

    /**
     * 接口是否禁用 0-正常，1-禁用
     */
    public static final String FORBIDDEN_FLAG_0 = "0";
    public static final String FORBIDDEN_FLAG_1 = "1";

    /************************************************分页常量************************************************/
    /**
     * 分页数据 , 默认当前页1，数据10
     */
    public static final int PAGENUM = 1;
    public static final int PAGESIZE = 10;

    /**********************************************Dynamic数据源**********************************************/
    /**
     * 数据源KEY
     */
    public static final String KEY = "DB_KEY";
}
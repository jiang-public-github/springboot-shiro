package com.shiro.common.api;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @Author jianglanglang
 * @Date 2020/11/6 16:36
 * @Description swagger接口文档
 */
@Configuration
@EnableSwagger2WebMvc
public class SwaggerApi {
    /**
     * 插件赋值需要调用OpenApiExtensionResolver提供的buildSettingExtensions方法，获取x-settings的增强属性
     */
    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Autowired
    public SwaggerApi(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }


    @Bean("defaultApi")
    public Docket defaultApi() {
        Docket docket = new  Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                    .title("贵州志诚慧远信息科技有限公司--在线服务接口 RESTful API")
                    .description("贵州志诚慧远信息科技有限公司《数据开发平台》API接口文档")
                    .termsOfServiceUrl("http://www.zchyit.com.cn/")
                    .contact(new Contact("Jianglanglang", null, "1029236288@qq.com"))
                    .version("v1.0.0")
                    .build())

                // 关闭swagger默认的状态码
                .useDefaultResponseMessages(true)
                .select()
                // 扫描@Api标注的controller类
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildSettingExtensions());
        return docket;
    }


}
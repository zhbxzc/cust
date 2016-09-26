package com.bincai.cloud.cust.domain.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 可以定义多个组，比如本类中定义把test和demo区分开了
     * （访问页面就可以看到效果了） 
     *
     */
    @Bean
    public Docket demoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("cust")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select().apis(RequestHandlerSelectors.basePackage("com.bincai"))
//                .paths("/demo/.*"))//过滤的接口
                .build()
                .apiInfo(demoApiInfo());
    }
    private ApiInfo demoApiInfo() {
        ApiInfo apiInfo = new ApiInfo("Cust Domain's API",//大标题
                "Cust Domain's REST API, for system administrator",//小标题
                "1.0",//版本
                "NO terms of service",
                "329458839@qq.com",//作者
                "cust共享",//链接显示文字
                "https://github.com/zhbxzc/cust"//网站链接
        );

        return apiInfo;
    }
    
}

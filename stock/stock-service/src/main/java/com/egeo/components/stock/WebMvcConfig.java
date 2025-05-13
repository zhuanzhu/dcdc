package com.egeo.components.stock;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by guofeng.qin on 2017/5/1 0001.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
//    @Bean
//    TokenInterceptor tokenInterceptor() {
//        return new TokenInterceptor();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**");
//        super.addInterceptors(registry);
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        super.addFormatters(registry);
//        registry.addConverter(new TextToLianLianPayNotifyRequesterConverter());
//        registry.addConverter(new TextToLianLianRepayNotifyRequesterConveter());
//    }
}

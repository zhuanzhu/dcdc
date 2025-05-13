package com.egeo.components.third;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
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

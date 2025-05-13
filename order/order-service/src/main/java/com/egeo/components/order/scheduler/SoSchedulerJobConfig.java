package com.egeo.components.order.scheduler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.type.AnnotatedTypeMetadata;

//@Configuration
//@PropertySource(value = {"file:./scheduler.properties"},ignoreResourceNotFound = false, encoding = "UTF-8", name = "scheduler.properties")
public class SoSchedulerJobConfig {
	@Bean
	@Conditional(SoSchedulerJobCondition.class)
    public SoSchedulerJob myBean() {
		System.out.println("启动定时器1..");
        return new SoSchedulerJob();
    }
	static class SoSchedulerJobCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        	System.out.println("启动定时器");
            return context.getResourceLoader().getResource("classpath:scheduler.properties").exists();
        }
    }
}

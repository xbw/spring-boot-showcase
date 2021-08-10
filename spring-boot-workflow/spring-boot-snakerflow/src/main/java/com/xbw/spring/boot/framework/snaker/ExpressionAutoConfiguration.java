package com.xbw.spring.boot.framework.snaker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snaker.engine.impl.JuelExpression;
import org.snaker.engine.spring.SpelExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xbw
 */
@Configuration
public class ExpressionAutoConfiguration {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "snaker.flow", name = "expression-type", havingValue = "spel", matchIfMissing = true)
    public SpelExpression spelExpression() {
        logger.info("Expression is {}", SpelExpression.class);
        return new SpelExpression();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "snaker.flow", name = "expression-type", havingValue = "juel")
    public JuelExpression juelExpression() {
        logger.info("Expression is {}", JuelExpression.class);
        return new JuelExpression();
    }
}

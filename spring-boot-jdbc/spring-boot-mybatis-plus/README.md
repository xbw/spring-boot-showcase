# spring-boot-mybatis-plus

## MapWrapper

---
### default SqlSessionFactory
`com.baomidou.mybatisplus.autoconfigureMybatisPlusAutoConfiguration#sqlSessionFactory(DataSource) `

#### use `org.springframework.core.convert.converter.Converter`
```yaml
mybatis-plus:
  #  config-location: classpath:mybatis/mybatis-config.xml
  configuration:
    object-wrapper-factory: com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory
```
and
```java
@ConditionalOnProperty(name = "mybatis-plus.configuration.object-wrapper-factory")
@Component
@ConfigurationPropertiesBinding
public class ObjectWrapperFactoryConverter implements Converter<String, ObjectWrapperFactory> {
    @Override
    public ObjectWrapperFactory convert(String source) {
        try {
            return (ObjectWrapperFactory) Class.forName(source).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
```

#### use `com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer`
```yaml
mybatis-plus:
  configuration:
#    object-wrapper-factory: com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory
```
and
```java
@Configuration
public class MybatisPlusConfig {
    // configurationCustomizers is not empty in MybatisPlusAutoConfiguration#applyConfiguration(factory)
    @ConditionalOnMissingBean(ObjectWrapperFactoryConverter.class)
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());
    }
}
```

#### use `org.apache.ibatis.session.Configuration`
```yaml
mybatis:
  configuration:
#    object-wrapper-factory: com.spring.boot.framework.mybatis.MybatisMapWrapperFactory
```
and
```java
@Configuration
public class MybatisPlusConfig {
    @Autowired
    MybatisPlusProperties mybatisPlusProperties;
    
    // mybatisConfiguration() is after MybatisPlusAutoConfiguration#applyConfiguration(factory)
    @ConditionalOnMissingBean(ConfigurationCustomizer.class)
    @Bean
    @ConfigurationProperties(prefix = "mybatis-plus.configuration")
    public MybatisConfiguration mybatisConfiguration() {
        MybatisConfiguration configuration = mybatisPlusProperties.getConfiguration();
        if (configuration == null) {
            configuration = new MybatisConfiguration();
        }
        configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());
        return configuration;
    }
}
```

---
### custom SqlSessionFactory
`com.spring.boot.framework.datasource.DataSourceConfigurer#sqlSessionFactory(DataSource) `

#### use `org.springframework.core.convert.converter.Converter`
Same as the default SqlSessionFactory

#### use `org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer`
```java
@Configuration
public class MybatisPlusConfig {
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());
    }
}

@Configuration
public class DataSourceConfigurer {
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        sessionFactory.setConfiguration(mybatisProperties.getConfiguration());
        /**
         * @see com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration#applyConfiguration(MybatisSqlSessionFactoryBean)
         */
        applyConfiguration(sessionFactory);
    }
}    
```

#### use `org.apache.ibatis.session.Configuration`
Same as the default SqlSessionFactory

or
```java
@Configuration
public class DataSourceConfigurer {
    // mybatisConfiguration() is after DataSourceConfigurer#sqlSessionFactory(DataSource dataSource)
    @ConditionalOnMissingBean(MybatisPlusConfig.class)
    @Bean
    @ConfigurationProperties(prefix = "mybatis-plus.configuration")
    public MybatisConfiguration mybatisConfiguration() {
        MybatisConfiguration configuration = mybatisPlusProperties.getConfiguration();
        if (configuration == null) {
            configuration = new MybatisConfiguration();
        }
        configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());
        return configuration;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        sessionFactory.setConfiguration(mybatisProperties.getConfiguration());
    }
}
```

---
### All SqlSessionFactory
use `com.spring.boot.framework.mybatis.CamelKeyMap`
```java
public interface UserMapper {
    @Select("SELECT * FROM users")
    List<CamelKeyMap> findToMap();
}
```
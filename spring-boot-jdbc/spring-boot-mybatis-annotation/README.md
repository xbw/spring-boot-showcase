# spring-boot-mybatis-annotation

## MapWrapper

### default SqlSessionFactory
`org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration#sqlSessionFactory(DataSource) `

#### use `org.springframework.core.convert.converter.Converter`
```yaml
mybatis:
  configuration:
    object-wrapper-factory: MybatisMapWrapperFactory
```
and
```java
@ConditionalOnProperty(name = "mybatis.configuration.object-wrapper-factory")
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

#### use `org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer`
```yaml
mybatis:
  configuration:
#    object-wrapper-factory: MybatisMapWrapperFactory
```
```java
@Configuration
public class MybatisConfig {
    // configurationCustomizers is not empty in MybatisAutoConfiguration#applyConfiguration(factory)
    @ConditionalOnMissingBean(name = "objectWrapperFactoryConverter")
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
#    object-wrapper-factory: MybatisMapWrapperFactory
```
and
```java
@Configuration
public class MybatisConfig {
    @Autowired
    MybatisProperties mybatisProperties;
    
    // mybatisConfiguration() is after MybatisAutoConfiguration#applyConfiguration(factory)
    @ConditionalOnMissingBean(ConfigurationCustomizer.class)
    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration mybatisConfiguration() {
        org.apache.ibatis.session.Configuration configuration = mybatisProperties.getConfiguration();
        if (configuration == null) {
            configuration = new org.apache.ibatis.session.Configuration();
        }
        configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());
        return configuration;
    }
}
```

---
### custom SqlSessionFactory
`DataSourceConfigurer#sqlSessionFactory(DataSource) `

#### use `org.springframework.core.convert.converter.Converter`
Same as the default SqlSessionFactory

#### use `org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer`
```java
@Configuration
public class MybatisConfig {
    @ConditionalOnMissingBean(name = "objectWrapperFactoryConverter")
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
         * @see org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration#applyConfiguration(SqlSessionFactoryBean)
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
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        org.apache.ibatis.session.Configuration configuration = mybatisProperties.getConfiguration();
        if (configuration == null) {
            configuration = new org.apache.ibatis.session.Configuration();
        }
        configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());
        sessionFactory.setConfiguration(configuration);
        sessionFactory.setConfiguration(mybatisProperties.getConfiguration());
    }
}
```

---
### All SqlSessionFactory
use `CamelKeyMap`
```java
public interface UserMapper {
    @Select("SELECT * FROM users")
    List<CamelKeyMap> findToMap();
}
```
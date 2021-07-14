package com.spring.boot.framework.cache;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @see org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration to be made package-private in 2.5 as this class is not intended or public use.
 * @see org.springframework.data.couchbase.cache.CouchbaseCacheManager
 * @see org.springframework.data.couchbase.cache.CouchbaseCacheConfiguration
 */
@ConditionalOnProperty(name = "spring.cache.type", havingValue = "couchbase")
@Configuration
public class CouchbaseCacheConfig {


}

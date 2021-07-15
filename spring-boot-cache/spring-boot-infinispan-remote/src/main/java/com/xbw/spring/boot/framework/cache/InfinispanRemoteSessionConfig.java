package com.xbw.spring.boot.framework.cache;

import org.infinispan.commons.marshall.ProtoStreamMarshaller;
import org.infinispan.spring.remote.session.configuration.EnableInfinispanRemoteHttpSession;
import org.infinispan.spring.starter.remote.InfinispanRemoteCacheCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * https://infinispan.org/docs/dev/titles/server/server.html#create_remote_cache
 */
@ConditionalOnProperty(name = "infinispan.remote.enabled", havingValue = "true")
@EnableInfinispanRemoteHttpSession
@Configuration
public class InfinispanRemoteSessionConfig {

    @Bean
    public InfinispanRemoteCacheCustomizer caches() {
        return b -> {
            // Ask the server to create this cache on startup
            b.remoteCache("sessions")
                    .configuration("<distributed-cache name=\"sessions\"><encoding media-type=\"application/x-protostream\"/></distributed-cache>");

            // Use protostream marshaller to serialize the sessions with Protobuf
            b.remoteCache("sessions").marshaller(ProtoStreamMarshaller.class);
        };
    }
}

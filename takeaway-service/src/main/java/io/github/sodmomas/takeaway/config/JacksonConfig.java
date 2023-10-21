package io.github.sodmomas.takeaway.config;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sod-Momas
 * @since 2023/10/21
 */
@Configuration(proxyBeanMethods = false)
public class JacksonConfig {
    @Bean
    Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return cus -> {
            cus.serializerByType(Long.class, ToStringSerializer.instance);
            cus.serializerByType(long.class, ToStringSerializer.instance);
        };
    }
}

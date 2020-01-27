package es.aboris.crud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({
        "classpath:application.properties",
        "classpath:database.properties",
        "classpath:token.properties"
})
public class PropertiesConfiguration {
}

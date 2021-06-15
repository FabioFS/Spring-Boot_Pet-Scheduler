/**
 * 
 */
package com.sippulse.pet.util;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author Fábio Figueiredo da Silva
 *
 */
public class PropertiesUtils {
	public static final String SPRING_PROFILES_ACTIVE = "spring.profiles.active";

    public static void initProperties() {
        String activeProfile = System.getProperty(SPRING_PROFILES_ACTIVE);
        if (activeProfile == null) {
            activeProfile = "dev";
        }
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer
                = new PropertySourcesPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[]
                {new ClassPathResource("application.properties"),
                        new ClassPathResource("application-" + activeProfile + ".properties")};
        propertySourcesPlaceholderConfigurer.setLocations(resources);

    }
}
package ua.edu.j2ee.shoestore.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ua.edu.j2ee.shoestore.services.StringToSetIntConverter;
import ua.edu.j2ee.shoestore.services.StringToSetStrConverter;

@ComponentScan(basePackages = {"ua.edu.j2ee.shoestore.*"})
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToSetStrConverter());
        registry.addConverter(new StringToSetIntConverter());
    }
}
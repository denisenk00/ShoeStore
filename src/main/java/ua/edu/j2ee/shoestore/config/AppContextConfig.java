package ua.edu.j2ee.shoestore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"ua.edu.j2ee.shoestore.*"})
@EnableWebMvc
public class AppContextConfig {

    @Autowired
    private Environment env;

    @Value("${spring.mvc.view.prefix}")
    private String prefixValue;

    @Value("${spring.mvc.view.suffix}")
    private String suffixValue;

    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(prefixValue);
        viewResolver.setSuffix(suffixValue);
        return viewResolver;
    }
}

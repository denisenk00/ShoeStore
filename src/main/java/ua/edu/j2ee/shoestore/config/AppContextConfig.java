package ua.edu.j2ee.shoestore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ua.edu.j2ee.shoestore.dao.*;

@Configuration
@ComponentScan(basePackages = {"ua.edu.j2ee.shoestore.config",
        "ua.edu.j2ee.shoestore.controllers",
        "ua.edu.j2ee.shoestore.dao",
        "ua.edu.j2ee.shoestore.services",
        "ua.edu.j2ee.shoestore.*"})
@EnableTransactionManagement
public class AppContextConfig {

    @Autowired
    private Environment env;

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
        rb.setBasenames("messages/validator");
        return rb;
    }

    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();

        return commonsMultipartResolver;
    }

    @Bean(name = "userDao")
    public UserDao getUserDao() {
        return new UserDaoImpl();
    }

    @Bean(name = "shoeModelDao")
    public ShoeModelDao getShoeModelDao(){
        return new ShoeModelDaoImpl();
    }

    @Bean(name = "shoeDao")
    public ShoeDao getShoeDao() {
        return new ShoeDaoImpl();
    }

    @Bean(name = "orderDAO")
    public OrderDao getOrderDao() {
        return new OrderDaoImpl();
    }

    @Bean(name = "supplierDao")
    public SupplierDaoImpl getSupplierDao()  {
        return new SupplierDaoImpl();
    }

}

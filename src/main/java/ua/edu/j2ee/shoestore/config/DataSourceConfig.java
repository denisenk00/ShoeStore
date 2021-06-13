package ua.edu.j2ee.shoestore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Hashtable;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"ua.edu.j2ee.shoestore.config",
        "ua.edu.j2ee.shoestore.controllers",
        "ua.edu.j2ee.shoestore.dao",
        "ua.edu.j2ee.shoestore.services",
        "ua.edu.j2ee.shoestore.*"})
public class DataSourceConfig {

    @Value("${datasource.name}")
    private String dataSourceName;

    @Bean(name = "dataSource", destroyMethod = "")
    public DataSource getDataSource() throws NamingException {
        Hashtable hashtable = new Hashtable();
        hashtable.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        hashtable.put(Context.PROVIDER_URL, "t3://localhost:7001");
        Context context = new InitialContext( hashtable );
        return (DataSource) context.lookup(dataSourceName);
    }

}

package ua.edu.j2ee.shoestore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Hashtable;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.jndi-name}")
    private String dataSourceName;

    @Bean(destroyMethod = "")
    public DataSource OracleDataSource() throws NamingException {
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        hashtable.put(Context.PROVIDER_URL, "t3://localhost:7001");

        Context context = new InitialContext(hashtable);
        DataSource dataSource = (DataSource) context.lookup(dataSourceName);
        context.close();
        return dataSource;
    }
}

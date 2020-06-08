package ru.pangaia.example.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;


@Configuration
@EnableAutoConfiguration//(exclude = {HibernateJpaAutoConfiguration.class})
@EnableJpaRepositories
@ComponentScan
public class BiblioApplication extends SpringApplication
{
//    public static final String DB_ADDR = "/home/oneuro/.bookshelfData/test.odb";
    public static void main(String[] args)
    {
        run(BiblioApplication.class, args);
    }

//    @Bean(name = "entityManagerFactory")
//    EntityManagerFactory emf()
//    {
//        return Persistence.createEntityManagerFactory(DB_ADDR);
//    }
//
//    @Bean(name = "transactionManager")
//    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
    @Bean
    public DataSource getDataSource() {

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        String U = System.getenv("DATABASE_URL");
        String[] c = U.split("//");
        String[] up = c[1].split("@");
        String conn = up[1];
        String u = up[0].split(":")[0];
        String p = up[0].split(":")[1];

        String url = "jdbc:postgresql://" + conn;
        String username = u;
        String password = p;
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.username(u);
        dataSourceBuilder.password(p);
        dataSourceBuilder.url(url);

        return dataSourceBuilder.build();

    }
}

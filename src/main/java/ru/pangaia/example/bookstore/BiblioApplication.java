package ru.pangaia.example.bookstore;

import com.objectdb.jpa.EMImpl;
import com.objectdb.jpa.Provider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.pangaia.example.bookstore.controller.MainController;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceProvider;


@Configuration
@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class})
@EnableJpaRepositories
@ComponentScan
public class BiblioApplication extends SpringApplication
{
    public static final String DB_ADDR = "/home/oneuro/.bookshelfData/test.odb";
    public static void main(String[] args)
    {
        run(BiblioApplication.class, args);
    }

    @Bean
    EntityManagerFactory emf()
    {
        return Persistence.createEntityManagerFactory(DB_ADDR);
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

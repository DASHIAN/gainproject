package config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages = {"com.ljh.study"})
public class RootConfig {
	 @Bean
	    public DataSource dataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
	        dataSource.setUrl("jdbc:mariadb://localhost:3306/spring");
	        dataSource.setUsername("root");
	        dataSource.setPassword("12345");
	        return dataSource;
	    }

	    @Bean
	    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception {
	        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
	        sessionFactory.setDataSource(dataSource);
	        sessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
	        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*Mapper.xml"));
	        return sessionFactory;
	    }

	    @Bean
	    public SqlSessionTemplate sqlSession(SqlSessionFactoryBean sqlSessionFactory) throws Exception {
	        return new SqlSessionTemplate(sqlSessionFactory.getObject());
	    }
}

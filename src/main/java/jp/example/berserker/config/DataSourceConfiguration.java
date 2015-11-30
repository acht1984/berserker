package jp.example.berserker.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {
	@Value("#{systemProperties['JDBC_CONNECTION_STRING'] ?: 'jdbc:mysql://localhost:3306/berserker?useLegacyDatetimeCode=false&serverTimezone=Universal'}")
	String url;

	@Value("#{systemProperties['DB_USERNAME'] ?: 'root'}")
	String username;

	@Value("#{systemProperties['DB_PASSWORD'] ?: ''}")
	String password;

	@Bean
	public DataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		basicDataSource.setUrl(url);
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);
		basicDataSource.setValidationQuery("SELECT 1");
		basicDataSource.setMaxActive(50);
		basicDataSource.setMaxIdle(10);
		basicDataSource.setMinIdle(5);
		return basicDataSource;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}

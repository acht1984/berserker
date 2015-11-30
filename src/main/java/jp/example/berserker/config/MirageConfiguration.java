package jp.example.berserker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mirage.repository.config.EnableMirageRepositories;
import org.springframework.data.mirage.repository.support.MiragePersistenceExceptionTranslator;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import jp.sf.amateras.mirage.SqlManager;
import jp.sf.amateras.mirage.SqlManagerImpl;
import jp.sf.amateras.mirage.bean.BeanDescFactory;
import jp.sf.amateras.mirage.bean.FieldPropertyExtractor;
import jp.sf.amateras.mirage.dialect.MySQLDialect;
import jp.sf.amateras.mirage.integration.spring.SpringConnectionProvider;
import jp.sf.amateras.mirage.naming.RailsLikeNameConverter;

@Configuration
@EnableMirageRepositories
public class MirageConfiguration {

	@Autowired
	DataSourceTransactionManager transactionManager;

	@Bean
	public SqlManager sqlManager() {
		SpringConnectionProvider springConnectionProvider = new SpringConnectionProvider();
		springConnectionProvider.setTransactionManager(transactionManager);

		BeanDescFactory bdf = new BeanDescFactory();
		bdf.setPropertyExtractor(new FieldPropertyExtractor());

		SqlManagerImpl sqlManagerImpl = new SqlManagerImpl();
		sqlManagerImpl.setConnectionProvider(springConnectionProvider);
		sqlManagerImpl.setDialect(new MySQLDialect());
		sqlManagerImpl.setNameConverter(new RailsLikeNameConverter());
		sqlManagerImpl.setBeanDescFactory(bdf);
		return sqlManagerImpl;
	}

	@Bean
	public MiragePersistenceExceptionTranslator miragePersistenceExceptionTranslator() {
		return new MiragePersistenceExceptionTranslator();
	}
}

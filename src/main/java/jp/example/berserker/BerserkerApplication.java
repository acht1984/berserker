package jp.example.berserker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mirage.repository.config.EnableMirageRepositories;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import jp.example.berserker.model.User;
import jp.example.berserker.model.UserRepository;

@Configuration
@ComponentScan
@EnableMirageRepositories
public class BerserkerApplication {

	public static void main(String[] args) {
		try (ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(
				BerserkerApplication.class)) {
			BerserkerApplication main = context.getBean(BerserkerApplication.class);
			main.execute();
		}
	}

	@Autowired
	UserRepository userRepos;

	@Autowired
	PlatformTransactionManager txManager;

	@Transactional
	public void execute() {
		// create
		userRepos.save(new User("watanabe", "$2a$10$MHPqWJ61alnBlUbvjEGK/uWRvwtYzolWCuFXW8YMJkT54HUB0H9iq"));

		// read
		Iterable<User> all = userRepos.findAll();
		for (User user : all) {
			System.out.println(user);
		}

		User miyamoto = userRepos.findOne("miyamoto");
		System.out.println(miyamoto);

		// update
		miyamoto.setPassword("$2a$10$vxq4n5VB4bsgUlBK9DXV9edhX911Qz/5iYqjLi/6qZPp8Xl7ZACKC");
		userRepos.save(miyamoto);

		// delete
		userRepos.delete("watanabe");
	}
}

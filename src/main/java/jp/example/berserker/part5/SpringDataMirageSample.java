package jp.example.berserker.part5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import jp.example.berserker.model.User;
import jp.example.berserker.model.UserRepository;
import jp.sf.amateras.mirage.integration.guice.Transactional;

@Component
public class SpringDataMirageSample {

	public static void main(String[] args) {
		try (ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			SpringDataMirageSample sdms = context.getBean(SpringDataMirageSample.class);
			sdms.execute();
		}
	}

	@Autowired
	UserRepository userRepos;

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

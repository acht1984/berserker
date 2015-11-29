package jp.example.berserker.part5;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jp.example.berserker.model.User;
import jp.example.berserker.model.UserRepository;

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

		// find by length
		Page<User> p = userRepos.findByUsername(6, new PageRequest(0, 2));
		List<User> users = p.getContent();
		assert users.size() <= 2;
	}
}

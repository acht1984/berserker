package jp.example.berserker;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataAccessSample {

	public static void main(String[] args) {
		try (ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			DataAccessSample das = context.getBean(DataAccessSample.class);
			das.execute();
		}
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Transactional
	public void execute() {
		// user count
		Long allUsersCount = jdbcTemplate.queryForObject("select count(*) from users", Long.class);
		System.out.println("allUsersCount is " + allUsersCount);

		// password
		String password = jdbcTemplate.queryForObject(
				"select password from users where username = ?",
				new Object[] { "miyamoto" },
				String.class);
		System.out.println("password = " + password);

		// user
		User user = jdbcTemplate.queryForObject(
				"select * from users where username = ?",
				new Object[] { "miyamoto" },
				new RowMapper<User>() {

					@Override
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						User user = new User();
						user.setUsername(rs.getString("username"));
						user.setPassword(rs.getString("password"));
						return user;
					}

				});
		System.out.println("user = " + user);
	}
}

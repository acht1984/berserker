package jp.example.berserker.model;

import org.springframework.data.annotation.Id;

import jp.sf.amateras.mirage.annotation.Column;
import jp.sf.amateras.mirage.annotation.PrimaryKey;
import jp.sf.amateras.mirage.annotation.PrimaryKey.GenerationType;
import jp.sf.amateras.mirage.annotation.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "users")
public class User {

	@Id
	@PrimaryKey(generationType = GenerationType.APPLICATION)
	@Column(name = "username")
	@Getter
	@Setter
	private String username;

	@Column(name = "password")
	@Getter
	@Setter
	private String password;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
}

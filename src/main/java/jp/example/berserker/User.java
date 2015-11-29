package jp.example.berserker;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class User {
	@Getter
	@Setter
	private String username;
	@Getter
	@Setter
	private String password;
}

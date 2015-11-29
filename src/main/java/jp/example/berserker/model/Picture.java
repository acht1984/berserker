package jp.example.berserker.model;

import org.springframework.data.annotation.Id;

import jp.sf.amateras.mirage.annotation.Column;
import jp.sf.amateras.mirage.annotation.PrimaryKey;
import jp.sf.amateras.mirage.annotation.PrimaryKey.GenerationType;
import jp.sf.amateras.mirage.annotation.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode(of = "pictureId")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "pictures")
public class Picture {

	@Id
	@PrimaryKey(generationType = GenerationType.IDENTITY)
	@Column(name = "picture_id")
	@Getter
	private long pictureId;

	@Column(name = "location")
	@Getter
	@Setter
	private String location;

	@Column(name = "event_id")
	@Getter
	@Setter
	private long eventId;

	public Picture(@NonNull String location, long eventId) {
		this.location = location;
		this.eventId = eventId;
	}
}

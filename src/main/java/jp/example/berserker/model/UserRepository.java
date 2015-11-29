package jp.example.berserker.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mirage.repository.MirageRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends MirageRepository<User, String> {

	Page<User> findByUsername(@Param("username_length") int len, Pageable pageable);
}

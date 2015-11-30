package jp.example.berserker.model;

import org.springframework.data.mirage.repository.MirageRepository;

public interface UserRepository extends MirageRepository<User, String> {
}

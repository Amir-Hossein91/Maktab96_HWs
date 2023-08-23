package repository;

import basics.baseRepository.BaseRepository;
import entity.Person;

import java.util.Optional;

public interface PersonRepository extends BaseRepository<Person> {
    Optional<Person> findUser(String username, String password);
}

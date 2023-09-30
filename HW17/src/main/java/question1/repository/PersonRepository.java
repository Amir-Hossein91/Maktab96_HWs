package question1.repository;

import question1.entity.Person;
import question1.base.baseReopsitory.BaseRepository;

import java.util.List;

public interface PersonRepository extends BaseRepository<Person> {

    List<Person> loadAll();
}

package question2.repository;

import question2.base.baseReopsitory.BaseRepository;
import question2.entity.Person;

import java.util.List;

public interface PersonRepository extends BaseRepository<Person> {

    List<Person> loadAll();
}

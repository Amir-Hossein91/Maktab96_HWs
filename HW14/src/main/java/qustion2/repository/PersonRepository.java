package qustion2.repository;

import qustion2.base.baseReopsitory.BaseRepository;
import qustion2.entity.Person;

import java.util.List;

public interface PersonRepository extends BaseRepository<Person> {

    List<Person> loadAll();
}

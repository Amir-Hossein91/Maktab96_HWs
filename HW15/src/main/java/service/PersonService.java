package service;

import basics.BaseService.BaseService;
import entity.Person;

public interface PersonService extends BaseService<Person> {
    Person saveOrUpdate (Person person);
}

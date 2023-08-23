package service;

import basics.BaseService.BaseService;
import entity.Person;
import exceptions.NotFoundException;

public interface PersonService extends BaseService<Person> {
    Person saveOrUpdate (Person person);
    Person findUser(String username, String password) throws NotFoundException;
}

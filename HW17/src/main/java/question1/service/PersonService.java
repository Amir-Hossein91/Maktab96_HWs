package question1.service;

import question1.base.baseService.BaseService;
import question1.entity.Person;

public interface PersonService extends BaseService<Person> {

    Person signUp(String firstname, String lastname);
}

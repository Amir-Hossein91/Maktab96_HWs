package question2.service;

import question2.base.baseService.BaseService;
import question2.entity.Person;

public interface PersonService extends BaseService<Person> {

    Person signUp(String firstname, String lastname);
}

package qustion2.service;

import qustion2.base.baseService.BaseService;
import qustion2.entity.Person;

public interface PersonService extends BaseService<Person> {

    Person signUp(String firstname, String lastname);
}

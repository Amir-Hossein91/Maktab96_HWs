package qustion2;


import qustion2.entity.Person;
import qustion2.repository.PersonRepository;
import qustion2.service.PersonService;
import qustion2.utility.ApplicationContext;

import java.util.Date;

public class Main {
    public static PersonService personService = ApplicationContext.personService;
    public static void main(String[] args) {
        Person person = new Person("amir","ahmadi",new Date());
        personService.save(person);
    }
}

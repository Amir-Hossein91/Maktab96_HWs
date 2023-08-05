package qustion2;


import qustion2.entity.Person;
import qustion2.repository.PersonRepository;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        PersonRepository personRepository = new PersonRepository();
        Person person = new Person("amir","ahmadi",new Date());
        personRepository.save(person);
    }
}

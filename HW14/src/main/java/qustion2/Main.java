package qustion2;


import qustion2.entity.Person;
import qustion2.service.PersonService;
import qustion2.utility.ApplicationContext;

import java.util.Date;

public class Main {
    public static PersonService personService = ApplicationContext.personService;

    public static void main(String[] args) {

//        System.out.println(personService.signUp("hamid", "rahimi"));

//        System.out.println(personService.contains(new Person(4,"hamid","rahimi",null)));


//        personService.delete(new Person(4,"hamid","rahimi",null));

//        System.out.println(personService.loadAll());

        personService.update(new Person(3,"amin", "dehbandi", null));


    }
}

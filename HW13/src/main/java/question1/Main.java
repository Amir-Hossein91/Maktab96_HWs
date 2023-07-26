package question1;


import question1.mockdata.MockData;
import question1.model.Person;

import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//        //B-1:
//        for (Person p : filterAgeLessThanFifty()) {
//            System.out.println(p.getAge());
//        }
//
//        //B-2:
//        for (Person p : sortByUsername()) {
//            System.out.println(p.getUsername());
//        }

        //B-3
        for (Person p : sortByAgeAndLastname()) {
            System.out.println(p.getAge() + "\t" + p.getLastName());
        }



    }

    public static List<Person> filterAgeLessThanFifty(){
        return MockData.getPeople().stream()
                .filter(person -> person.getAge()<50).toList();
    }

    public static List<Person> sortByUsername(){
        return MockData.getPeople().stream()
                .sorted(Comparator.comparing(Person::getUsername)).toList();
    }

    public static List<Person> sortByAgeAndLastname(){
        return MockData.getPeople().stream()
                .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getLastName)).toList();
    }
}
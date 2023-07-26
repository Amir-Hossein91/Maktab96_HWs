package question1;


import question1.mockdata.MockData;
import question1.model.Person;

import java.util.*;

public class Main {
    public static void main(String[] args) {

//        //B-1:
//        for (Person p : filterAgeLessThanFifty()) {
//            System.out.println(p.getAge());
//        }

//        //B-2:
//        for (Person p : sortByUsername()) {
//            System.out.println(p.getUsername());
//        }

//        //B-3
//        for (Person p : sortByAgeAndLastname()) {
//            System.out.println(p.getAge() + "\t" + p.getLastName());
//        }

//        //B-4
//        for (String s: mapToIpv4()){
//            System.out.println(s);
//        }

        //B-5
        System.out.println(fetchHundredPeople().size());
        for(Map.Entry<String, Person> entry :fetchHundredPeople().entrySet()){
            System.out.println(entry);
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

    public static List<String> mapToIpv4(){
        return MockData.getPeople().stream().map(Person::getIpv4).toList();
    }

    public static Map<String, Person> fetchHundredPeople(){
        Map<String, Person> result = new HashMap<>();
       MockData.getPeople().stream()
                .sorted(Comparator.comparing(Person::getLastName))
                .filter(person -> person.getGender().equalsIgnoreCase("female") && person.getAge() > 40)
                .sorted(Comparator.comparing(Person::getFirstName))
                .dropWhile(person -> person.getFirstName().startsWith("A"))
                .skip(5)
                .limit(100)
                .forEach(person -> result.put(person.getFirstName() + " " + person.getLastName(),person));
        return result;
    }

}
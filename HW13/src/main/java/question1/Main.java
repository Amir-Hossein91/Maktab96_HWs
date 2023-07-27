package question1;


import question1.mockdata.MockData;
import question1.model.Person;
import question1.model.PersonSummary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ParseException {

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

//        //B-5
//        System.out.println(fetchHundredPeople().size());
//        for(Map.Entry<String, Person> entry :fetchHundredPeople().entrySet()){
//            System.out.println(entry);
//        }

        //B-6
        List<PersonSummary> personSummaries = alterAgesFetchMales();
        for(PersonSummary p : personSummaries){
                System.out.println(p);
        }
        System.out.println(calculateMaleAverageAges(personSummaries));



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

    public static List<PersonSummary> alterAgesFetchMales() {
        List<PersonSummary> result = MockData.getPeople().stream()
                .map(person ->
                {
                    try {
                        return new PersonSummary(person.getId(),
                                person.getFirstName(),
                                person.getLastName(),
                                person.getGender(),
                                new SimpleDateFormat("dd/MM/yyyy").parse(person.getBirthDate()));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(personSummary -> personSummary.getGender().equalsIgnoreCase("male"))
                .toList();

        result.forEach(personSummary -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(personSummary.getBirthDate());
                    personSummary.setAge(LocalDate.now().getYear() - calendar.get(Calendar.YEAR));
                });

        return result;
    }
    public static int calculateMaleAverageAges(List<PersonSummary> list){
        return list.stream()
                .map(PersonSummary::getAge)
                .reduce(0, (age1, age2) -> age1 + age2)/ list.size();
    }
}
import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

// Поток для поиска совершеннолетних

        Stream<Person> streamOld = persons.stream();
        long countOld = streamOld.filter(x -> x.getAge() >= 18)
                .count();
        System.out.println(countOld);

// Поток для нахождения фамилий призывников

        Stream<Person> streamArmy = persons.stream();
        List<String> armyList = streamArmy.filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .map(Person::getFamily)
                .toList();
        System.out.println(armyList.size());

// Поток для поиска людей в работоспособном возврасте

        Stream<Person> streamEdu = persons.stream();
        List<Person> eduList = streamEdu.filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> (x.getSex() == Sex.MAN && (x.getAge() >= 18 && x.getAge() <= 65)
                        || x.getSex() == Sex.WOMAN && (x.getAge() >= 18 && x.getAge() <= 60)))
                .sorted(Comparator.comparing(Person::getFamily))
                .toList();
        System.out.println(eduList.size());
    }
}
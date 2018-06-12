package code.zillow.classes;

import java.util.Comparator;
import java.util.HashMap;

/*
Factory method to generate Comparators from a Stored HashMap of Comparators
*/

public class MappedComparatorFactory {

    /*
    * HashMap that stores the Comparators
    * */
    private static HashMap<String, Comparator<Person>[]> hashMap = new HashMap<>();

    static {
        hashMap.put("FirstName", new Comparator[]{Comparator.comparing(Person::getFirstName), Comparator.comparing(Person::getFirstName).reversed()});
        hashMap.put("LastName", new Comparator[]{Comparator.comparing(Person::getLastName), Comparator.comparing(Person::getLastName).reversed()});
        hashMap.put("Ssn", new Comparator[]{Comparator.comparing(Person::getSsn), Comparator.comparing(Person::getSsn).reversed()});
        hashMap.put("DateOfBirth", new Comparator[]{Comparator.comparing(Person::getDateOfBirth), Comparator.comparing(Person::getDateOfBirth).reversed()});
        hashMap.put("WeightLb", new Comparator[]{Comparator.comparing(Person::getWeightLb), Comparator.comparing(Person::getWeightLb).reversed()});
        hashMap.put("HeightIn", new Comparator[]{Comparator.comparing(Person::getHeightIn), Comparator.comparing(Person::getHeightIn).reversed()});
    }

    /*
    * Returns the stored comparators and uses an array to store the Comparator and its reverse comparator
    * */
    public static Comparator<Person> getInstance(String field, boolean asc) {

        Comparator<Person>[] comparators = hashMap.get(field);
        if (comparators != null) return comparators[asc ? 0 : 1];

        System.out.println("Using Natural Ordering as Field Could not be found");
        return ((Person p1, Person p2) -> asc ? 1 : -1);

    }

}
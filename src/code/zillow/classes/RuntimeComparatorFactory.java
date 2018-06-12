package code.zillow.classes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.HashMap;

/*
Factory method to generate Comparators at Runtime, using java.reflect
Takes a String field as @param and checks if there is a corresponding
method. Then uses that to generate the Comparator. Caches the comparator
incase it is called again during runtime, for slight computational advantage.
*/

public class RuntimeComparatorFactory {

    /*
    HashMap to cache repeated calls to get a Comparator
    * */
    private static HashMap<String, Comparator<Person>[]> hashMap = new HashMap<>();

    /*
    Function to get Comparator correspoding to a field
    * */
    public static Comparator<Person> getInstance(String field, boolean asc) {

        Comparator<Person>[] comparators = hashMap.get(field);
        if (comparators != null) return comparators[asc ? 0 : 1];
        return createAndUpdate(field, asc);

    }

    /*
    * Helper function to create a new Comparator; uses invocations to a java.reflect.method
    * to get comparable objects that can be used in the Comparator
    * */
    private static Comparator<Person> createAndUpdate(String field, boolean asc) {

        try {

            Method method = Person.class.getMethod("get" + field);

            Comparator<Person> comparator = ((Person p1, Person p2) -> {
                try {
                    return ((Comparable) method.invoke(p1)).compareTo(method.invoke(p2));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (ClassCastException e) {
                    e.printStackTrace();
                }
                return -1;
            });

            Comparator<Person>[] comparators = new Comparator[]{comparator, comparator.reversed()};
            hashMap.put(field, comparators);
            return comparators[asc ? 0 : 1];

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        System.out.println("Using Natural Ordering as Field Could not be found");
        return ((Person p1, Person p2) -> asc ? 1 : -1);

    }

}
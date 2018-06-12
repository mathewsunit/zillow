package code.zillow.services;

import code.zillow.classes.Person;
import code.zillow.classes.RuntimeComparatorFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
* Sorting service that provides the sorting funcitonality, tries to obtain a appropriate Comparator
* */
public class RuntimeSortingService {

    /*
    * Function that sorts the input according to the field and ascending or descending order
    * Works by trying to obtain an approprriate Comparator at runtime, based on the field and order
    * Function never returns null, even if given null input
    * if Sortfield is not found, the unchanged order is returned if asc is true else the reverse of orginal order
    * If asc is null or not true or false, the unchanged order is returned
    * */

    public static List<Person> sort(Iterable<Person> people, String sortField, String ascending) {

        ArrayList<Person> list = new ArrayList<>();
        if (people == null) return list;
        for (Person p : people) list.add(p);
        if (list.size() <= 1 || ascending == null || sortField == null) return list;
        if (ascending.equalsIgnoreCase("true") || ascending.equalsIgnoreCase("false")) {
            Comparator comparator = RuntimeComparatorFactory.getInstance(sortField, ascending.equalsIgnoreCase("true"));
            list.sort(comparator);
        }
        return list;

    }

}
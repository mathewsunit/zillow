package code.zillow.services;

import code.zillow.classes.Person;

import java.util.*;

public class SortingServiceTest {

    static final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final List<String> map = Arrays.asList("FirstName", "LastName", "Ssn", "DateOfBirth", "HeightIn", "WeightLb");
    static final Random random = new Random();
    static final StringBuilder builder = new StringBuilder("SJJKS");

    final List<Person> set = new ArrayList<>();

    static String getRandomString(Random random, int len, StringBuilder sb) {

        for (int i = 0; i < len; i++)
            sb.setCharAt(i, AB.charAt(random.nextInt(AB.length())));
        return sb.toString();

    }

    @org.junit.Before
    public void setUp() {

        for (int i = 0; i < 100; i++) {
            set.add(new Person(String.valueOf(i), new Date(random.nextLong()), getRandomString(random, 5, builder), getRandomString(random, 5, builder), random.nextDouble(), random.nextDouble()));
        }

    }

    @org.junit.Test
    public void runtimeSortResource() {

        double memory = 0;
        long current = new Date().getTime();

        for (int i = 0; i < 1000; i++) {
            RuntimeSortingService.sort(set, map.get(random.nextInt(map.size())), random.nextBoolean() ? "true" : "false");
            memory += (double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024;
        }

        System.out.println(" Average Runtime Usage KB: " + memory / 1000);
        System.out.println(" Average Runtime Time: " + (new Date().getTime() - current));

    }

    @org.junit.Test
    public void mappedSortResource() {

        double memory = 0;
        long current = new Date().getTime();

        for (int i = 0; i < 1000; i++) {
            MappedSortingService.sort(set, map.get(random.nextInt(map.size())), random.nextBoolean() ? "true" : "false");
            memory += (double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024;
        }

        System.out.println(" Average Mapped Usage KB: " + memory / 1000);
        System.out.println(" Average Mapped Time: " + (new Date().getTime() - current));

    }

    @org.junit.Test
    public void runtimeSort() {

        List<Person> list = RuntimeSortingService.sort(set, "FirstName", "true");
        Person prev = list.get(0);
        for (Person p : list) {
            assert (prev.getFirstName().compareTo(p.getFirstName())) <= 0;
            prev = p;
        }

        list = RuntimeSortingService.sort(set, "FirstName", "false");
        prev = list.get(0);
        for (Person p : list) {
            assert (prev.getFirstName().compareTo(p.getFirstName())) >= 0;
            prev = p;
        }

        list = RuntimeSortingService.sort(set, "LastName", "true");
        prev = list.get(0);
        for (Person p : list) {
            assert (prev.getLastName().compareTo(p.getLastName())) <= 0;
            prev = p;
        }

        list = RuntimeSortingService.sort(set, "LastName", "false");
        prev = list.get(0);
        for (Person p : list) {
            assert (prev.getLastName().compareTo(p.getLastName())) >= 0;
            prev = p;
        }

        list = RuntimeSortingService.sort(set, "DateOfBirth", "true");
        prev = list.get(0);
        for (Person p : list) {
            assert (prev.getDateOfBirth().compareTo(p.getDateOfBirth())) <= 0;
            prev = p;
        }

        list = RuntimeSortingService.sort(set, "DateOfBirth", "false");
        prev = list.get(0);
        for (Person p : list) {
            assert (prev.getDateOfBirth().compareTo(p.getDateOfBirth())) >= 0;
            prev = p;
        }

        list = RuntimeSortingService.sort(set, "HeightIn", "true");
        prev = list.get(0);
        for (Person p : list) {
            assert (prev.getHeightIn().compareTo(p.getHeightIn())) <= 0;
            prev = p;
        }

        list = RuntimeSortingService.sort(set, "HeightIn", "false");
        prev = list.get(0);
        for (Person p : list) {
            assert (prev.getHeightIn().compareTo(p.getHeightIn())) >= 0;
            prev = p;
        }

        list = RuntimeSortingService.sort(set, "WeightLb", "true");
        prev = list.get(0);
        for (Person p : list) {
            assert (prev.getWeightLb().compareTo(p.getWeightLb())) <= 0;
            prev = p;
        }

        list = RuntimeSortingService.sort(set, "WeightLb", "false");
        prev = list.get(0);
        for (Person p : list) {
            assert (prev.getWeightLb().compareTo(p.getWeightLb())) >= 0;
            prev = p;
        }

        list = RuntimeSortingService.sort(set, "Ssn", "true");
        prev = list.get(0);
        for (Person p : list) {
            assert (prev.getSsn().compareTo(p.getSsn())) <= 0;
            prev = p;
        }

        list = RuntimeSortingService.sort(set, "Ssn", "false");
        prev = list.get(0);
        for (Person p : list) {
            assert (prev.getSsn().compareTo(p.getSsn())) >= 0;
            prev = p;
        }
    }

    @org.junit.Test
    public void mappedSort() {

        List<Person> list = MappedSortingService.sort(set, "FirstName", "true");
        Person prev = list.get(0);
        for (Person p : list) {
            assert (prev.getFirstName().compareTo(p.getFirstName())) <= 0;
            prev = p;
        }

        list = MappedSortingService.sort(set, "FirstName", "false");
        prev = list.get(0);
        for (Person p : list) {
            assert (prev.getFirstName().compareTo(p.getFirstName())) >= 0;
            prev = p;
        }

        list = MappedSortingService.sort(set, "LastName", "true");
        prev = list.get(0);
        for (Person p : list) {
            assert (prev.getLastName().compareTo(p.getLastName())) <= 0;
            prev = p;
        }

        list = RuntimeSortingService.sort(set, "LastName", "false");
        prev = list.get(0);
        for (Person p : list) {
            assert (prev.getLastName().compareTo(p.getLastName())) >= 0;
            prev = p;
        }

        list = MappedSortingService.sort(set, "DateOfBirth", "true");
        prev = list.get(0);
        for (Person p : list) {
            assert (prev.getDateOfBirth().compareTo(p.getDateOfBirth())) <= 0;
            prev = p;
        }

        list = MappedSortingService.sort(set, "DateOfBirth", "false");
        prev = list.get(0);
        for (Person p : list) {
            assert (prev.getDateOfBirth().compareTo(p.getDateOfBirth())) >= 0;
            prev = p;
        }

        list = MappedSortingService.sort(set, "HeightIn", "true");
        prev = list.get(0);
        for (Person p : list) {
            assert (prev.getHeightIn().compareTo(p.getHeightIn())) <= 0;
            prev = p;
        }

        list = MappedSortingService.sort(set, "HeightIn", "false");
        prev = list.get(0);
        for (Person p : list) {
            assert (prev.getHeightIn().compareTo(p.getHeightIn())) >= 0;
            prev = p;
        }

        list = MappedSortingService.sort(set, "WeightLb", "true");
        prev = list.get(0);
        for (Person p : list) {
            assert (prev.getWeightLb().compareTo(p.getWeightLb())) <= 0;
            prev = p;
        }

        list = MappedSortingService.sort(set, "WeightLb", "false");
        prev = list.get(0);
        for (Person p : list) {
            assert (prev.getWeightLb().compareTo(p.getWeightLb())) >= 0;
            prev = p;
        }

        list = MappedSortingService.sort(set, "Ssn", "true");
        prev = list.get(0);
        for (Person p : list) {
            assert (prev.getSsn().compareTo(p.getSsn())) <= 0;
            prev = p;
        }

        list = MappedSortingService.sort(set, "Ssn", "false");
        prev = list.get(0);
        for (Person p : list) {
            assert (prev.getSsn().compareTo(p.getSsn())) >= 0;
            prev = p;
        }
    }

    @org.junit.Test
    public void nullMappedTest() {
        List<Person> list = MappedSortingService.sort(null, "", "");
        assert list.size() == 0;
        list = MappedSortingService.sort(set, null, "");
        assert list.get(0) == set.get(0) && list.get(set.size() - 1) == set.get(set.size() - 1);
        list = MappedSortingService.sort(set, "", null);
        assert list.get(0) == set.get(0) && list.get(set.size() - 1) == set.get(set.size() - 1);
    }

    @org.junit.Test
    public void nullRuntimeTest() {
        List<Person> list = RuntimeSortingService.sort(null, "", "");
        assert list.size() == 0;
        list = RuntimeSortingService.sort(set, null, "");
        assert list.get(0) == set.get(0) && list.get(set.size() - 1) == set.get(set.size() - 1);
        list = RuntimeSortingService.sort(set, "", null);
        assert list.get(0) == set.get(0) && list.get(set.size() - 1) == set.get(set.size() - 1);
    }

    @org.junit.Test
    public void zeroMappedTest() {
        List<Person> list = MappedSortingService.sort(new ArrayList<Person>(), "", "");
        assert list.size() == 0;
    }

    @org.junit.Test
    public void zeroRuntimeTest() {
        List<Person> list = RuntimeSortingService.sort(new ArrayList<Person>(), "", "");
        assert list.size() == 0;
    }

    @org.junit.Test
    public void sortFieldUnavailableMappedTest() {
        List<Person> list = MappedSortingService.sort(set, "", "true");
        assert list.get(0) == set.get(0) && list.get(set.size() - 1) == set.get(set.size() - 1);
    }

    @org.junit.Test
    public void sortFieldUnavailableRuntimeTest() {
        List<Person> list = RuntimeSortingService.sort(set, "", "true");
        assert list.get(0) == set.get(0) && list.get(set.size() - 1) == set.get(set.size() - 1);
    }

    @org.junit.Test
    public void ascFieldUnavailableMappedTest() {
        List<Person> list = MappedSortingService.sort(set, "FirstName", "");
        assert list.get(0) == set.get(0) && list.get(set.size() - 1) == set.get(set.size() - 1);
    }

    @org.junit.Test
    public void ascFieldUnavailableRuntimeTest() {
        List<Person> list = RuntimeSortingService.sort(set, "FirstName", "");
        assert list.get(0) == set.get(0) && list.get(set.size() - 1) == set.get(set.size() - 1);
    }
}
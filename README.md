ReadMe

There are a few ways of trying to tackle this problem, and there were two that I felt were best suited. So I coded out both of them, since I felt it would be interesting to see how they would end up performing.
But before that I made a few Assumptions.

Assumptions

1. The field name would be in Camel case i.e. FirstName as opposed to firstName as given in the example. I did this beacuse it made implementation easier.
2. The sort feature should never return nulls.
3. For the sake of testing out the sort method I created 2 services for each of my two approaches.
4. I could use Collections.sort or List.sort, basically java implementations instead of my own implementations. Although memory usage would be higher.
5. Exception handling is kept simple, printing the trace and moving on, although MethodNotFound exceptions should ideally be raised for nonexistent fields and moved upwards.
6. Incase of unexpected inputs, the output is kept as the original, not changing the order unless ascending is false.
7. Changes to the Person Class are not allowed. i.e. we can't make it implement an interace for example.
8. The person class has getters.
Approaches

Basically the approaches that we need to consider boils down to how to implement the Comparators, and how to lookup which one to use. It'll also be good if we can store the reverse Comparator as well, so we can think of an Array having 2 elements.
1. Cached fixed mapping of Comparators. We can use a HashMap to store a  String which is the fieldName and the Corresponding Comparators. This is fixed.
2. Runtime creation of Comparators. We can use java.lang.reflect to get the Methods implemented at Runtime using the fieldName. We use a HashMap in this implementation as well to function as a cache, so that repeated calls to the HashMap can reuse Comparators.

Advantages of Approach 1 over Approach 2.
1. Simple straightforward implementation is easy to understand. Hence Lower footprint.
2. Approach 2 uses java.lang.reflect which can be debatable.

Disadvantages of Approach 1 over Approach 2.
1. Approach 1 is difficult ot maintain as new fields to Person can require significant refractoring.

Testing

I developed a testing suite using jUnit to run over some base cases and some negative cases and some ballpark performance benchmarking. As expected the stored Map approach performs better on smaller sets but performance is almost equal over large sets.

Some of the testcases I covered:
1. Empty List
2. Null List
3. Field/Ascedning empty/null
4. General Correctness.
5. Ballpark resource benchmarking.

Some Trade-offs I considered while designing
1. Used a HashMap to reuse Comparators instead of having a if else every time sort is called.
2. Other trade-offs have been mentioned in the previous points.


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();

        people.add(new Student("Ringo", "Starr", 2.66));
        people.add(new Employee("John", "Lennon", "Musician", 27045.78));
        people.add(new Student("Paul", "McCartney", 3.68));
        people.add(new Employee("George", "Harrison", "Guitarist", 50000.00));

        Collections.sort(people);
        printData(people);
    }

    public static void printData(Iterable<Person> people) {
        for (Person person : people) {
            System.out.println(person + " earns " + String.format("%.2f", person.getPaymentAmount()) + " tenge");
        }
    }
}

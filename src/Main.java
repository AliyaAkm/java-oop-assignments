public class Main{
    public static void main(String[] args) {
        //Task1
    Person person=new Person();
    person.name="Akmagambetova Aliya";
    person.age=17;
        System.out.println("From task 1: Hello, my name is "+person.name+", I am "+person.age+" years old");
        //Task 2
        person.printDetails(person.name,person.age);

        //Task 4
        Person2 person2=new Person2("Yerkezhan",3);
        person2.printDetails(person2.GetName(),person2.GetAge());
    }
}
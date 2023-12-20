public class Person2 {
    //Task 3
    private String name;
    private int age;
    public void SetName(String name){
        this.name=name;
    }
    public void SetAge(int age){
        this.age=age;
    }
    public String GetName(){
        return name;
    }
    public int GetAge(){
        return age;
    }
    //task 4
    public Person2(String name,int age){
        SetName(name);
        SetAge(age);
    }
    public void printDetails(String name,int age)
    {
        System.out.println("From task 4: Hello, my name is "+name+", I am "+age+" years old");
    }
}

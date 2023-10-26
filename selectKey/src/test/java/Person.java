

public class Person {
    String name;
    int age;

    public Person() {
    }

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        Person person = new Person();
        person.setName(null);
        person.setAge(18);
        System.out.println(GwJsonUtil.toJson(person));
    }
}

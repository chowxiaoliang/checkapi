package genericity;

public class ClassTest {

    class Person{
        String name;
        String age;
        String sex;

        public Person(String name, String age, String sex){
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        public void printPerson(){
            System.out.println("this is a person:"+name+","+age+","+sex);
        }
    }

    public void testPerson() throws IllegalAccessException, InstantiationException{
        Person person = new Person("yangxiaoxiao", "12", "female");
        person.printPerson();
        Person person1 = person.getClass().newInstance();
        person1.printPerson();

    }
    public static void main(String[] args) throws IllegalAccessException, InstantiationException{
        ClassTest classTest = new ClassTest();
        classTest.testPerson();

    }
}

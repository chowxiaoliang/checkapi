package thread.threadlocal;

public class ThreadLocalOne {

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        Person person1 = new Person("123", "zhouliang", "15872151893");
        Person person2 = new Person("321", "wangwenqi", "13227136694");

        threadLocal.set(person1.getName());
        threadLocal.set(person2.getName());

        System.out.println(threadLocal.get());

    }

    static class Person{

        private String certNo;

        private String name;

        private String phone;

        public Person(String certNo, String name, String phone){
            this.certNo = certNo;
            this.name = name;
            this.phone = phone;
        }

        public String getCertNo() {
            return certNo;
        }

        public void setCertNo(String certNo) {
            this.certNo = certNo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}

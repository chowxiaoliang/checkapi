package check;

import java.util.Objects;

public class People {
    private String name;
    private String phone;

//    public Object getObject(){
//        return new People();
//    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        People people = (People) o;
        return Objects.equals(name, people.name) &&
                Objects.equals(phone, people.phone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, phone);
    }

    public void finalize(){
        System.out.println("垃圾回收机制开始工作了");
    }
}

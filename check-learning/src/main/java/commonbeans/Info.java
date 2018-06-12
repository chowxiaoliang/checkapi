package commonbeans;

public class Info implements Comparable<Info>{

    private String name;

    private String sex;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int compareTo(Info o1) {
        return this.getAge().compareTo(o1.getAge());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Info)) {
            return false;
        }

        Info info = (Info) o;

        if (!getName().equals(info.getName())) {
            return false;
        }
        if (!getSex().equals(info.getSex())) {
            return false;
        }
        return getAge().equals(info.getAge());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getSex().hashCode();
        result = 31 * result + getAge().hashCode();
        return result;
    }
}

package ra.academy.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int age1;
    private  boolean sex;
    private String address;

    public Student() {
    }

    public Student(long id, String name, int age, boolean sex, String address) {
        this.id = id;
        this.name = name;
        this.age1 = age;
        this.sex = sex;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age1;
    }

    public void setAge(int age) {
        this.age1 = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public Student clone(){
        Student s = new Student(this.id,this.name,this.age1,this.sex,this.address);
        return s;
    }
    public void cloneStudent(Student s){
        this.id = s.getId();
        this.name = s.getName();
        this.age1 = s.getAge();
        this.address = s.getAddress();
        this.sex = s.isSex();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age1=" + age1 +
                ", sex=" + sex +
                ", address='" + address + '\'' +
                '}';
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

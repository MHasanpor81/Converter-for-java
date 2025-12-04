package entity;

public class UserEntity extends BaseEntity<Integer> {
    private String name;
    private String email;
    private int age;

    public String getName() {
       return name != null ? name : "Mahdi";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id='" + getId()+" ," + '\'' +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}

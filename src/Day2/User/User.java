package Day2.User;

/**
 * id      VARCHAR2(5) PRIMARY KEY,
 * name    VARCHAR2(10) NOT NULL,
 * age     NUMBER(3)    NOT NULL,
 * sex     VARCHAR2(1)  NOT NULL CHECK (sex IN ('F', 'M')),
 * email   VARCHAR2(20),
 * address VARCHAR2(50) NOT NULL,
 * password VARCHAR2(50) NOT NULL
 * <p>
 * Created by gjp06 on17.3.16.
 */

public class User {
    private String id;
    private String name;
    private Integer age;
    private String sex;
    private String email;
    private String address;
    private String password;

    User() {
    }

    public User(String id, String name, Integer age, String sex, String email, String address, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.email = email;
        this.address = address;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

package Day3.xkManager.Entity;

/**
 * TID
 * NAME
 * SEX
 * ID_CARD
 * EMAIL
 * ADDRESS
 * Created by gjp06 on 17.3.17.
 */
public class Teacher {
    private String tid;
    private String name;
    private String sex;
    private String idCard;
    private String email;
    private String address;

    public Teacher() {
    }

    public Teacher(String tid, String name, String sex, String idCard, String email, String address) {
        this.tid = tid;
        this.name = name;
        this.sex = sex;
        this.idCard = idCard;
        this.email = email;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tid='" + tid + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", idCard='" + idCard + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
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

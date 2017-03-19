package Day3.xkManager.Entity;

/**
 * ID
 * NAME
 * TID
 * INFO
 * Created by gjp06 on 17.3.17.
 */
public class Subject {
    private String id;
    private String name;
    private String tid;
    private String info;

    public Subject() {
    }

    public Subject(String id, String name, String tid, String info) {
        this.id = id;
        this.name = name;
        this.tid = tid;
        this.info = info;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", tid='" + tid + '\'' +
                ", info='" + info + '\'' +
                '}';
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

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

package plus.knowing.distribute.jws;

import java.io.Serializable;

public class DTO implements Serializable {

    private static final long serialVersionUID = -1L;

    private String name;

    private Integer count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

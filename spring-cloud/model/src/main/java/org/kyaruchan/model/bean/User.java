package org.kyaruchan.model.bean;
import java.io.Serializable;

public class User implements Serializable {
    private long id;
    private String name;
    private String dbName;

    public User() {
    }

    public User(String name, String dbName) {
        this.id = id;
        this.name = name;
        this.dbName = dbName;
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

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}

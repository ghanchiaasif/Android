package net.simplifiedlearning.retrofitexample;

/**
 * Created by Belal on 10/2/2017.
 */

public class Hero {

    private int id;
    private String name;
    private String updated_at;
    private String created_at;

    public Hero(int id) {
        this.id = id;
        this.name = name;
        this.updated_at = updated_at;
        this.created_at = created_at;
    }

    public Hero(int id, String name, String updated_at, String created_at) {

        this.id = id;
        this.name = name;
        this.updated_at = updated_at;
        this.created_at = created_at;
    }

    public Hero(Integer id, String name, String updated_at, String created_at, String gender) {
        this.id = this.id;
        this.name = name;
        this.updated_at = updated_at;
        this.created_at = created_at;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }
}



package net.simplifiedlearning.retrofitexample;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 31-07-2018.
 */

public class Message {

    @SerializedName("error")
    private Boolean error;

    @SerializedName("Name")
    private String  name;

    @SerializedName("user")
    private Hero hero;

    public Message(Boolean error, String name,Hero hero) {
        this.error = error;
        this.name = name;
        this.hero = hero;
    }

    public Boolean geterror() {return error;}

    public String  getName() {
        return name;
    }

    public Hero getHero(){return hero;}
}

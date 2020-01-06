package es.upv.myreclycler;

/**
 * Created by gustavo.peiretti on 07/02/2017.
 */

public class UserModel {


    public UserModel() {
    }

    public UserModel(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

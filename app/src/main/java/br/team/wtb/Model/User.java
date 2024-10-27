package br.team.wtb.Model;

import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String cellphone;


    public User(String name, String email, String cellphone, String password) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.cellphone = cellphone;
        this.password = password;
    }

    public User(UUID id, String name, String email, String cellphone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cellphone = cellphone;
        this.password = password;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cellphone='" + cellphone + '\'';
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public String getCellphone() {
        return cellphone;
    }
}

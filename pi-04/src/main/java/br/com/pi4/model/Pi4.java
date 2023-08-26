package br.com.pi4.model;

public class Pi4 {
    private String id_user, name, email, password, cpf, status, group_user;

    public Pi4(){

    }

    public Pi4(String id_user, String name, String email, String password, String cpf, String status, String group_user){
        this.id_user = id_user;
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.status = status;
        this.group_user = group_user;
    }

    public Pi4(String name, String email, String password, String cpf, String status, String group_user) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.status = status;
        this.group_user = group_user;

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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGroup_user() {
        return group_user;
    }

    public void setGroup_user(String group_user) {
        this.group_user = group_user;
    }
}

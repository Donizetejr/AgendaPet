package com.converter.cambio.app_petshop.Model;

public class UsuarioModel extends PetModel{
    private  int usu_id;
    private long usu_token;
    private String usu_nome;
    private String usu_email;
    private String usu_telefone;
    private PetModel pet_nome;


    public String getUsu_nome() {
        return usu_nome;
    }

    public void setUsu_nome(String usu_nome) {
        this.usu_nome = usu_nome;
    }

    public String getUsu_email() {
        return usu_email;
    }

    public void setUsu_email(String usu_email) {
        this.usu_email = usu_email;
    }

    public String getUsu_telefone() {
        return usu_telefone;
    }

    public void setUsu_telefone(String usu_telefone) {
        this.usu_telefone = usu_telefone;
    }
}

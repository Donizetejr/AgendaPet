package com.converter.cambio.app_petshop.Model;

public class LoginModel extends UsuarioModel{
    private UsuarioModel usu_email;
    private UsuarioModel usu_senha;
    private String token;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

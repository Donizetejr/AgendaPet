package com.converter.cambio.app_petshop.Model;

public class ClienteModel extends PetModel{
    private  int cli_id;
    private long cli_token;
    private String cli_auth_key;
    private String cli_cpf;
    private String cli_nome;
    private String cli_telefone;
    private String cli_email;
    private String senha;
    private String cli_endereco;
    private PetModel pet_nome;

    public int getCli_id() {
        return cli_id;
    }

    public void setCli_id(int cli_id) {
        this.cli_id = cli_id;
    }

    public long getCli_token() {
        return cli_token;
    }

    public void setCli_token(long cli_token) {
        this.cli_token = cli_token;
    }

    public String getCli_auth_key() {
        return cli_auth_key;
    }

    public void setCli_auth_key(String cli_auth_key) {
        this.cli_auth_key = cli_auth_key;
    }

    public String getCli_cpf() {
        return cli_cpf;
    }

    public void setCli_cpf(String cli_cpf) {
        this.cli_cpf = cli_cpf;
    }

    public String getCli_nome() {
        return cli_nome;
    }

    public void setCli_nome(String cli_nome) {
        this.cli_nome = cli_nome;
    }

    public String getCli_telefone() {
        return cli_telefone;
    }

    public void setCli_telefone(String cli_telefone) {
        this.cli_telefone = cli_telefone;
    }

    public String getCli_email() {
        return cli_email;
    }

    public void setCli_email(String cli_email) {
        this.cli_email = cli_email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCli_endereco() {
        return cli_endereco;
    }

    public void setCli_endereco(String cli_endereco) {
        this.cli_endereco = cli_endereco;
    }

    public PetModel getPet_nome() {
        return pet_nome;
    }

    public void setPet_nome(PetModel pet_nome) {
        this.pet_nome = pet_nome;
    }
}

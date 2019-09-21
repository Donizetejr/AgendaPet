package com.converter.cambio.app_petshop.Model;

public class ClienteModel extends PetModel{
    private  String cli_id;
    private String cli_cpf;
    private String cli_nome;
    private String cli_telefone;
    private String cli_email;
    private String cli_senha;
    private String cli_data_ultima_alteracao_senha;
    private String cli_endereco;
//    private String _cli_pet_id;

    //    private long cli_token;
    //    private String cli_auth_key;

//    public long getCli_token() {
//        return cli_token;
//    }
//
//    public void setCli_token(long cli_token) {
//        this.cli_token = cli_token;
//    }

//    public String getCli_auth_key() {
//        return cli_auth_key;
//    }
//
//    public void setCli_auth_key(String cli_auth_key) {
//        this.cli_auth_key = cli_auth_key;
//    }

    public String getCli_id() {
        return cli_id;
    }

    public void setCli_id(String cli_id) {
        this.cli_id = cli_id;
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

    public String getCli_senha() {
        return cli_senha;
    }

    public void setCli_senha(String cli_senha) {
        this.cli_senha = cli_senha;
    }

    public String getCli_endereco() {
        return cli_endereco;
    }

    public void setCli_endereco(String cli_endereco) {
        this.cli_endereco = cli_endereco;
    }

//    public String get_cli_pet_id() {
//        return _cli_pet_id;
//    }
//
//    public void set_cli_pet_id(String _cli_pet_id) {
//        this._cli_pet_id = _cli_pet_id;
//    }

    public String getCli_data_ultima_alteracao_senha() {
        return cli_data_ultima_alteracao_senha;
    }

    public void setCli_data_ultima_alteracao_senha(String cli_data_ultima_alteracao_senha) {
        this.cli_data_ultima_alteracao_senha = cli_data_ultima_alteracao_senha;
    }
}

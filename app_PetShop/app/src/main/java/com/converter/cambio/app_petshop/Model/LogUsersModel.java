package com.converter.cambio.app_petshop.Model;

public class LogUsersModel {
    private int log_id;
    private String log_acao_banco; // Ins, Updt ou Del
    private String log_data_acao;
    private int log_id_usuario;
    private int log_tipo_usuario; // Empresa ou Cliente
    private String log_descricao;


    public int getLog_id() {
        return log_id;
    }

    public void setLog_id(int log_id) {
        this.log_id = log_id;
    }

    public String getLog_acao_banco() {
        return log_acao_banco;
    }

    public void setLog_acao_banco(String log_acao_banco) {
        this.log_acao_banco = log_acao_banco;
    }

    public String getLog_data_acao() {
        return log_data_acao;
    }

    public void setLog_data_acao(String log_data_acao) {
        this.log_data_acao = log_data_acao;
    }

    public int getLog_id_usuario() {
        return log_id_usuario;
    }

    public void setLog_id_usuario(int log_id_usuario) {
        this.log_id_usuario = log_id_usuario;
    }

    public int getLog_tipo_usuario() {
        return log_tipo_usuario;
    }

    public void setLog_tipo_usuario(int log_tipo_usuario) {
        this.log_tipo_usuario = log_tipo_usuario;
    }

    public String getLog_descricao() {
        return log_descricao;
    }

    public void setLog_descricao(String log_descricao) {
        this.log_descricao = log_descricao;
    }
}

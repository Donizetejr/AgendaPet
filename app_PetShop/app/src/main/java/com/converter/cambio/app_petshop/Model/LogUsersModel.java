package com.converter.cambio.app_petshop.Model;

public class LogUsersModel {
    private int log_id;
    private String log_acao_banco; // Ins, Updt ou Del
    private String log_data_acao;
    private int log_id_usuario;
    private int log_tipo_usuario; // Empresa ou Cliente
    private String log_descricao;
}

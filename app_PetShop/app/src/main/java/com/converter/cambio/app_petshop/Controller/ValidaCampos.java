package com.converter.cambio.app_petshop.Controller;

public class ValidaCampos {


    public String vString(String strCampo){
        if(strCampo == null || strCampo.equals("")){
            return "O campo nome é obrigatório";
        }
        else if(strCampo.trim().length() < 3){
            return "No mínimo 3 caracteres";
        }

        return "ok";
    }

    public String vStringEmail(String strEmail) {
        if(strEmail.trim().contains("@") && strEmail.trim().contains(".") && strEmail.trim().length() > 4){
            return "ok";
        }
        else if(strEmail.trim().equals("")){
            return "O campo e-mail é obrigatório!";
        }
        return "Digite um e-mail válido.";
    }

    public String vStringSenha(String strSenha) {

        if(strSenha.trim().equals("")){
            return "O campo senha é obrigatório!";
        }
        else if(strSenha.trim().length() < 8){
            return "A senha deve conter no mínimo 8 caracteres!";
        }
        return "ok";
    }

    public String vStringEndereco(String strEndereco) {
        if(strEndereco.trim().equals("")){
            return "O campo endereço deve ser preenchido!";
        }
        else if(strEndereco.trim().length() < 8){
            return "formato de endereço: Rua, Endereço, Cidade - Estado";
        }
        if(strEndereco.trim().length() >= 8) {
            return "ok";
        }
        return "Digite o endereço corretamente : Rua, Numero, Bairro, Cidade, Estado";
    }

    public String vStringTelefone(String strTelefone) {
        if(strTelefone.trim().equals("")){
            return "O campo Telefone deve ser preenchido!";
        }
        else if(strTelefone.trim().length() < 8){
            return "Digite um telefone válido";
        }
        if(strTelefone.trim().length() >= 8) {
            return "ok";
        }
        return "formato de telefone inválido";
    }

    public String vStringCpf(String strCpf) {

        if(strCpf.trim().equals("")){
            return "O cmapo Cpf é obrigatório";
        }
        else if(strCpf.trim().length() < 11){
            return "Digite um CPF válido!";
        }
        else if(strCpf.trim().length() > 12){
            return  "Digite somente os números!";
        }
        return "ok";
    }

    public String vStringSpn(String strTexto) {
        if(strTexto.trim().toUpperCase().contains("SELECIONE")){
            return "Selecione um item.";
        }
        return "ok";
    }

    public String vInt(String strNum) {
        if(strNum.trim().equals("")){
            return "Digite a idade do Pet.";
        }
        return "ok";

    }
}

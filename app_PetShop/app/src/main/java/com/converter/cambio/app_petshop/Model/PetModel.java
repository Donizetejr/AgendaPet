package com.converter.cambio.app_petshop.Model;

public class PetModel {
    private int pet_id;
    private String pet_nome;
    private String pet_sexo;
    private String pet_raca;
    private String pet_idade;
    private String porte;
    private int pet_cli_id;


    public int getPet_id() {
        return pet_id;
    }

    public void setPet_id(int pet_id) {
        this.pet_id = pet_id;
    }

    public String getPet_nome() {
        return pet_nome;
    }

    public void setPet_nome(String pet_nome) {
        this.pet_nome = pet_nome;
    }

    public String getPet_sexo() {
        return pet_sexo;
    }

    public void setPet_sexo(String pet_sexo) {
        this.pet_sexo = pet_sexo;
    }

    public String getPet_raca() {
        return pet_raca;
    }

    public void setPet_raca(String pet_raca) {
        this.pet_raca = pet_raca;
    }

    public String getPet_idade() {
        return pet_idade;
    }

    public void setPet_idade(String pet_idade) {
        this.pet_idade = pet_idade;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public int getPet_cli_id() {
        return pet_cli_id;
    }

    public void setPet_cli_id(int pet_cli_id) {
        this.pet_cli_id = pet_cli_id;
    }
}

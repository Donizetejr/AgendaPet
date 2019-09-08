package com.converter.cambio.app_petshop.Model;

public class PetModel {
    private int pet_id;
    private String pet_nome;
    private String pet_raca;
    private String pet_porte;


    public String getPet_nome() {
        return pet_nome;
    }

    public void setPet_nome(String pet_nome) {
        this.pet_nome = pet_nome;
    }

    public String getPet_raca() {
        return pet_raca;
    }

    public void setPet_raca(String pet_raca) {
        this.pet_raca = pet_raca;
    }

    public String getPet_porte() {
        return pet_porte;
    }

    public void setPet_porte(String pet_porte) {
        this.pet_porte = pet_porte;
    }
}

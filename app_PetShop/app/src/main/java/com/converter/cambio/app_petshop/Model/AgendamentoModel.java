package com.converter.cambio.app_petshop.Model;

import android.provider.ContactsContract;

public class AgendamentoModel {
    private int age_id;
    private String age_data;
    private String age_hora;
    private String age_status;
    private int age_cli_id;
    private int age_pet_id;
    private int age_empresa_id;

    public int getAge_id() {
        return age_id;
    }

    public void setAge_id(int age_id) {
        this.age_id = age_id;
    }

    public String getAge_data() {
        return age_data;
    }

    public void setAge_data(String age_data) {
        this.age_data = age_data;
    }

    public String getAge_hora() {
        return age_hora;
    }

    public void setAge_hora(String age_hora) {
        this.age_hora = age_hora;
    }

    public String getAge_status() {
        return age_status;
    }

    public void setAge_status(String age_status) {
        this.age_status = age_status;
    }

    public int getAge_cli_id() {
        return age_cli_id;
    }

    public void setAge_cli_id(int age_cli_id) {
        this.age_cli_id = age_cli_id;
    }

    public int getAge_pet_id() {
        return age_pet_id;
    }

    public void setAge_pet_id(int age_pet_id) {
        this.age_pet_id = age_pet_id;
    }

    public int getAge_empresa_id() {
        return age_empresa_id;
    }

    public void setAge_empresa_id(int age_empresa_id) {
        this.age_empresa_id = age_empresa_id;
    }
}

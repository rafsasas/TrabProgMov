package com.example.testelogin;

import java.io.Serializable;

public class Time implements Serializable{
    int id;
    String descricao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Time{" +
                "descricao='" + descricao + '\'' +
                '}';
    }
}

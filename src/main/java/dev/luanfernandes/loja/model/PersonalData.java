package dev.luanfernandes.loja.model;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Getter
@Embeddable
public class PersonalData {

    private  String name;
    private  String cpf;

    public PersonalData(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }
}

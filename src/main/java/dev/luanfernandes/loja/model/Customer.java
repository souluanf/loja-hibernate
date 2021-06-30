package dev.luanfernandes.loja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PersonalData personalData;

    public Customer(String name, String cpf) {
        this.personalData = new PersonalData(name,cpf);
    }

    public String getName() {
        return this.personalData.getName();
    }

}

package marcelos.corporation.desafioCrud.dto;


import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import marcelos.corporation.desafioCrud.entities.Client;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class ClientDto  {


    private Long id;
    @Size(min = 3,max = 80,message = "Nome precisa ter entre 3 e 80 caracteres")
    @NotBlank(message = "Campo obrigatório")
    private String name;
    @Digits(integer = 11, fraction = 0, message = "Digite apenas numeros")
    @NotBlank(message = "Campo obrigatório")
    @Size(min = 11, max = 11,message = "Preencha o cpf com 11 digitos")
    private String cpf;
    private Double income;
    @PastOrPresent
    private LocalDate birthDate;
    private Integer children;

    public ClientDto(){

    }

    public ClientDto(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientDto(Client entity) {
        id = entity.getId();
        name = entity.getName();
        cpf = entity.getCpf();
        income = entity.getIncome();
        birthDate = entity.getBirthDate();
        children = entity.getChildren();
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }
}

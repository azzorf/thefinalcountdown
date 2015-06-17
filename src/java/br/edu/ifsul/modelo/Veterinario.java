package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "veterinario")
public class Veterinario extends Pessoa implements Serializable {

    @NotNull(message = "O CRMV deve ser informado")
    @Column(name = "crmv", length = 5, nullable = false)
    private Integer crmv;

    @NotEmpty(message = "A Especialidade deve ser informado")
    @Length(max = 25, min = 3, message = "A Especialidade deve ter no minimo {min} e no maximo {max}  caracteres")
    @Column(name = "especialidade", length = 25, nullable = false)
    private String especialidade;

    @NotNull(message = "O Status deve ser informado")
    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @NotNull(message = "O CH deve ser informado")
    @Column(name = "ch", length = 2, nullable = false)
    private Double cargaHoraria;

    @NotEmpty(message = "A senha deve ser informado")
    @Length(max = 10, min = 6, message = "A senha deve ter {min} e {max} caracteres")
    @Column(name = "password", length = 10, nullable = false)
    private String password;
    

    public Veterinario() {
    }

    public Integer getCrmv() {
        return crmv;
    }

    public void setCrmv(Integer crmv) {
        this.crmv = crmv;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Double getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Double cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}

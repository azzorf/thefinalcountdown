package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "cliente")
public class Cliente extends Pessoa implements Serializable {

    @CPF
    @NotNull(message = "O CPF deve ser informado")
    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Temporal(TemporalType.DATE)
    @Column(name = "cadastro", nullable = false)
    private Calendar cadastro;

    public Cliente() {
        this.cadastro = Calendar.getInstance();

    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Calendar getCadastro() {
        return cadastro;
    }

    public void setCadastro(Calendar cadastro) {
        this.cadastro = cadastro;
    }

    @Transient
    public String getDataCadastroString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(cadastro.getTime());
    }
}

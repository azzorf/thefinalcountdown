/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Azzorf
 */

@Entity
@Table(name = "acesso_usuario")
public class AcessoUsuario implements Serializable{
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "seq_cidade", sequenceName = "seq_cidade_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_cidade", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "dataAcesso", nullable = false)
    private Calendar dataAcesso;
    
    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id", nullable = false)
    private Veterinario usuario;
    
    @NotEmpty(message = "O IP de acesso deve ser informado")
    @Length(max = 15, message = "O IP de acesso não pode ter mais de {max} caracteres")
    @Column(name = "ip_acesso", length = 15, nullable = false)
    private String ipAcesso;

    public AcessoUsuario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AcessoUsuario other = (AcessoUsuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AcessoUsuario{" + "id=" + id + ", dataAcesso=" + dataAcesso + '}';
    }

    public Calendar getDataAcesso() {
        return dataAcesso;
    }

    public void setDataAcesso(Calendar dataAcesso) {
        this.dataAcesso = dataAcesso;
    }

    public Veterinario getUsuario() {
        return usuario;
    }

    public void setUsuario(Veterinario usuario) {
        this.usuario = usuario;
    }
    
    public AcessoUsuario(String ipAcesso){
        this.dataAcesso = Calendar.getInstance();
        this.ipAcesso = ipAcesso;
        
    }
    
}

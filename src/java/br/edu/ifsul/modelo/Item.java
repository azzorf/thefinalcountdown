package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "item")
public class Item implements Serializable {
    
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "seq_item", sequenceName = "seq_item_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item")
    private Integer id;
    
    @NotEmpty(message = "O Nome deve ser informado")
    @Length(max = 25, min = 3, message = "O Nome deve ter no minimo {min} e no maximo {max}  caracteres")
    @Column(name = "nome", length = 25, nullable = false)
    private String nome;
           
    @NotEmpty(message = "A Descricao deve ser informado")
    @Length(max = 500, min = 3, message = "A Descricao deve ter no minimo {min} e no maximo {max}  caracteres")
    @Column(name = "descricao", nullable = false, length = 500, columnDefinition = "text")
    private String descricao;
    
    @NotNull(message = "O Preco deve ser informado")
    @Column(name = "preco", precision = 10, scale = 2, nullable = false)
    private Double preco;
        
    @NotNull(message = "O Status deve ser informado")
    @Column(name = "Status", nullable = false)
    private Boolean status;

    public Item() {
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final Item other = (Item) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    
    

}

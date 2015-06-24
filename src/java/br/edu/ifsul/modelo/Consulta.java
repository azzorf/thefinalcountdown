package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "consulta")
public class Consulta implements Serializable {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "seq_consulta", sequenceName = "seq_consulta_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_consulta")
    private Integer id;

    @NotNull(message = "O Preco deve ser informado")
    @Column(name = "valorTotal", precision = 10, scale = 2, nullable = false)
    private Double valorTotal;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "O Consulta deve ser informado")
    @Column(name = "dataConsulta", nullable = false)
    private Calendar dataConsulta;

    @Temporal(TemporalType.DATE)
    @Column(name = "pagamento", nullable = true)
    private Calendar dataPagamento;

    @ManyToMany
    @JoinTable(name = "itens_consultas", joinColumns
            = @JoinColumn(name = "consulta", referencedColumnName = "id"),
            inverseJoinColumns
            = @JoinColumn(name = "item", referencedColumnName = "id"))
    private List<Item> itens = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "veterinario", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Um Veterinario deve ser informado.")
    private Veterinario veterinario;

    @ManyToOne
    @JoinColumn(name = "animal", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Um animal deve ser informado.")
    private Animal animal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.getId());
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
        final Consulta other = (Consulta) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Consulta() {
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Calendar getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Calendar dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public Calendar getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Calendar dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

}

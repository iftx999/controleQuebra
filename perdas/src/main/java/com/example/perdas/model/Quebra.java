package com.example.perdas.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Quebra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item; // Corrigi a importação do tipo Item

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    private int quantidade;
    private LocalDate data;
    private BigDecimal valorUnitario;

    // Construtores, Getters e Setters

    public Long getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Area getArea() {
        return area;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    // Cálculos realizados dentro do getter
    public BigDecimal getQuantidadeBigDecimal() {
        return new BigDecimal(quantidade); // Converte 'int' para 'BigDecimal'
    }

    public BigDecimal getValorTotal() {
        // Multiplicação feita no getter
        return getQuantidadeBigDecimal().multiply(valorUnitario); // Multiplicação
    }
}

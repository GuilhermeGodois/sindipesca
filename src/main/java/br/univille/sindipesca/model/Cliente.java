package br.univille.sindipesca.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.type.TrueFalseType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String cpf;
    private String nome;
    private String sexo;
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date data;
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dataNascimento;
    private String endereço;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<ItensDocumento> itens = new ArrayList<ItensDocumento>();
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private Usuario usuario;

    public long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCp(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSexo() {
        return sexo;
    }
    
    public List<ItensDocumento> getItens() {
        return itens;
    }

    public void setItens(List<ItensDocumento> itens) {
        this.itens = itens;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
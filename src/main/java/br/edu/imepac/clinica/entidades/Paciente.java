/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.imepac.clinica.entidades;

/**
 *
 * @author ypth
 */
public class Paciente {
    private String cpf;
    private String nome;
    private int n_convenio;
    
    public Paciente() {
    }

    public Paciente(String cpf, String nome, int n_convenio) {
        this.cpf = cpf;
        this.nome = nome;
        this.n_convenio = n_convenio;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getN_convenio() {
        return n_convenio;
    }

    public void setN_convenio(int n_convenio) {
        this.n_convenio = n_convenio;
    }

    @Override
    public String toString() {
        return nome;
    }
}

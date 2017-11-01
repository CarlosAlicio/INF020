package server;

import client.IChatClienteRMI;

public class Conversa {

    public String nome;
    public IChatClienteRMI cliente;

    public Conversa(String nome, IChatClienteRMI cliente){
            this.nome = nome;
            this.cliente = cliente;
    }

    public String getNome(){
            return nome;
    }
    public IChatClienteRMI getCliente(){
            return cliente;
    }	
}

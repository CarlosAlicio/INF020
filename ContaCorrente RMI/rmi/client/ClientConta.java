package teste.rmi.alicio_hugo.client;

import java.rmi.Naming;

import teste.rmi.alicio_hugo.interfaces.Conta;

@SuppressWarnings("deprecation")
public class ClientConta {

    // Endereço de IP do servidor remoto.
    static String endpoint_addr = "127.0.0.1";
    
    public static void main(String[] args) {       

        try {
            // Obtendo referencia do objeto remoto
            Conta conta = (Conta) Naming.lookup("//" + endpoint_addr + "/Conta");

            // Executando métodos remoto
            System.out.println("\n> Enviando execução do método Conta#Depositar");
            double resultadoOperacao = conta.depositar(1000.00);
            System.out.println("< Resposta do método Conta#Depositar: " + resultadoOperacao);
            
            // Executando métodos remoto
            System.out.println("\n> Enviando execução do método Conta#Scar");
            resultadoOperacao = conta.sacar(333.000);
            System.out.println("< Resposta do método Conta#Sacar: " + resultadoOperacao);
            
            // Executando métodos remoto
            System.out.println("\n> Enviando execução do método Conta#Consultar Saldo");
            resultadoOperacao = conta.consultarSaldo();
            System.out.println("< Resposta do método Conta#Consultar Saldo: " + resultadoOperacao);            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

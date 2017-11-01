package teste.rmi.alicio_hugo.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import teste.rmi.alicio_hugo.interfaces.Conta;

public class ServidorBanco {

    public static void main(String[] args) {

        try {
            // Criando serviço na porta 1099
            LocateRegistry.createRegistry(1099);
            // Criando objeto a ser enviado
            Conta contaCorrente = new ContaCorrente(){

                private static final long serialVersionUID = 1L;

                @Override
                public void listener(String method) {
                    System.out.println("Executando Operação#" + method + " no lado do servidor");
                }                
            };

            // Exportando o objeto para enviar
            Conta stub = (Conta) UnicastRemoteObject.exportObject(contaCorrente, 0);

            // Declarando o método na /conta
            Naming.rebind("Conta", contaCorrente);
            
            System.out.println("Servidor do BANCO está pronto para ser utilizado.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

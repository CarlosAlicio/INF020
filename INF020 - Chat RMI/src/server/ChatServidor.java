package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.RemoteRef;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Vector;

import client.IChatClienteRMI;

public class ChatServidor extends UnicastRemoteObject implements IChatServerRMI {

    String linha = "---------------------------------------------\n";
    private Vector<Conversa> conversa;
    private static final long serialVersionUID = 1L;

    public ChatServidor() throws RemoteException {
        super();
        conversa = new Vector<Conversa>(10, 1);
    }

    public static void main(String[] args) {
        iniciarRegistroRMI();
        String hostName = "localhost";
        String serviceName = "GroupChatService";

        if (args.length == 2) {
            hostName = args[0];
            serviceName = args[1];
        }

        try {
            IChatServerRMI servidor = new ChatServidor();
            Naming.rebind("rmi://" + hostName + "/" + serviceName, servidor);
            System.out.println("Servidor do Chat RMI Execultando...");
        } catch (Exception e) {
            System.out.println("Problemas ao iniciar o Servdor");
        }
    }

    public static void iniciarRegistroRMI() {
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            System.out.println("Servidor RMI Ponto!");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
  
    public void atualizarChat(String name, String nextPost) throws RemoteException {
        String message = name + " : " + nextPost + "\n";
        sendToAll(message);
    }
  
    @Override
    public void gerarID(RemoteRef ref) throws RemoteException {
        
        try {
            System.out.println(linha + ref.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void registrar(String[] details) throws RemoteException {
        System.out.println(new Date(System.currentTimeMillis()));
        System.out.println(details[0] + " entrou no chat");
        System.out.println(details[0] + " hostname : " + details[1]);
        System.out.println(details[0] + " serviço RMI : " + details[2]);
        registrarUsuario(details);
    }
    
    private void registrarUsuario(String[] details) {
        try {
            IChatClienteRMI proximoCliente = (IChatClienteRMI) Naming.lookup("rmi://" + details[1] + "/" + details[2]);

            conversa.addElement(new Conversa(details[0], proximoCliente));

            proximoCliente.mensagemServidor("[Servidor] : OI " + details[0] + " você já pode enviar mensagens.\n");

            sendToAll("[Servidor] : " + details[0] + " entrou no chat.\n");

            updateUserList();
        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    private void updateUserList() {
        String[] currentUsers = getUserList();
        for (Conversa c : conversa) {
            try {
                c.getCliente().atualizarListaDeUsuarios(currentUsers);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private String[] getUserList() {
        String[] allUsers = new String[conversa.size()];
        for (int i = 0; i < allUsers.length; i++) {
            allUsers[i] = conversa.elementAt(i).getNome();
        }
        return allUsers;
    }

    
    public void sendToAll(String newMessage) {
        for (Conversa c : conversa) {
            try {
                c.getCliente().mensagemServidor(newMessage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deixarChat(String userName) throws RemoteException {

        for (Conversa c : conversa) {
            if (c.getNome().equals(userName)) {
                System.out.println(linha + userName + " dexou o Chat!");
                System.out.println(new Date(System.currentTimeMillis()));
                conversa.remove(c);
                break;
            }
        }
        if (!conversa.isEmpty()) {
            updateUserList();
        }
    }
   
    @Override
    public void mensagemPrivada(int[] privateGroup, String privateMessage) throws RemoteException {
        Conversa pc;
        for (int i : privateGroup) {
            pc = conversa.elementAt(i);
            pc.getCliente().mensagemServidor(privateMessage);
        }
    }

}

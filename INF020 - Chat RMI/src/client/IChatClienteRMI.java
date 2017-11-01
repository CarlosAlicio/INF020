package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChatClienteRMI extends Remote{

    public void mensagemServidor(String mensagem) throws RemoteException;
    public void atualizarListaDeUsuarios(String[] usuariosAtuais) throws RemoteException;
	
}
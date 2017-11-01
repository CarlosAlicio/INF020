package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RemoteRef;

public interface IChatServerRMI extends Remote {
		
	public void atualizarChat(String nomeUsuario, String mensagem)throws RemoteException;	
	public void gerarID(RemoteRef ref)throws RemoteException;	
	public void registrar(String[] detalis)throws RemoteException;	
	public void deixarChat(String nomeUsuario)throws RemoteException;	
	public void mensagemPrivada(int[] grupoPrivado, String mensagemPrivada)throws RemoteException;
}



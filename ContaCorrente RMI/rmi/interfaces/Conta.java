package teste.rmi.alicio_hugo.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Conta extends Remote {

    double sacar(double valor) throws RemoteException;

    double depositar(double valor) throws RemoteException;

    double consultarSaldo() throws RemoteException;
}

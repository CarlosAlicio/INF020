package teste.rmi.alicio_hugo.server;

import java.io.Serializable;
import java.rmi.RemoteException;

import teste.rmi.alicio_hugo.interfaces.Conta;

public abstract class ContaCorrente implements Conta, Serializable {

    private static final double saldo = 0;

    @Override
    public double sacar(double valor) throws RemoteException {
        listener("Sacar");
        return saldo - valor; 
    }

    @Override
    public double depositar(double valor) throws RemoteException {
        listener("Depositar");
        return saldo + valor; 
    }

    @Override
    public double consultarSaldo() throws RemoteException {
        listener("Consultar Saldo");
        return saldo; 
    }

    public abstract void listener(String method);
}

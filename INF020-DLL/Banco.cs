using System;

namespace INF020_DLL
{
    class Banco
    {
        static void Main(string[] args)
        {
            try
            {
                ContaCorrenteDLL.ContaCorrenteDLL conta = new ContaCorrenteDLL.ContaCorrenteDLL("1234-5", "4321-5", 5000);
                conta.depositar(1250);
                conta.sacar(300);
                conta.depositar(13000);
                conta.sacar(138);

                Console.WriteLine("Visualizar Informações da Conta: " + conta.verSaldo());
                Console.ReadKey();
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
    }
}

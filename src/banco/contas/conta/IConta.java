package banco.contas.conta;

public interface IConta {
    void sacar(double valor);
    void depositar(double valor);
    void transferir(double valor, Conta contaDestino);

    void encerrarConta(Conta conta);

    void imprimirExtrato();

    void imprimirInfosGerais();

}

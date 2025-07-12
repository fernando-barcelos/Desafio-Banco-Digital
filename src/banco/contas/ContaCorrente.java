package banco.contas;

import banco.contas.conta.Conta;
import banco.clientes.Cliente;

public class ContaCorrente extends Conta {
    public static final int TIPOCOMTA = 1;

    public static int getTIPOCOMTA() {
        return TIPOCOMTA;
    }

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("======= Extrato CC =======");
        super.imprimeExtrato();
    }

    @Override
    public void imprimirInfosGerais() {
        System.out.println("======= Dados Gerais =======");
        super.imprimirInfosGerais();

    }

}

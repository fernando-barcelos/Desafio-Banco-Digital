package banco.contas;

import banco.clientes.Cliente;
import banco.contas.conta.Conta;

public class ContaPoupanca extends Conta {
    public static final int TIPOCOMTA = 2;

    public static int getTIPOCOMTA() {
        return TIPOCOMTA;
    }

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("======= Extrato CP =======");
        super.imprimirInfosGerais();
    }


}

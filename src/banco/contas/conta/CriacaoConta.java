package banco.contas.conta;

import banco.clientes.Cliente;

import java.util.Scanner;

public class CriacaoConta {
    Cliente novoCliente;

    public CriacaoConta() {
        this.novoCliente = new Cliente();
    }

    public Cliente criaNovaConta(Integer tipoConta, Scanner dadosCliente) {
        System.out.println("Entre com seu nome: ");
        novoCliente.setNome(dadosCliente.nextLine());
        System.out.println("Entre com seu email: ");
        novoCliente.setEmail(dadosCliente.nextLine());
        System.out.println("Entre com seu endereço: ");
        novoCliente.setEndereco(dadosCliente.nextLine());
        System.out.println("Entre com seu numero de celular: ");
        novoCliente.setTelefone(dadosCliente.nextLine());
        novoCliente.setTipoConta(tipoConta);
        return novoCliente;
    }

    @Override
    public String toString() {
        return "CriacaoConta{" +
                "novoCliente=" + novoCliente +
                '}';
    }
}

import banco.clientes.Cliente;
import banco.contas.ContaCorrente;
import banco.contas.ContaPoupanca;
import banco.contas.conta.Conta;
import banco.contas.conta.CriacaoConta;
import banco.instituicao.Banco;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Supplier;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();
        CriacaoConta novaConta = new CriacaoConta();
        Supplier<Integer> cc = ContaCorrente::getTIPOCOMTA;
        Supplier<Integer> cp = ContaPoupanca::getTIPOCOMTA;
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("Entre com uma opção para começar!");
            System.out.println("1 - Para criar uma conta corrente.");
            System.out.println("2 - Para criar uma conta Poupança.");
            System.out.println("3 - Para depositar.");
            System.out.println("4 - Para sacar.");
            System.out.println("5 - Para Transferir.");
            System.out.println("6 - Para ver o saldo.");
            System.out.println("7 - Para encerrar conta.");
            System.out.println("8 - Encerrar atendimento");

            int escolha = scanner.nextInt();
            scanner.nextLine();
            switch (escolha){
                case 1:
                    System.out.println("Entre com Seus dados");
                    Cliente novoClienteCC = novaConta.criaNovaConta(cc.get(), scanner);
                    Conta contaCorrente = new ContaCorrente(novoClienteCC);
                    banco.inseriContaNaList(contaCorrente);
                    banco.limparTerminal();
                    contaCorrente.imprimirInfosGerais();
                    break;

                case 2:
                    System.out.println("Entre com Seus dados");
                    Cliente novoClienteCP = novaConta.criaNovaConta(cp.get(), scanner);
                    Conta contaPoupanca = new ContaPoupanca(novoClienteCP);
                    banco.inseriContaNaList(contaPoupanca);
                    banco.limparTerminal();
                    contaPoupanca.imprimirInfosGerais();
                    break;

                case 3:
                    System.out.println("Entre com o numero da conta");
                    int numerodigitado = scanner.nextInt();
                    List<Conta> listContas = banco.getContas();
                    Optional<Conta> contaUsuario = listContas.stream()
                        .filter(c -> c.getNunConta() == numerodigitado)
                        .findFirst();

                    if(contaUsuario.isPresent()) {
                        System.out.println("Digite o valor para depósito:");
                        double valorDeposito = scanner.nextDouble();
                        contaUsuario.get().depositar(valorDeposito);
                        System.out.println("Depósito realizado com sucesso!");
                    } else {
                        System.out.println("Esta conta não foi encontrada");
                    }
                    break;

                case 4:
                    System.out.println("Entre com o numero da conta");
                    numerodigitado = scanner.nextInt();
                    listContas = banco.getContas();
                    contaUsuario = listContas.stream()
                        .filter(c -> c.getNunConta() == numerodigitado)
                        .findFirst();

                    if(contaUsuario.isPresent()) {
                        System.out.println("Digite o valor para saque:");
                        double valorSaque = scanner.nextDouble();
                        contaUsuario.get().sacar(valorSaque);
                        System.out.println("Saque realizado com sucesso!");
                    } else {
                        System.out.println("Esta conta não foi encontrada");
                    }
                    break;

                case 5:
                    System.out.println("Entre com o numero da conta de origem");
                    int numeroOrigem = scanner.nextInt();
                    System.out.println("Entre com o numero da conta de destino");
                    int numeroDestino = scanner.nextInt();

                    listContas = banco.getContas();
                    Optional<Conta> contaOrigem = listContas.stream()
                        .filter(c -> c.getNunConta() == numeroOrigem)
                        .findFirst();
                    Optional<Conta> contaDestino = listContas.stream()
                        .filter(c -> c.getNunConta() == numeroDestino)
                        .findFirst();

                    if(contaOrigem.isPresent() && contaDestino.isPresent()) {
                        System.out.println("Digite o valor para transferência:");
                        double valorTransferencia = scanner.nextDouble();
                        contaOrigem.get().transferir(valorTransferencia, contaDestino.get());
                        System.out.println("Transferência realizada com sucesso!");
                    } else {
                        System.out.println("Uma ou ambas as contas não foram encontradas");
                    }
                    break;

                case 6:
                    System.out.println("Entre com o numero da conta que deseja ver o saldo.");
                    int nConta = scanner.nextInt();
                    listContas = banco.getContas();
                    contaUsuario = listContas.stream()
                            .filter(c -> c.getNunConta() == nConta)
                            .findFirst();

                    if(contaUsuario.isPresent()){
                        contaUsuario.get().imprimirExtrato();
                    } else {
                        System.out.println("Esta conta não foi encontrada");
                    }
                    break;

                case 7:
                    System.out.println("Entre com o numero da conta para encerrar");
                    numerodigitado = scanner.nextInt();
                    listContas = banco.getContas();
                    contaUsuario = listContas.stream()
                        .filter(c -> c.getNunConta() == numerodigitado)
                        .findFirst();

                    if(contaUsuario.isPresent()) {
                        banco.getContas().remove(contaUsuario.get());
                        System.out.println("Conta encerrada com sucesso!");
                    } else {
                        System.out.println("Esta conta não foi encontrada");
                    }
                    break;

                case 8:
                    System.out.println("Atendimento encerrado!");
                    scanner.close();
                    return;

                default:
                    throw new IllegalStateException("Opção inválida: " + escolha);
            }
        }
    }
}
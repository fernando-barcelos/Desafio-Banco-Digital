package banco.contas.conta;

import banco.clientes.Cliente;

import java.util.Optional;
import java.util.Scanner;

public abstract class Conta implements IConta{
    private static int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int nunConta;
    protected double saudo = 0d;
    protected Cliente cliente;

    //constructor
    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.nunConta = SEQUENCIAL++;
        this.cliente = cliente;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getNunConta() {
        return nunConta;
    }

    public void setNunConta(int nunConta) {
        this.nunConta = nunConta;
    }

    public double getSaudo() {
        return saudo;
    }

    public void setSaudo(double saudo) {
        this.saudo = saudo;
    }

    //metodos herdado via implements do IConta
    @Override
    //saca valor da conta
    public void sacar(double valor) {
        saudo -= valor;
    }

    @Override
    //deposita valor na conta
    public void depositar(double valor) {
        saudo += valor;
    }

    @Override
    //saca de uma conta e deposita em outra
    public void transferir(double valor, Conta contaDestino) {
        this.sacar(valor);
        contaDestino.depositar(valor);
    }

    @Override
    public void encerrarConta(Conta conta) {
        Scanner valor = new Scanner(System.in);
        if (conta != null) {
            while (conta.getSaudo() != 0){
                System.out.println("Para encerrar a conta o saldo deve estar zerado.");
                System.out.printf("O saldo atual e %.2f%n", this.saudo);
                double valorDigitado = valor.nextDouble();
                sacar(valorDigitado);
                this.saudo += valorDigitado;
            }
        }
    }

    protected void imprimeExtrato() {
        System.out.printf("Titular: %s%n", this.cliente.getNome());
        System.out.printf("Saldo: %.2f%n", this.saudo);
    }

    public void imprimirInfosGerais(){
        System.out.printf("Titular: %s%n", this.cliente.getNome());
        System.out.printf("Agencia: %d%n", this.agencia);
        System.out.printf("Conta: %d%n", this.nunConta);
        System.out.printf("Saldo: %.2f%n", this.saudo);
    }
}

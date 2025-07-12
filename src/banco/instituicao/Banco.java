package banco.instituicao;

import banco.contas.conta.Conta;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private List<Conta> contasList;

    public Banco() {
        this.contasList = new ArrayList<>();
    }

    public void inseriContaNaList(Conta conta){
        contasList.add(conta);
    }

    public Conta removerContaDaList(Conta conta){
        Conta contaParaRemover = null;
        if (conta != null) {
            for(Conta c : contasList){
                if (c.getNunConta() == conta.getNunConta()) {
                    contasList.remove(contaParaRemover);
                }
            }
        }
        return contaParaRemover;
    }

    public List<Conta> getContas() {
        return contasList;
    }

    public void setContas(List<Conta> contas) {
        this.contasList = contas;
    }

    public void limparTerminal(){
        try {
            final String OS = System.getProperty("os.name");
            if (OS.contains("Windows")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (final Exception e){
            System.out.println("Não foi possível limpar a tela");
        }
    }
}

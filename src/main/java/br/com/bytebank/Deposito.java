package br.com.bytebank;
import java.math.BigDecimal;
public final class Deposito implements OperacaoBancaria {
    public void executar(Conta conta, BigDecimal valor) {
        if (conta == null) throw new IllegalArgumentException("Conta invalida");
        conta.depositar(valor);
    }
}

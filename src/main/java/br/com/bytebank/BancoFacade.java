package br.com.bytebank;
import java.math.BigDecimal;
import java.util.Map;
/** Facade: simplifica selecao da estrategia, execucao e registro. */
public final class BancoFacade {
    private final Map<String, OperacaoBancaria> operacoes = Map.of(
        "DEPOSITO", new Deposito(), "SAQUE", new Saque());
    public BigDecimal processar(Conta conta, String tipo, BigDecimal valor) {
        if (conta == null) throw new IllegalArgumentException("Conta invalida");
        if (tipo == null || !operacoes.containsKey(tipo)) throw new IllegalArgumentException("Operacao invalida");
        operacoes.get(tipo).executar(conta, valor);
        RegistroOperacoes.INSTANCE.registrar(tipo + " " + valor.toPlainString()
            + " | saldo: " + conta.getSaldo().toPlainString());
        return conta.getSaldo();
    }
}

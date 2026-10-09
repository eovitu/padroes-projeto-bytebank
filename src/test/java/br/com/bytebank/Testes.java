package br.com.bytebank;
import java.math.BigDecimal;
public final class Testes {
    private static int total;
    static BigDecimal valor(String v) { return new BigDecimal(v); }
    static void igual(String esperado, BigDecimal real) {
        if (valor(esperado).compareTo(real) != 0) throw new AssertionError(esperado + " != " + real);
        total++;
    }
    static void rejeita(Runnable acao) {
        try { acao.run(); } catch (IllegalArgumentException e) { total++; return; }
        throw new AssertionError("Operacao deveria falhar");
    }
    public static void main(String[] args) {
        Conta conta = new Conta(valor("100"));
        new Deposito().executar(conta, valor("50")); igual("150", conta.getSaldo());
        Conta saque = new Conta(valor("80"));
        new Saque().executar(saque, valor("30")); igual("50", saque.getSaldo());
        rejeita(() -> saque.sacar(valor("60"))); igual("50", saque.getSaldo());
        rejeita(() -> conta.depositar(valor("0")));
        rejeita(() -> conta.depositar(valor("-1")));
        rejeita(() -> conta.sacar(valor("0")));
        rejeita(() -> conta.sacar(valor("-1"))); igual("150", conta.getSaldo());
        rejeita(() -> new Conta(valor("-1")));
        rejeita(() -> new Conta(null));
        rejeita(() -> conta.depositar(null));
        rejeita(() -> conta.sacar(null));
        Conta decimal = new Conta(valor("0.10"));
        decimal.depositar(valor("0.20")); igual("0.30", decimal.getSaldo());
        Conta zerada = new Conta(valor("0")); igual("0", zerada.getSaldo());
        saque.sacar(valor("50")); igual("0", saque.getSaldo());
        System.out.println(total + " verificacoes passaram.");
    }
}

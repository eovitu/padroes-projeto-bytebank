package br.com.bytebank;
import java.math.BigDecimal;
/** Protege as invariantes do saldo sem arredondamento implicito. */
public final class Conta {
    private BigDecimal saldo;
    public Conta(BigDecimal saldoInicial) {
        if (saldoInicial == null || saldoInicial.signum() < 0)
            throw new IllegalArgumentException("Saldo inicial invalido");
        saldo = saldoInicial;
    }
    public BigDecimal getSaldo() { return saldo; }
    public void depositar(BigDecimal valor) {
        validar(valor);
        saldo = saldo.add(valor);
    }
    public void sacar(BigDecimal valor) {
        validar(valor);
        if (valor.compareTo(saldo) > 0) throw new IllegalArgumentException("Saldo insuficiente");
        saldo = saldo.subtract(valor);
    }
    private void validar(BigDecimal valor) {
        if (valor == null || valor.signum() <= 0) throw new IllegalArgumentException("Valor invalido");
    }
}

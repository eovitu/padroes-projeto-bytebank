package br.com.bytebank;
import java.math.BigDecimal;
public final class Aplicacao {
    public static void main(String[] args) {
        Conta conta = new Conta(new BigDecimal("100.00"));
        BancoFacade banco = new BancoFacade();
        System.out.println("Deposito: " + banco.processar(conta, "DEPOSITO", new BigDecimal("50.00")));
        System.out.println("Saque: " + banco.processar(conta, "SAQUE", new BigDecimal("30.00")));
        try { banco.processar(conta, "SAQUE", new BigDecimal("200.00")); }
        catch (IllegalArgumentException e) { System.out.println("Erro: " + e.getMessage()); }
        System.out.println("Saldo preservado: " + conta.getSaldo());
        System.out.println("Operacoes registradas:");
        RegistroOperacoes.INSTANCE.listar().forEach(System.out::println);
    }
}

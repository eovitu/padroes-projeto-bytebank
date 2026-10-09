package br.com.bytebank;
import java.math.BigDecimal;
/** Strategy: contrato comum para diferentes operacoes. */
public interface OperacaoBancaria {
    void executar(Conta conta, BigDecimal valor);
}

package br.com.bytebank;
import java.util.ArrayList;
import java.util.List;
/** Singleton: uma instancia por processo Java, sem persistencia. */
public enum RegistroOperacoes {
    INSTANCE;
    private final List<String> registros = new ArrayList<>();
    public synchronized void registrar(String registro) {
        if (registro == null || registro.isBlank()) throw new IllegalArgumentException("Registro invalido");
        registros.add(registro);
    }
    public synchronized List<String> listar() { return List.copyOf(registros); }
}

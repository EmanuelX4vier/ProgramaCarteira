package Programa.Sistema;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Data implements Serializable {

    private static final long serialVersionUID = 1L;
    private String data;

    public Data() {
        // Pega a data e hora atual do sistema
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.data = agora.format(formatter);
    }

    @Override
    public String toString() {
        return data;
    }
}

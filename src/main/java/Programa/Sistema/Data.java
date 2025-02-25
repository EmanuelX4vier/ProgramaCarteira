package Programa.Sistema;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Data implements Serializable {

    private String data;

    DateTimeFormatter padraoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate dataHoje = LocalDate.now();
    String dataHojeFormatada = dataHoje.format(padraoBR);

    public Data(){
        this.data = dataHojeFormatada;
    }

    @Override
    public String toString() {
        return dataHojeFormatada;

    }
}


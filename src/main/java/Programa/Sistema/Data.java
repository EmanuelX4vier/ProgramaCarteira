package Programa.Sistema;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;

public class Data implements Serializable {

    private String data;

    Calendar c = Calendar.getInstance();
    DateFormat dfb = DateFormat.getInstance();

    public Data (){
        this.data = dfb.format(c.getTime());
    }

    public String toString(){
        return data;
    }
}


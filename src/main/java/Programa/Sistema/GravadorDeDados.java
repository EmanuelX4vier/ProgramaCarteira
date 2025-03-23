package Programa.Sistema;

import Programa.Transacoes.Entrada;
import Programa.Transacoes.Saida;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GravadorDeDados implements Serializable {

    private String dadosFinanceiros;

    public GravadorDeDados(){
        this.dadosFinanceiros = "dadosFinanceiros.dat";
    }

    public void gravaDados (Usuario usuario) throws IOException{
        try(FileOutputStream arq = new FileOutputStream(this.dadosFinanceiros)){
            ObjectOutputStream obj = new ObjectOutputStream(arq);
            obj.writeObject(usuario);
            obj.flush();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public Usuario recuperaDados() throws IOException {
        try (FileInputStream arq = new FileInputStream(this.dadosFinanceiros)) {
            ObjectInputStream obj = new ObjectInputStream(arq);
            Usuario usuario = (Usuario)obj.readObject();
            return usuario;
        } catch (ClassNotFoundException | ClassCastException e) {
            throw new IOException(e);
        }
    }

}

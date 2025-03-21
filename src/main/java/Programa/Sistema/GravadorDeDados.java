package Programa.Sistema;

import Programa.Transacoes.Entrada;
import Programa.Transacoes.Saida;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GravadorDeDados implements Serializable {

    private String dadosFinanceiros;

    public GravadorDeDados(){
        this.dadosFinanceiros = "dadosFinanceiros.dat";
    }

    public void gravaDados (Usuario usuarios) throws IOException{
        ArrayList<Usuario> usuariosArrayList = new ArrayList<>();
        usuariosArrayList.add(usuarios);
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.dadosFinanceiros))){
            out.writeObject(usuariosArrayList);
        }
    }

    public Usuario recuperaDados() throws IOException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.dadosFinanceiros))) {
            //Recupera em forma de lista.
            List<Usuario> infoUsuarioRecuperado = (ArrayList<Usuario>) in.readObject();
            //Transforma em usuario.
            Usuario usuario = new Usuario();
            for (Usuario u : infoUsuarioRecuperado) {
                Usuario usuarioRecuperado = new Usuario(u.getNome(), u.getSaldoCorrente());
                List<Entrada> entradasRecuperadas = new ArrayList<>(u.getEntradas());
                List<Saida> saidasRecuperadas = new ArrayList<>(u.getSaidas());
                usuarioRecuperado.setEntradas(entradasRecuperadas);
                usuarioRecuperado.setSaidas(saidasRecuperadas);
                usuario = usuarioRecuperado;
            }
            return usuario;
        } catch (ClassNotFoundException | ClassCastException e) {
            throw new IOException(e);
        }
    }

}

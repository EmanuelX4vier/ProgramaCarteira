package Programa.Sistema;

import java.io.*;

public class GravadorDeDados implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String arquivoDados;

    public GravadorDeDados() {
        this.arquivoDados = "dadosFinanceiros.dat";
    }

    // Salva o usuário no arquivo
    public void gravaDados(Usuario usuario) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivoDados))) {
            oos.writeObject(usuario);
        } catch (IOException e) {
            throw new IOException("Erro ao gravar dados do usuário.", e);
        }
    }

    // Recupera o usuário do arquivo
    public Usuario recuperaDados() throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoDados))) {
            return (Usuario) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Classe do usuário não encontrada ao recuperar dados.", e);
        } catch (ClassCastException e) {
            throw new IOException("Erro de conversão ao recuperar dados do usuário.", e);
        }
    }

    // Verifica se o arquivo de dados existe
    public boolean dadosExistem() {
        File file = new File(arquivoDados);
        return file.exists() && file.length() > 0;
    }
}

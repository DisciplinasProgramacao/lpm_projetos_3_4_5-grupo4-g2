package services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import models.Usuario;
import utils.Utilidade;

public class UsuarioService {

    private String finalPath = Utilidade.CSV_FOLDER_PATH+"POO_Usuarios.csv";

    public void cadastrarUsuario(Usuario usuario) {
        String str = usuario.getNome()+";"+
                    usuario.getUser()+";"+
                    usuario.getSenha();

        Utilidade.escrever(str, finalPath);
    }

    public void removerUsuario(Usuario usuario) {
        
        String str = usuario.getNome()+";"+
                    usuario.getUser()+";"+
                    usuario.getSenha();

        try {
            ArrayList<String> oldLines = new ArrayList<String>(Files.readAllLines(Path.of(finalPath)));
            ArrayList<String> newLines =  new ArrayList<String>();

            newLines.addAll(oldLines);

            for(String s : oldLines) {
                String[] aux = s.split(";");
                if(aux[1].equals(usuario.getUser())) {
                    newLines.remove(s);
                }
            }

        }catch(Exception e) {
            e.getMessage();
        }
        
        Utilidade.escrever(str, finalPath);
    }


    public List<Usuario> preencherUsuarios(){
        try {
            List<Usuario> usuarios = Files.lines(Paths.get(finalPath))
            .map(lines -> lines.split(";"))
            .map(col -> new Usuario(col[0], col[1], col[2], Integer.parseInt(col[3])))
            .collect(Collectors.toList());

            return usuarios;

        } catch(Exception e) {
            e.getMessage();
        }
        return null;
    }
}

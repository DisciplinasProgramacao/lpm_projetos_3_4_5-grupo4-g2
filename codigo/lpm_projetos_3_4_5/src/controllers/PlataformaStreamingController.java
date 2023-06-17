package controllers;

import models.PlataformaStreaming;
import models.Usuario;

public class PlataformaStreamingController {

    public PlataformaStreaming plataforma = PlataformaStreaming.getInstance("Meu Streaming");
      
    public void login(String user, String senha) {
        for(Usuario u : plataforma.getUsuarios()) {
            if(u.getUser().equals(user) && u.getSenha().equals(senha)) {
                Usuario usuarioAtual = new Usuario(u.getNome(), u.getUser(), u.getSenha(), u.getRole());
                plataforma.setUsuarioAtual(usuarioAtual);
            }
        }
    }

    public void logoff() {
        plataforma.setUsuarioAtual(null);
    }

    public void listarTodas() {
        plataforma.getMidias().forEach(m -> System.out.println(m.toString()));
    }

    public void listarPorNome(String nome) {
        plataforma.getMidias().stream().filter(m -> m.getNome().contains(nome))
        .forEach(m -> System.out.println(m.toString()));
    }

    public void listarPorGenero(String genero) {
        plataforma.getMidias().stream().filter(m -> m.getGenero().equals(genero))
        .forEach(m -> System.out.println(m.toString()));
    }

    public void listarPorIdioma(String idioma) {
        plataforma.getMidias().stream().filter(m -> m.getIdioma().equals(idioma))
        .forEach(m -> System.out.println(m.toString()));
    }
}

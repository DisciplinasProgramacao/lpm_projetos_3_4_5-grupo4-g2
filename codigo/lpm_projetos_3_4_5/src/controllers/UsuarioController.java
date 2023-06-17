package controllers;

import java.util.List;

import models.Usuario;
import services.UsuarioService;

public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController() {
        this.usuarioService = new UsuarioService();
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarioService.cadastrarUsuario(usuario);
    }

    public void removerUsuario(Usuario usuario) {
        usuarioService.removerUsuario(usuario);
    }

    public List<Usuario> preencherUsuarios() {
        return usuarioService.preencherUsuarios();
    }
}

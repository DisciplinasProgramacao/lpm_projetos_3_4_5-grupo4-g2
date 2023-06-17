package controllers;

import java.util.List;

import models.Usuario;
import services.UsuarioService;

public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController() {
        this.usuarioService = new UsuarioService();
    }

    public static void cadastrarUsuario(Usuario usuario) {
        UsuarioController.cadastrarUsuario(usuario);
    }

    public static void removerUsuario(Usuario usuario) {
        UsuarioController.removerUsuario(usuario);
    }

    public List<Usuario> preencherUsuarios() {
        return usuarioService.preencherUsuarios();
    }
}

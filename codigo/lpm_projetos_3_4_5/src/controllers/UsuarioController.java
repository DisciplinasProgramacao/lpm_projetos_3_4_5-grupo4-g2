package controllers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import models.Audiencia;
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

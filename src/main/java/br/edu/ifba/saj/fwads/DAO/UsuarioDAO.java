package br.edu.ifba.saj.fwads.DAO;

import br.edu.ifba.saj.fwads.model.Usuario;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UsuarioDAO extends GenericDAOImpl<Usuario, UUID> {

    public UsuarioDAO() {
        super(UUID.class);

    }
    public List<Usuario> buscarOrdenadosPorNome() {
        return buscarTodos().stream()
                .sorted(Comparator.comparing(Usuario::getNome))
                .collect(Collectors.toList());
    }

    public Usuario buscarPorEmail(String email) {
        return buscarTodos().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public boolean autenticar(String email, String senha) {
        Usuario usuario = buscarPorEmail(email);
        return usuario != null && usuario.getSenha().equals(senha);
    }
}
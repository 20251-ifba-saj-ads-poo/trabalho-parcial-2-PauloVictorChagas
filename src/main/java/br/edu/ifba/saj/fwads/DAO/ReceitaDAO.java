package br.edu.ifba.saj.fwads.DAO;

import br.edu.ifba.saj.fwads.model.Receita;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ReceitaDAO extends GenericDAOImpl<Receita, UUID> {
    public ReceitaDAO() {
        super(UUID.class);
    }
    public List<Receita> buscarPorUsuario(UUID usuarioId) {
        return buscarTodos().stream()
                .filter(r -> r.getUsuarioId().equals(usuarioId))
                .collect(Collectors.toList());
    }

    public List<Receita> buscarPorMesAno(UUID usuarioId, int mes, int ano) {
        return buscarTodos().stream()
                .filter(r -> r.getUsuarioId().equals(usuarioId) && r.getData().getMonthValue() == mes && r.getData().getYear() == ano)
                .collect(Collectors.toList());
    }
}
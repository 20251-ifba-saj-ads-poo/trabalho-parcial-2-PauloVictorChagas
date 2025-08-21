package br.edu.ifba.saj.fwads.DAO;

import br.edu.ifba.saj.fwads.model.Lancamento;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class LancamentoDAO extends GenericDAOImpl<Lancamento, UUID> {
    public LancamentoDAO() {
        super(UUID.class);
    }

    public List<Lancamento> buscarPorUsuario(UUID usuarioId) {
        return buscarTodos().stream()
                .filter(l -> l.getUsuarioId().equals(usuarioId))
                .collect(Collectors.toList());
    }

    public List<Lancamento> buscarPorTipo(UUID usuarioId, String tipo) {
        return buscarTodos().stream()
                .filter(l -> l.getUsuarioId().equals(usuarioId) && l.getTipo().equals(tipo))
                .collect(Collectors.toList());
    }

    public List<Lancamento> buscarPorMesAno(UUID usuarioId, int mes, int ano) {
        return buscarTodos().stream()
                .filter(l -> l.getUsuarioId().equals(usuarioId) && l.getData().getMonthValue() == mes && l.getData().getYear() == ano)
                .collect(Collectors.toList());
    }
}
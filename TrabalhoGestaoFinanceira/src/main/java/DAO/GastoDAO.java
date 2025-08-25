package DAO;

import Model.Gasto;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class GastoDAO extends GenericDAOImpl<Gasto, UUID> {
    public GastoDAO() {
        super(UUID.class);
    }

    public List<Gasto> buscarPorUsuario(UUID usuarioId) {
        return buscarTodos().stream()
                .filter(g -> g.getUsuarioId().equals(usuarioId))
                .collect(Collectors.toList());
    }

    public List<Gasto> buscarGastosFixosPorUsuario(UUID usuarioId) {
        return buscarTodos().stream()
                .filter(g -> g.getUsuarioId().equals(usuarioId) && g.isGastoFixo())
                .collect(Collectors.toList());
    }

    public List<Gasto> buscarPorMesAno(UUID usuarioId, int mes, int ano) {
        return buscarTodos().stream()
                .filter(g -> g.getUsuarioId().equals(usuarioId) && g.getData().getMonthValue() == mes && g.getData().getYear() == ano)
                .collect(Collectors.toList());
    }
}
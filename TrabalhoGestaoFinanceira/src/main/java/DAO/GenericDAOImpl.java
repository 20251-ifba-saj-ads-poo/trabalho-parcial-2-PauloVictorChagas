package DAO;

import Model.AbstractModel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GenericDAOImpl<T extends AbstractModel<UUID>, ID> implements GenericDAO<T, UUID> {

    protected Map<UUID, T> bancoDeDados = new HashMap<>();

    public GenericDAOImpl(Class<UUID> uuidClass) {
    }

    @Override
    public UUID salvar(T entidade) {
        UUID novoId = UUID.randomUUID();

        entidade.setId(novoId);
        entidade.setCreatedAt(LocalDateTime.now());
        bancoDeDados.put(entidade.getId(), entidade);
        return novoId;
    }

    @Override
    public void atualizar(T entidade) {
        if (entidade.getId() != null && bancoDeDados.containsKey(entidade.getId())) {
            entidade.setUpdatedAt(LocalDateTime.now());
            bancoDeDados.put(entidade.getId(), entidade);
        }
    }

    @Override
    public T buscarPorId(UUID id) {
        return bancoDeDados.get(id);
    }

    @Override
    public void deletar(UUID id) {
        bancoDeDados.remove(id);
    }

    @Override
    public List<T> buscarTodos() {
        return new ArrayList<>(bancoDeDados.values());
    }
}
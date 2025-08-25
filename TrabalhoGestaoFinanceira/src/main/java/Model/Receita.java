package Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Receita extends AbstractModel<UUID> {
    private String nome;
    private BigDecimal valor;
    private LocalDate data;
    private UUID usuarioId;

    public Receita(String nome, BigDecimal valor, LocalDate data, UUID usuarioId) {
        this.nome = nome;
        this.valor = valor;
        this.data = data;
        this.usuarioId = usuarioId;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }

    @Override
    public String toString() {
        return "Receita{" + "id=" + getId() + ", nome='" + nome + '\'' + ", valor=" + valor +
                ", data=" + data + ", usuarioId=" + usuarioId + '}';
    }
}
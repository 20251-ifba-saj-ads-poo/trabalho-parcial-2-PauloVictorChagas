package Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Lancamento extends AbstractModel<UUID> {
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    private String tipo; // receita ou gasto
    private UUID usuarioId;

    public Lancamento(String descricao, BigDecimal valor, LocalDate data, String tipo, UUID usuarioId) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
        this.usuarioId = usuarioId;
    }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }

    @Override
    public String toString() {
        return "Lancamento{" + "id=" + getId() + ", descricao='" + descricao + '\'' + ", valor=" + valor +
                ", data=" + data + ", tipo='" + tipo + '\'' + ", usuarioId=" + usuarioId + '}';
    }
}
package br.edu.ifba.saj.fwads.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Gasto extends AbstractModel<UUID> {
    private String nomeGasto;
    private BigDecimal valorGasto;
    private LocalDate data;
    private boolean gastoFixo;
    private LocalDate dataFimGastoFixo;
    private UUID usuarioId;

    public Gasto(String nomeGasto, BigDecimal valorGasto, LocalDate data, boolean gastoFixo, UUID usuarioId) {
        this.nomeGasto = nomeGasto;
        this.valorGasto = valorGasto;
        this.data = data;
        this.gastoFixo = gastoFixo;
        this.usuarioId = usuarioId;
    }

    public Gasto(String nomeGasto, BigDecimal valorGasto, LocalDate data, boolean gastoFixo, LocalDate dataFimGastoFixo, UUID usuarioId) {
        this(nomeGasto, valorGasto, data, gastoFixo, usuarioId);
        this.dataFimGastoFixo = dataFimGastoFixo;
    }

    public String getNomeGasto() { return nomeGasto; }
    public void setNomeGasto(String nomeGasto) { this.nomeGasto = nomeGasto; }
    public BigDecimal getValorGasto() { return valorGasto; }
    public void setValorGasto(BigDecimal valorGasto) { this.valorGasto = valorGasto; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public boolean isGastoFixo() { return gastoFixo; }
    public void setGastoFixo(boolean gastoFixo) { this.gastoFixo = gastoFixo; }
    public LocalDate getDataFimGastoFixo() { return dataFimGastoFixo; }
    public void setDataFimGastoFixo(LocalDate dataFimGastoFixo) { this.dataFimGastoFixo = dataFimGastoFixo; }
    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }

    @Override
    public String toString() {
        return "Gasto{" + "id=" + getId() + ", nomeGasto='" + nomeGasto + '\'' + ", valorGasto=" + valorGasto +
                ", data=" + data + ", gastoFixo=" + gastoFixo + ", dataFimGastoFixo=" + dataFimGastoFixo +
                ", usuarioId=" + usuarioId + '}';
    }
}
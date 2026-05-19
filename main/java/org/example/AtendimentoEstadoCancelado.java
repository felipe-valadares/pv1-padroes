package org.example;

public class AtendimentoEstadoCancelado extends AtendimentoEstado {
    private AtendimentoEstadoCancelado() {}
    private static AtendimentoEstadoCancelado instance = new AtendimentoEstadoCancelado();
    public static AtendimentoEstadoCancelado getInstance() {
        return instance;
    }

    @Override
    public String getEstado() {
        return "Cancelado";
    }
}

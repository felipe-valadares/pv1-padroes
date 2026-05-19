package org.example;

public class AtendimentoEstadoFinalizado extends AtendimentoEstado {
    private AtendimentoEstadoFinalizado() {}
    private static AtendimentoEstadoFinalizado instance = new AtendimentoEstadoFinalizado();
    public static AtendimentoEstadoFinalizado getInstance() {
        return instance;
    }

    @Override
    public String getEstado() {
        return "Finalizado";
    }
}

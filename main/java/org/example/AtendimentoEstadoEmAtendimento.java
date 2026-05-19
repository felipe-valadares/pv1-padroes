package org.example;

public class AtendimentoEstadoEmAtendimento extends AtendimentoEstado {
    private AtendimentoEstadoEmAtendimento() {}
    private static AtendimentoEstadoEmAtendimento instance = new AtendimentoEstadoEmAtendimento();
    public static AtendimentoEstadoEmAtendimento getInstance() {
        return instance;
    }

    @Override
    public String getEstado() {
        return "EmAtendimento";
    }

    @Override
    public boolean finalizar(Atendimento atendimento) {
        atendimento.setEstado(AtendimentoEstadoFinalizado.getInstance());
        return true;
    }
}

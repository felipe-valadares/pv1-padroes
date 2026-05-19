package org.example;

public class AtendimentoEstadoAgendado extends AtendimentoEstado {
    private AtendimentoEstadoAgendado() {}
    private static AtendimentoEstadoAgendado instance = new AtendimentoEstadoAgendado();
    public static AtendimentoEstadoAgendado getInstance() {
        return instance;
    }

    @Override
    public String getEstado() {
        return "Agendado";
    }

    @Override
    public boolean iniciar(Atendimento atendimento) {
        atendimento.setEstado(AtendimentoEstadoEmAtendimento.getInstance());
        return true;
    }

    @Override
    public boolean cancelar(Atendimento atendimento) {
        atendimento.setEstado(AtendimentoEstadoCancelado.getInstance());
        return true;
    }
}

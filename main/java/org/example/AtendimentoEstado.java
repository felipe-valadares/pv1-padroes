package org.example;

public abstract class AtendimentoEstado {
    public abstract String getEstado();

    public boolean iniciar(Atendimento atendimento) {
        return false;
    }

    public boolean finalizar(Atendimento atendimento) {
        return false;
    }

    public boolean cancelar(Atendimento atendimento) {
        return false;
    }
}

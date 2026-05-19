package org.example;

public class Veterinario implements Observer {
    private String nome;
    private String notificacao;

    public Veterinario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String getNotificacao() {
        return notificacao;
    }

    @Override
    public void update(Observable o, Object arg) {
        Atendimento atendimento = (arg instanceof Atendimento) ? (Atendimento) arg : (Atendimento) o;
        if (atendimento.getEstado() instanceof AtendimentoEstadoCancelado) {
            this.notificacao = "Veterinário " + nome + ", o atendimento do animal " + 
                atendimento.getAnimal().getNome() + " foi cancelado.";
        }
    }
}

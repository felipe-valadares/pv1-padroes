package org.example;

public class Tutor implements Observer {
    private String nome;
    private String notificacao;

    public Tutor(String nome) {
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
        if (atendimento.getEstado() instanceof AtendimentoEstadoEmAtendimento) {
            this.notificacao = "Olá " + nome + ", o atendimento do seu animal " + 
                atendimento.getAnimal().getNome() + " foi iniciado!";
        }
    }
}

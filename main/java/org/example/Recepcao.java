package org.example;

public class Recepcao implements Observer {
    private String notificacao;

    public String getNotificacao() {
        return notificacao;
    }

    @Override
    public void update(Observable o, Object arg) {
        Atendimento atendimento = (arg instanceof Atendimento) ? (Atendimento) arg : (Atendimento) o;
        if (atendimento.getEstado() instanceof AtendimentoEstadoFinalizado) {
            this.notificacao = "Recepção, o atendimento do animal " + 
                atendimento.getAnimal().getNome() + " foi finalizado. Valor a pagar: R$" + 
                atendimento.getValorFinal();
        }
    }
}

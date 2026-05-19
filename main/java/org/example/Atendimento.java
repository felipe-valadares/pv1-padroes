package org.example;

public class Atendimento extends Observable {
    private Tutor tutor;
    private Animal animal;
    private ServicoVeterinario servicoVeterinario;
    private double valorBase;
    private AtendimentoEstado estado;
    private Pagamento pagamento;

    public Atendimento(Tutor tutor, Animal animal, ServicoVeterinario servicoVeterinario, double valorBase) {
        this.tutor = tutor;
        this.animal = animal;
        this.servicoVeterinario = servicoVeterinario;
        this.valorBase = valorBase;
        this.estado = AtendimentoEstadoAgendado.getInstance();
        this.pagamento = new PagamentoBase(valorBase);
    }

    public Tutor getTutor() {
        return tutor;
    }

    public Animal getAnimal() {
        return animal;
    }

    public ServicoVeterinario getServicoVeterinario() {
        return servicoVeterinario;
    }

    public double getValorBase() {
        return valorBase;
    }

    public double getValorFinal() {
        return pagamento.getValorFinal();
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public AtendimentoEstado getEstado() {
        return estado;
    }

    public void setEstado(AtendimentoEstado estado) {
        this.estado = estado;
        setChanged();
        notifyObservers(this);
    }

    public boolean iniciar() {
        return estado.iniciar(this);
    }

    public boolean finalizar() {
        return estado.finalizar(this);
    }

    public boolean cancelar() {
        return estado.cancelar(this);
    }
}

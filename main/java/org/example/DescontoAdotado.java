package org.example;

public class DescontoAdotado extends PagamentoDecorator {
    private Animal animal;

    public DescontoAdotado(Pagamento pagamentoDecorado, Animal animal) {
        super(pagamentoDecorado);
        this.animal = animal;
    }

    @Override
    public double getValorIncremento() {
        if (this.animal.isAdotado()) {
            return -0.1 * this.getPagamentoDecorado().getValorFinal();
        }
        return 0.0;
    }
}

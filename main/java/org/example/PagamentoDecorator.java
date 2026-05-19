package org.example;

public abstract class PagamentoDecorator implements Pagamento {
    private Pagamento pagamentoDecorado;

    public PagamentoDecorator(Pagamento pagamentoDecorado) {
        this.pagamentoDecorado = pagamentoDecorado;
    }

    public Pagamento getPagamentoDecorado() {
        return pagamentoDecorado;
    }

    public void setPagamentoDecorado(Pagamento pagamentoDecorado) {
        this.pagamentoDecorado = pagamentoDecorado;
    }

    public abstract double getValorIncremento();

    @Override
    public double getValorFinal() {
        return this.pagamentoDecorado.getValorFinal() + this.getValorIncremento();
    }
}

package model.service.layer.design;

public class BrazilTaxServices implements TaxService{

	@Override
	public Double tax(double amount) {
		if(amount <= 100.00) {
			return amount * 0.20;
		}
		else return amount * 0.15;
	}

}

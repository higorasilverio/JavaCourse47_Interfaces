package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.domain.layer.design.CarRental;
import model.domain.layer.design.Vehicle;
import model.service.layer.design.BrazilTaxServices;
import model.service.layer.design.RentalService;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner input = new Scanner(System.in);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		try {
			System.out.println("Enter rental data:");
			System.out.print("Car model: ");
			String model = input.nextLine();
			System.out.print("Pickup (dd/MM/yyyy hh:mm): ");
			Date start = sdf.parse(input.nextLine());
			System.out.print("Return (dd/MM/yyyy hh:mm): ");
			Date finish = sdf.parse(input.nextLine());
			CarRental carRent = new CarRental(start, finish, new Vehicle(model));
			
			System.out.print("Enter price per hour: ");
			double pricePerHour = input.nextDouble();
			System.out.print("Enter price per day: ");
			double pricePerDay = input.nextDouble();
			RentalService service = new RentalService(pricePerHour, pricePerDay, new BrazilTaxServices());
			
			service.processInvoice(carRent);
			
			System.out.println();
			System.out.println("INVOICE:");
			System.out.println("Basic payment: " + String.format("%.2f", carRent.getInvoice().getBasicPayment()));
			System.out.println("Tax: " + String.format("%.2f", carRent.getInvoice().getTax()));
			System.out.println("Total payment: " + String.format("%.2f", carRent.getInvoice().getTotalPayment()));
		}
		catch (ParseException e) {
			System.out.println("Error parsing: " + e.getMessage());
		}
		
		
		
		
		
		input.close();
	}

}

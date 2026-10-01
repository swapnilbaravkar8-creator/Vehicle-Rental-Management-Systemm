package Project;

import java.util.*;
public class Vehicle {
	
	private int vehicleId;
	private String vehiclename;
	protected double rentPerDay;
	protected boolean available;
	
	static int totalvehicles=0;
	final String COMPANY_NAME="ABC Vehicle Rentals";
	
	 Vehicle(int vehicleId, String vehiclename, double rentPerDay) {
		
		this.vehicleId = vehicleId;
		this.vehiclename = vehiclename;
		this.rentPerDay = rentPerDay;
		this.available = true;
		
		totalvehicles++;
	}
	
	public int getVehicleId() {
		return vehicleId;
	}
	
	public String getVehicleName() {
		return vehiclename;
	}
	
	double calculateRent(int days) {
		return rentPerDay*days;
	}
	
	void display() {
		System.out.println("ID        :"+vehicleId);
		System.out.println("Name      :"+vehiclename);
		System.out.println("Rent/Day  :"+rentPerDay);
		System.out.println("Available :"+available);
	}
	
	
}
package Project;

 class Car extends Vehicle {

	 Car(int vehicleId, String vehiclename, double rentPerDay) {
		super(vehicleId, vehiclename, rentPerDay);
	}

	 @Override
	 double calculateRent(int days) {
			return rentPerDay*days;
		}
	 

}
package Project;

class Bike extends Vehicle {

	Bike(int vehicleId, String vehiclename, double rentPerDay) {
		super(vehicleId, vehiclename, rentPerDay);
	}

	@Override
	double calculateRent(int days) {
		return rentPerDay * days;
	}

}
package Project;

class Truck extends Vehicle {

	Truck(int vehicleId, String vehiclename, double rentPerDay) {
		super(vehicleId, vehiclename, rentPerDay);
	}

	@Override
	double calculateRent(int days) {
		return rentPerDay * days;
	}

}
package Project;

import java.util.Scanner;

public class VehicleRentalSystem {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Vehicle[] vehicles = new Vehicle[10];

		int count = 0;
		int choice;

		vehicles[count++] = new Car(101, "Toyota Car", 1500);
		vehicles[count++] = new Bike(102, "KTM Bike", 500);
		vehicles[count++] = new Truck(103, "Tata Truck", 2500);

		while (true) {
			System.out.println("\n=====================");
			System.out.println("VEHICLE RENTAL MANAGEMENT");
			System.out.println("1.Add vehicle");
			System.out.println("2.Display Vehicles");
			System.out.println("3.Rent vehicle");
			System.out.println("4.Return Vehicle");
			System.out.println("5.Search Vehicle");
			System.out.println("6.Total vehicle");
			System.out.println("7.Exit");

			System.out.println("Enter your choice: ");
			choice = sc.nextInt();

			switch (choice) {

			case 1:
				if (count >= vehicles.length) {
					System.out.println("Vehicle Storage is full.");
					break;
				}

				System.out.println("Enter vehicle ID:");
				int id = sc.nextInt();

				sc.nextLine();

				System.out.println("Enter Vehicle Name: ");
				String name = sc.nextLine();

				System.out.println("1. Car");
				System.out.println("2. Bike");
				System.out.println("3. Truck");

				System.out.println("Enter Vehicle Type: ");
				int type = sc.nextInt();

				System.out.println("Enter Rent per Day: ");
				double rent = sc.nextDouble();

				if (type == 1) {
					vehicles[count++] = new Car(id, name, rent);

				} else if (type == 2) {
					vehicles[count++] = new Bike(id, name, rent);
				} else if (type == 3) {
					vehicles[count++] = new Truck(id, name, rent);
				} else {
					System.out.println("Invalid vehicle type.");
					break;
				}

				System.out.println("Vehicle added successfully.");
				break;

			case 2:
				if (count == 0) {
					System.out.println("No vehicles available.");
				} else {
					for (int i = 0; i < count; i++) {
						System.out.println("\n------------------");
						vehicles[i].display();
						System.out.println("Company : " + vehicles[i].COMPANY_NAME);
					}
				}

				break;

			case 3:
				System.out.println("Enter vehicle ID: ");
				int rentId = sc.nextInt();

				boolean found = false;

				for (int i = 0; i < count; i++) {
					if (vehicles[i].getVehicleId() == rentId) {
						found = true;

						if (vehicles[i].available) {
							System.out.println("Enter number of days: ");
							int days = sc.nextInt();

							if (days > 0) {
								double totalRent = vehicles[i].calculateRent(days);

								System.out.println("Total Rent: ₹" + totalRent);

								vehicles[i].available = false;

								System.out.println("Vehicle rented successfully.");

							} else {
								System.out.println("Dayss must be greater than 0.");
							}
						} else {
							System.out.println("Vehicle is already rented.");
						}
						break;
					}
				}

				if (!found) {
					System.out.println("vehicle not found");
				}

				break;

			case 4:
				System.out.print("Enter Vehicle ID: ");
				int returnId = sc.nextInt();
				boolean returnFound = false;
				for (int i = 0; i < count; i++) {
					if (vehicles[i].getVehicleId() == returnId) {
						returnFound = true;
						if (!vehicles[i].available) {
							vehicles[i].available = true;
							System.out.println("Vehicle returned successfully.");
						} else {
							System.out.println("Vehicle was not rented.");
						}
						break;
					}
				}
				if (!returnFound) {
					System.out.println("Vehicle not found.");
				}
				break;

			case 5:
				System.out.print("Enter Vehicle ID: ");
				int searchId = sc.nextInt();
				boolean searchFound = false;
				for (int i = 0; i < count; i++) {
					if (vehicles[i].getVehicleId() == searchId) {
						searchFound = true;
						System.out.println("\nVehicle Found");
						vehicles[i].display();
						break;
					}
				}
				if (!searchFound) {
					System.out.println("Vehicle not found.");
				}
				break;

			case 6:
				System.out.println("Total Vehicles Created: " + Vehicle.totalvehicles);
				break;

			case 7:
				System.out.println("Thank you for using Vehicle Rental System.");
				sc.close();
				return;

			default:
				System.out.println("Invalid choice.");

			}
		}
	}

}

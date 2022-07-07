package t_r_s;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.temporal.ValueRange;
import java.util.Scanner;

public class table_reservation_system implements ReservationSystem {

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
		Reservation s = new Reservation();
		
		s.run();
		while(true) {
		Scanner sc = new Scanner(System.in);
		System.out.println("____________________________________________________________________");
		System.out.println("                       MENU                                            ");
		System.out.println("____________________________________________________________________");

		System.out.println("                   TABLE RESERVATION SYSTEM                          ");
		System.out.println("____________________________________________________________________");
		System.out.println("            1.Add Reservation");
		System.out.println("            2.View Reservation List");
		System.out.println("            3.View By Reservation Id");
		System.out.println("            4.Delete Reservation by Id");
		System.out.println("            5.Confirm By Reservation Id");
		System.out.println("            6.Cancel By Reservation Id");
		System.out.println("            7.Sort Reservation");
		System.out.println("            8.Generate Report");
		System.out.println("            9.Exit");
		System.out.println("____________________________________________________________________");

		
		try {
			int val = sc.nextInt();
			if(val>9 || val<=0) {
				System.out.println("Enter Valid Number");
			}
		
		
		switch (val) {
		case 1:
			s.addDetails();
			break;

		case 2:
			s.ViewReservation();
			continue;

		case 3:
			
			System.out.println("----------------------------------------------------------------------------------------------------");
			System.out.println("                           View By Reservation Id                                   ");
			System.out.println("----------------------------------------------------------------------------------------------------");
			System.out.println("-> Enter Reservation ID");
			String id = sc.next();
			s.viewReservationByID(id);
			break;
	
		case 4:
			System.out.println("Enter ID to Delete");
			String dID = sc.next();
			s.deleteRbYid(dID);
			break;
			
		case 5:
			System.out.println("Enter ID to Confirm");
			String cID = sc.next();
			s.confByRiD(cID);
			break;
		case 6:
			System.out.println("Enter ID to Cancel");
			String caID = sc.next();
			s.cancByRiD(caID);
			break;
			
		case 7:
			System.out.println("Sort Reservation");
			s.sortReserve();
			break;
			
		case 8:
			System.out.println("Generate Report");
			
			s.generateRep();
			break;
		
		case 9:
			System.exit(0);
			
			
			break;
		}
		} catch (Exception e) {
			System.out.println("Select Numeric Number listed below from 1 to 9");
		}

	}
	}

	
}

package t_r_s;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.ListIterator;
import java.util.Scanner;

interface ReservationSystem {

	public class Reservation extends Thread {

		private static final String dataFilePath = "C:/Users/Amaresh/git/trs/Table_Reservation_System/reports/table_reservation_data.txt";

		private static String path = null, tPath = "Booked";

		private static int Reservation_Id, Adult, Children;

		private static String Reservation_Description, Customer_Name, Status;

		private LocalDateTime Reservation_Date = LocalDateTime.now();

		private double Sub_Total_Amount, Discount_Amount, Tax_Amount, Total_Amount;

		public void addDetails() {
			Scanner sc = new Scanner(System.in);
			Scanner st = new Scanner(System.in);
			ArrayList data = new ArrayList();

			do {

				try {
					System.out.println(
							"----------------------------------------------------------------------------------------------------------------");

					System.out.println("Hours = " + Reservation_Date.getHour());
					if (Reservation_Date.getHour() < 22 && Reservation_Date.getHour() > 7) {
						System.out.println(
								"----------------------------------------------------------------------------------------------------------------");
						System.out.println("->>>>>>>>>>>>>>>>>>>>>>WE ARE BACK -> " + " Happy To Serve You");
						System.out.println(
								"----------------------------------------------------------------------------------------------------------------");
					}
					System.out.println(
							"----------------------------------------------------------------------------------------------------------------");

					System.out.println("Please Enter Reservation Details");
					System.out.println(
							"----------------------------------------------------------------------------------------------------------------");

					System.out.println("-> Enter Reservation ID");

					Reservation_Date = LocalDateTime.now();
					int getYear = Reservation_Date.getYear();
					int id = sc.nextInt();
					String s2 = Integer.toString(id);

					String s1 = Integer.toString(getYear);

					String s3 = s1 + s2;
					System.out.println("ID Length = " + s3.length());

					try {
						Scanner scan = new Scanner(new File(dataFilePath));
						while (scan.hasNext()) {
							String line = scan.next();
							if (line.equals(s3)) {
								System.out.println("Duplicate Order Id. Please enter unique order id");
								addDetails();
							} else {
								break;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					if (s3.length() == 8) {
						Reservation_Id = Integer.parseInt(s3);
						data.add(Reservation_Id);// 1
					} else {
						System.out.println("ID is to long:please enter 4 digits only");
						addDetails();
					}

					System.out.println(Reservation_Id);

					System.out.println("-> Enter Your Name");
					Customer_Name = st.next();
					data.add(Customer_Name);

					System.out.println("-> Enter Reservation_Description");
					Reservation_Description = st.next();

					data.add(Reservation_Description);// 2

					System.out.println("-> Enter Reservation Date");
					Reservation_Date = LocalDateTime.now();
					data.add(Reservation_Date);// 3

					System.out.println("Date = " + Reservation_Date);

					// For each Adult cost 500 Rs & for children 300 Rs.
					System.out.println("-> Enter No of Adults ");
					Adult = sc.nextInt();
					data.add(Adult);// 4

					System.out.println("-> Enter No of Childrens");
					Children = sc.nextInt();
					data.add(Children);// 5

					Sub_Total_Amount = (Adult * 500) + (Children * 300);
					System.out.println("Sub Total Amount = " + Sub_Total_Amount);
					data.add(Sub_Total_Amount);// 6

					if (DayOfWeek.WEDNESDAY != null) {
						Discount_Amount = .2;
					} else {
						Discount_Amount = .25;
					}
					System.out.println("Discount You Won  = " + Discount_Amount);

					Tax_Amount = (((Sub_Total_Amount - Discount_Amount) / 100) * 5);
					data.add(Tax_Amount);// 7
					System.out.println("Toatl Tax = " + "Rs	" + Tax_Amount);

					Total_Amount = ((Sub_Total_Amount - Discount_Amount) + Tax_Amount);
					data.add(Total_Amount);// 8

					System.out.println("Total Amount = " + "Rs	" + Total_Amount);

					Status = "Booked";
					data.add(Status);

					System.out.println(
							"---------------------------------------------------------------------------------------------------");

					System.out.println("           Reservation Created Successfully");
					System.out.println(
							"---------------------------------------------------------------------------------------------------");
					System.out.println("-> Reservation ID = " + Reservation_Id);
					System.out.println("-> Reservation Sub Total Amount = " + Sub_Total_Amount);
					System.out.println("-> Reservation Sub Discount Amount = " + Discount_Amount);
					System.out.println("-> Reservation Sub Tax Amount = " + Tax_Amount);
					System.out.println("-> Reservation Total Amount = " + Tax_Amount);
					System.out.println("");
					System.out.println(
							"-----------------------------------------------------------------------------------------------");
					System.out.println(data);

					try {
						FileWriter fw = new FileWriter(dataFilePath, true);
						for (int i = 0; i < 10; i++) {
							fw.write(data.get(i) + "   ");
						}
						fw.write("\n");
						fw.close();
					} catch (Exception e) {
						System.out.println(e);
					}
					System.out.println(
							"-----------------------------------------------------------------------------------");
					System.out.println("            Order Added Successfully         ");

				} catch (Exception e) {
					System.out.println("We love's you bookings closed please come back tommorow");
				}
				System.out
						.println("-----------------------------------------------------------------------------------");
				System.out.println("                   Do you want to continue Yes/No                      ");

				String qes = sc.next();
				if (qes.equalsIgnoreCase("yes")) {
					addDetails();
				} else {
					System.out.println("Thank You We Would Love You To See You Soon");
					System.exit(0);
				}
				sc.close();
				st.close();
			} while (true);

		}

		public void ViewReservation() {
			try {
				FileInputStream fis = new FileInputStream(dataFilePath);
				int i = fis.read();

				System.out.println(
						"--------------------------------------------------------------------------------------------------------------------");
				System.out.println("Reservation_Id	|" + " Reservation_Description |" + " Reservation_Date |"
						+ " Adult	|" + " Children |" + " Sub_Total_Amount |" + " Tax_Amount |" + "Total_Amount |");
				System.out.println(
						"--------------------------------------------------------------------------------------------------------------------");
				while (i != -1) {
					System.out.print((char) i);
					i = fis.read();
				}

				System.out.println();
				System.out.println(
						"--------------------------------------------------------------------------------------------------------------------");
				System.out.println("                          End Of List");
				System.out.println(
						"_______________________________________________________________________________________________________________________");
				fis.close();
			} catch (FileNotFoundException ex) {
				System.out.println(ex);
				ex.getMessage();

			} catch (IOException ex) {
				System.out.println("No File Exist");
			}

		}

		public void viewReservationByID(String id) {

			try {
				Scanner scan = new Scanner(new File(dataFilePath));
				ArrayList<String> li = new ArrayList<String>();

				while (scan.hasNext()) {
					li.add(scan.next());
				}

				int ind = li.indexOf(id);

				for (int i = ind; i < ind + 10; i++) {
					System.out.print("   " + li.get(i) + "\n");
				}

				scan.close();
				System.out.println();
			} catch (Exception e) {
				System.out.println("ID Not Found");
			}
		}

		public void deleteRbYid(String dID) throws FileNotFoundException, ClassNotFoundException, IOException {
			try {
				ArrayList<String> lf = new ArrayList<>(Files.readAllLines(Paths.get(dataFilePath)));
				System.out.println(lf);
				System.out.println("----------------------------");
				int ind = 0;
				for (int i = 0; i < lf.size(); i++) {
					String str = (String) lf.get(i);
					if (str.startsWith(dID)) {
						break;
					}
					ind++;
				}
				lf.remove(ind);

				System.out.println(lf);

				File file = new File(dataFilePath);
				PrintWriter writer = new PrintWriter(file);
				writer.print("");
				writer.close();

				FileWriter fw = new FileWriter(dataFilePath, true);
				for (int i = 0; i < lf.size(); i++) {
					fw.write(lf.get(i) + " \n ");

				}

				fw.close();

			} catch (Exception e) {
				System.out.println("ID Not Found");
			}

		}

		public void loadfile() throws FileNotFoundException, IOException, ClassNotFoundException {

			try {
				Scanner scd = new Scanner(System.in);
				ListIterator li = null;
				ArrayList<String> lf = new ArrayList<>(Files.readAllLines(Paths.get(dataFilePath)));
				System.out.println(lf);
				System.out.println("----------------------------");
				li = lf.listIterator();
				while (li.hasNext())

					System.out.println(li.next());
				System.out.println("----------------------------");

			} catch (Exception e) {
				System.out.println(e);
			}

		}

		public void confByRiD(String cID) {
			try {
				Scanner scan = new Scanner(new File(dataFilePath));

				ArrayList lc = new ArrayList();
				while (scan.hasNext()) {
					lc.add(scan.next());
				}
				int ind = lc.indexOf(cID);
				;
				System.out.println(ind);

				for (int i = ind; i < ind + 8; i++) {

					lc.set((ind + 9), "Confirmed");
				}
				System.out.println(lc);
				File file = new File(dataFilePath);
				PrintWriter writer = new PrintWriter(file);
				writer.print("");
				writer.close();

				try {
					FileWriter fw = new FileWriter(dataFilePath, true);
					for (int i = 0; i < lc.size(); i++) {
						fw.write(lc.get(i) + "   ");
						if ((i + 1) % 10 == 0) {
							fw.write("\n");
						}

					}
					fw.write("\n");
					fw.close();
				} catch (Exception e) {
					System.out.println(e);
				}

			} catch (Exception e) {
				System.out.println(e);
			}
		}

		public void cancByRiD(String caID) {
			try {
				Scanner scan = new Scanner(new File(dataFilePath));

				ArrayList lc = new ArrayList();
				while (scan.hasNext()) {
					lc.add(scan.next());
				}
				int ind = lc.indexOf(caID);
				;
				System.out.println(ind);

				for (int i = ind; i < ind + 8; i++) {

					lc.set((ind + 9), "Canceled");

				}
				System.out
						.println("-----------------------------------------------------------------------------------");
				System.out.println("Reservation ID =  " + caID + " cancelled." + "\n Amount =  " + lc.get(ind + 8)
						+ "\n will be refund within 24 hours ");
				System.out
						.println("-----------------------------------------------------------------------------------");
				System.out.println(lc);

				File file = new File(dataFilePath);
				PrintWriter writer = new PrintWriter(file);
				writer.print("");
				writer.close();

				try {
					FileWriter fw = new FileWriter(dataFilePath, true);
					for (int i = 0; i < lc.size(); i++) {
						fw.write(lc.get(i) + "   ");
						if ((i + 1) % 10 == 0) {
							fw.write("\n");
						}

					}
					fw.write("\n");
					fw.close();

				} catch (Exception e) {
					System.out.println(e);
				}

			} catch (Exception e) {
				System.out.println(e);
			}

		}

		public void generateAll() {
			try {
				ArrayList lc = new ArrayList();
				Scanner scan = new Scanner(new File(dataFilePath));

				while (scan.hasNext()) {
					lc.add(scan.nextLine());
				}

				for (int i = 0; i < lc.size(); i++) {
					System.out.println(lc.get(i));
					if ((i + 10) % 10 == 0) {
						System.out.println(
								"----------------------------------------------------------------------------------------------------------------");
						System.out.println("\n");
					}

				}
				scan.close();

				// emptying file before writing into
				File file = new File("C:/Users/Amaresh/Documents/all_reports.txt");

				PrintWriter writer = new PrintWriter(file);
				writer.print("");
				writer.close();

				// writing into file all_reports
				createFileStamp();

				FileWriter fw = new FileWriter(path, true);
				for (int i = 0; i < lc.size(); i++) {
					fw.write(lc.get(i) + "   ");
					if ((i + 1) % 10 == 0) {
						fw.write("\n");
					}
				}
				fw.write("\n");
				fw.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		}

		public void generateRep() {
			Scanner sc = new Scanner(System.in);

			System.out.println("******** Choose Report Type*********");
			System.out.println("1.Export All");
			System.out.println("2.Export By Status");
			System.out.println("Enter Your Option");
			int opt = sc.nextInt();
			switch (opt) {
			case 1:
				generateAll();
				break;
			case 2:
				System.out.println("******** Choose Status*********");
				System.out.println("1.Booked");
				System.out.println("2.Cancelled");
				System.out.println("3.Confirmed");
				int key = sc.nextInt();
				switch (key) {
				case 1:
					String opt1 = "Booked";

					byStatus(opt1);
					break;

				case 2:
					String opt2 = "Canceled";
					byStatus(opt2);
					break;
				case 3:
					String opt3 = "Confirmed";
					byStatus(opt3);
					break;
				default:
					break;
				}
			}
		}

		public void run() {
			try {
				Thread.sleep(1000);
				generateAll();
				Thread.sleep(5000);
				byStatus(tPath);

			} catch (Exception e) {

			}
		}

		private String createFileStamp() {
			try {
				String filename = new Date().getTime() + "Reservation _Report_" + ".txt";

				File file = new File("C://Users//Amaresh//Documents//Reports//" + filename);
				boolean value = file.createNewFile();
				System.out.println(file.getAbsolutePath());
				path = file.getAbsolutePath();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return path;
		}

		private String byStatus(String opt1) {

			createFileStamp();

			try {
				Scanner scd = new Scanner(System.in);

				ArrayList<String> nlist = new ArrayList<>();

				ArrayList<String> lf = new ArrayList<>(Files.readAllLines(Paths.get(dataFilePath)));
				for (int i = 0; i < lf.size(); i++) {
					String str = lf.get(i);
					String s4 = str.trim();
					if (s4.endsWith(opt1))
						nlist.add(s4 + "\n");
				}
				System.out.println(
						"----------------------------------------------------------------------------------------------------------");
				Collections.sort(nlist);
				System.out.println(
						"---------------------------------------------------------------------------------------------------------");
				System.out.println(nlist);

				FileWriter fw = new FileWriter(path, true);
				for (int i = 0; i < nlist.size(); i++) {
					fw.write(nlist.get(i) + "   ");
					if ((i + 1) % 10 == 0) {
						fw.write("\n");
					}
				}
				fw.write("\n");
				fw.close();

			} catch (Exception e) {
				System.out.println(e);
			}
			return null;

		}

		public void sortReserve() throws IOException {

			Scanner sc = new Scanner(System.in);
			System.out.println("____________________________________________________________________");

			System.out.println("              Choose Sort Reservation Property                   ");
			System.out.println("____________________________________________________________________");
			System.out.println("              1:->Reservation Id                     ");
			System.out.println("              2:->Reservation Description             ");
			System.out.println("              3:->Reservation Date                    ");
			System.out.println("              4:->Total Amount                     ");
			System.out.println("____________________________________________________________________");
			System.out.println("                 Enter Your Option                          ");
			System.out.println("____________________________________________________________________");
			int key = sc.nextInt();
			switch (key) {
			case 1:
				System.out.println("____________________________________________________________________");
				System.out.println("             ->Welcome To Sort By Reservation ID");
				System.out.println("____________________________________________________________________");
				System.out.println("                  Choose Sort Order                                   ");
				System.out.println("                   1: Ascending            ");
				System.out.println("                   2:Descending           ");
				System.out.println("____________________________________________________________________");
				sortReserveById();
				System.out.println("____________________________________________________________________");
				break;

			case 2:
				System.out.println("____________________________________________________________________");
				System.out.println("           -> Welcome To Sort By Reservation Description");
				System.out.println("____________________________________________________________________");
				System.out.println("               Choose Sort Order                                   ");
				System.out.println("                1: Ascending            ");
				System.out.println("                2:Descending           ");
				System.out.println("____________________________________________________________________");
				System.out.println("                Enter Your Option        ");
				System.out.println("____________________________________________________________________");

				sortReserveByDiscript();
				System.out.println("____________________________________________________________________");
				break;

			case 3:
				System.out.println("____________________________________________________________________");
				System.out.println("              -> Welcome To Sort By Reservation Date");
				System.out.println("____________________________________________________________________");
				System.out.println("                Choose Sort Order                                   ");
				System.out.println("                1: Ascending            ");
				System.out.println("                2:Descending           ");
				System.out.println("____________________________________________________________________");
				System.out.println("                Enter Your Option        ");
				System.out.println("____________________________________________________________________");
				sortReserveByDate();
				System.out.println("____________________________________________________________________");
				break;

			case 4:
				System.out.println("____________________________________________________________________");
				System.out.println("               -> Welcome To Sort By Reservation Total Amount");
				System.out.println("____________________________________________________________________");
				System.out
						.println("                        Choose Sort Order                                         ");
				System.out.println("                         1: Ascending            ");
				System.out.println("                         2:Descending           ");
				System.out.println("                         Enter Your Option        ");
				System.out.println("____________________________________________________________________");

				sortReserveByTotalAmount();
				break;

			default:
				break;
			}

		}

		private void sortReserveByTotalAmount() {

			Scanner scan = new Scanner(System.in);
			System.out.println("Enter Your Option");
			int option = scan.nextInt();
			try {
				scan = new Scanner(new File(dataFilePath));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			ArrayList<String> list2 = new ArrayList<String>();
			while (scan.hasNext()) {
				list2.add(scan.next());
			}

			ArrayList IDlist = new ArrayList();
			for (int i = 8; i < list2.size(); i = i + 10) {
				IDlist.add(list2.get(i));
			}

			switch (option) {
			case 1:
				System.out.println(
						"___________________________________________________________________________________________");
				System.out.println("                      ->Ascending Order                 ");
				Collections.sort(IDlist);
				System.out.println(
						"___________________________________________________________________________________________");

				break;
			case 2:
				System.out.println(
						"___________________________________________________________________________________________");
				System.out.println("Descending Order                   ");
				Collections.sort(IDlist, Collections.reverseOrder());
				System.out.println(
						"___________________________________________________________________________________________");
				break;
			default:
				break;
			}

			System.out.println(IDlist);
			try {
				Scanner scan1 = new Scanner(new File(dataFilePath));

				ArrayList<String> list1 = new ArrayList<String>();
				while (scan1.hasNext()) {
					list1.add(scan1.next());
				}
				System.out.println(list1.size());

				for (int j = 0; j < IDlist.size(); j++) {
					String str = (String) IDlist.get(j);
					int ind = list1.indexOf(str);
					list1.remove(ind);
					list1.add((ind - 8), str);
					for (int i = ind - 8; i < ind + 1; i++) {

						System.out.print(list1.get(i) + " ");
					}
					System.out.println();
				}
				scan.close();
				System.out.println();

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		private void sortReserveByDate() {
			Scanner scan = new Scanner(System.in);
			int option = scan.nextInt();
			try {
				scan = new Scanner(new File(dataFilePath));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			ArrayList<String> list2 = new ArrayList<String>();
			while (scan.hasNext()) {
				list2.add(scan.next());
			}
			// id starts from 0 index and nextline index starts from 0+10
			ArrayList IDlist = new ArrayList();
			for (int i = 3; i < list2.size(); i = i + 10) {

				// storing id into IDlist
				IDlist.add(list2.get(i));
			}

			switch (option) {
			case 1:
				System.out.println(
						"___________________________________________________________________________________________");
				System.out.println("                                    Ascending Order                           ");
				Collections.sort(IDlist);
				System.out.println(
						"___________________________________________________________________________________________");

				break;
			case 2:
				System.out.println(
						"___________________________________________________________________________________________");
				System.out.println(
						"                                     Descending Order                                                  ");
				Collections.sort(IDlist, Collections.reverseOrder());
				break;
			default:
				break;
			}

			System.out.println(IDlist);

			try {
				Scanner scan1 = new Scanner(new File(dataFilePath));

				ArrayList<String> list1 = new ArrayList<String>();
				while (scan1.hasNext()) {
					list1.add(scan1.next());
				}
				System.out.println(list1.size());

				for (int j = 0; j < IDlist.size(); j++) {
					String str = (String) IDlist.get(j);
					int ind = list1.indexOf(str);
					list1.remove(ind);
					list1.add((ind - 3), str);

					for (int i = ind - 3; i < ind + 7; i++) {

						System.out.print(list1.get(i) + " ");
					}
					System.out.println();
				}
				scan.close();
				System.out.println();

			} catch (Exception e) {

				e.printStackTrace();
			}

		}

		private void sortReserveByDiscript() throws IOException {
			Scanner scan = new Scanner(System.in);

			System.out.println("Enter Your Option");
			int option = scan.nextInt();
			try {
				scan = new Scanner(new File(dataFilePath));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			ArrayList<String> list2 = new ArrayList<String>();
			while (scan.hasNext()) {
				list2.add(scan.next());
			}

			ArrayList IDlist = new ArrayList();
			for (int i = 2; i < list2.size(); i = i + 10) {
				IDlist.add(list2.get(i));
			}
			switch (option) {
			case 1:
				System.out.println(
						"-------------------------------------Ascending Order---------------------------------------------------------");
				Collections.sort(IDlist);

				break;
			case 2:
				System.out.println(
						"-------------------------------------Descending Order---------------------------------------------------------");
				Collections.sort(IDlist, Collections.reverseOrder());
				break;
			default:
				break;
			}
			try {
				Scanner scan1 = new Scanner(new File(dataFilePath));

				ArrayList<String> list1 = new ArrayList<String>();
				while (scan1.hasNext()) {
					list1.add(scan1.next());
				}
				System.out.println(list1.size());

				for (int j = 0; j < IDlist.size(); j++) {
					String str = (String) IDlist.get(j);
					int ind = list1.indexOf(str);
					list1.remove(ind);
					list1.add((ind - 2), str);
					for (int i = ind - 2; i < ind + 8; i++) {

						System.out.print(list1.get(i) + " ");
					}
					System.out.println();
				}
				scan.close();
				System.out.println();

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		public void sortReserveById() {
			try {
				Scanner scd = new Scanner(System.in);
				System.out.println("Enter Your Option");
				int option = scd.nextInt();
				ArrayList<String> nlist = new ArrayList<>();

				ArrayList<String> lf = new ArrayList<>(Files.readAllLines(Paths.get(dataFilePath)));
				System.out.println(lf);
				System.out.println("----------------------------");
				int ind = 0;
				for (int i = 0; i < lf.size(); i++) {
					String str = lf.get(i);

					String s4 = str.trim();

					nlist.add(s4 + "\n");
				}
				switch (option) {
				case 1:

					System.out.println(
							"-------------------------------------Ascending Order---------------------------------------------------------");
					Collections.sort(nlist);

					break;
				case 2:
					System.out.println(
							"-------------------------------------Descending Order---------------------------------------------------------");
					Collections.sort(nlist, Collections.reverseOrder());
					break;

				default:
					break;
				}

				System.out.println(nlist);
				System.out.println(
						"-----------------------------------------------END---------------------------------------------------------");

			} catch (Exception e) {

			}
		}

	}

}

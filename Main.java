import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private static Scanner in; //Change IN to in Author Prabashi
	private static library lib; // change LIB to lib by Prabashi
	private static String menu; //change MENU to menu by Prabashi
	private static Calendar CAL;
	private static SimpleDateFormat SDF;
	
	
	private static String GetMenu() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\nLibrary Main Menu\n\n")
		  .append("  M  : add member\n")
		  .append("  LM : list members\n")
		  .append("\n")
		  .append("  B  : add book\n")
		  .append("  LB : list books\n")
		  .append("  FB : fix books\n")
		  .append("\n")
		  .append("  L  : take out a loan\n")
		  .append("  R  : return a loan\n")
		  .append("  LL : list loans\n")
		  .append("\n")
		  .append("  P  : pay fine\n")
		  .append("\n")
		  .append("  T  : increment date\n")
		  .append("  Q  : quit\n")
		  .append("\n")
		  .append("Choice : ");
		  
		return sb.toString();
	}


	public static void main(String[] args) {		
		try {			
			in = new Scanner(System.in); //Change IN to in Author Prabashi
			lib = library.INSTANCE(); // change LIB to lib by Prabashi
			CAL = Calendar.Instance();
			SDF = new SimpleDateFormat("dd/MM/yyyy");
	
			for (Member m : lib.members()) {  // change LIB to lib by Prabashi
				output(m);
			}
			output(" ");
			for (book b : lib.books()) {  // change LIB to lib by Prabashi
				output(b);
			}
						
			menu = GetMenu();
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + SDF.format(CAL.date()));
				String c = input(MENU);
				
				switch (c.toUpperCase()) {
				
				case "M": 
					addMember();
					break;
					
				case "LM": 
					members();
					break;
					
				case "B": 
					addBook();
					break;
					
				case "LB": 
					books();
					break;
					
				case "FB": 
					fixBooks();
					break;
					
				case "L": 
					borrowBook();
					break;
					
				case "R": 
					returnBook();
					break;
					
				case "LL": 
					currentLoans();
					break;
					
				case "P": 
					getFine();
					break;
					
				case "T": 
					incrementDate();
					break;
					
				case "Q": 
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				library.SAVE();
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

	
	private static void getFine() {//Fix the FINE to GetFine by MP
		new PayFineUI(new PayFineControl()).run();		
	}


	private static void currentLoans() {//Fix CURRENT_LOAN to CurrentLoans by MP
		output("");
		for (Loan loan : lib.currentLoans()) {  // change LIB to lib by Prabashi
			output(loan + "\n");
		}		
	}



	private static void books() { //change method name BOOKS to Books by MP
		output("");
		for (book book : lib.books()) {  // change LIB to lib by Prabashi
			output(book + "\n");
		}		
	}



	private static void members() {//Edit Method Name MEMBERS to members by MP
		output("");
		for (Member member : lib.members()) {  // change LIB to lib by Prabashi
			output(member + "\n");
		}		
	}



	private static void borrowBook() { //Edit Method Name by MP
		new BorrowBookUI(new BorrowBookControl()).run();		
	}


	private static void returnBook() {//Edit  Method Name Auther Prabashi
		new ReturnBookUI(new ReturnBookControl()).RuN();		
	}


	private static void fixBooks() {//Edit  Method Name Auther Prabashi
		new FixBookUI(new FixBookControl()).run();		
	}


	private static void incrementDate() {//Edit  Method Name Auther Prabashi
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			CAL.incrementDate(days);
			lib.checkCurrentLoans();
			output(SDF.format(CAL.date()));
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void addBook() {//Edit  Method Name Author Prabashi
		
		String a = input("Enter author: "); // change A to a by MP
		String t  = input("Enter title: "); // change T to t by MP
		String c = input("Enter call number: "); // change c to c  by MP
		book b = lib.addBook(a, t, c); // change variables by MP
		output("\n" + b + "\n");
		
	}

	
	private static void addMember() {//Edit  Method Name Auther Prabashi
		try {
			String ln = input("Enter last name: "); //change variable LN to ln Author Prabashi
			String fn  = input("Enter first name: "); ////change variable FN to fn Author Prabashi
			String em = input("Enter email: "); //change variable EM to em Author Prabashi
			int pn = Integer.valueOf(input("Enter phone number: ")).intValue(); //change variable PN to pn Author Prabashi
			Member m = lib.addMember(ln, fn, em, pn); //change variable Author Prabashi
			output("\n" + m + "\n");
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		return in.nextLine();
	}
	
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}

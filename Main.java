import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private static Scanner IN;
	private static library LIB;
	private static String MENU;
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
			IN = new Scanner(System.in);
			LIB = library.INSTANCE();
			CAL = Calendar.INSTANCE();
			SDF = new SimpleDateFormat("dd/MM/yyyy");
	
			for (member m : LIB.MEMBERS()) {
				output(m);
			}
			output(" ");
			for (book b : LIB.BOOKS()) {
				output(b);
			}
						
			MENU = GetMenu();
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + SDF.format(CAL.Date()));
				String c = input(MENU);
				
				switch (c.toUpperCase()) {
				
				case "M": 
					AddMember();
					break;
					
				case "LM": 
					Members();
					break;
					
				case "B": 
					AddBook();
					break;
					
				case "LB": 
					Books();
					break;
					
				case "FB": 
					FixBooks();
					break;
					
				case "L": 
					BorrowBook();
					break;
					
				case "R": 
					ReturnBook();
					break;
					
				case "LL": 
					CurrentLoans();
					break;
					
				case "P": 
					GetFine();
					break;
					
				case "T": 
					IncrementDate();
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

	
	private static void GetFine() {//Fix the FINE to GetFine by MP
		new PayFineUI(new PayFineControl()).RuN();		
	}


	private static void CurrentLoans() {//Fix CURRENT_LOAN to CurrentLoans by MP
		output("");
		for (loan loan : LIB.CurrentLoans()) {
			output(loan + "\n");
		}		
	}



	private static void Books() { //change method name BOOKS to Books by MP
		output("");
		for (book book : LIB.BOOKS()) {
			output(book + "\n");
		}		
	}



	private static void Members() {//Edit Method Name MEMBERS to Members by MP
		output("");
		for (member member : LIB.MEMBERS()) {
			output(member + "\n");
		}		
	}



	private static void BorrowBook() { //Edit Method Name by MP
		new BorrowBookUI(new BorrowBookControl()).run();		
	}


	private static void ReturnBook() {//Edit  Method Name Auther Prabashi
		new ReturnBookUI(new ReturnBookControl()).RuN();		
	}


	private static void FixBooks() {//Edit  Method Name Auther Prabashi
		new FixBookUI(new FixBookControl()).Run();		
	}


	private static void IncrementDate() {//Edit  Method Name Auther Prabashi
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			CAL.incrementDate(days);
			LIB.checkCurrentLoans();
			output(SDF.format(CAL.Date()));
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void AddBook() {//Edit  Method Name Auther Prabashi
		
		String A = input("Enter author: ");
		String T  = input("Enter title: ");
		String C = input("Enter call number: ");
		book B = LIB.Add_book(A, T, C);
		output("\n" + B + "\n");
		
	}

	
	private static void AddMember() {//Edit  Method Name Auther Prabashi
		try {
			String LN = input("Enter last name: ");
			String FN  = input("Enter first name: ");
			String EM = input("Enter email: ");
			int PN = Integer.valueOf(input("Enter phone number: ")).intValue();
			member M = LIB.Add_mem(LN, FN, EM, PN);
			output("\n" + M + "\n");
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		return IN.nextLine();
	}
	
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}

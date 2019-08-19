import java.util.Scanner;


public class BorrowBookUI {
	
	public static enum UiState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };

	private BorrowBookControl control;//change the variable name CONTROL to control
	private Scanner input;
	private UI_STATE state;//change the variable StaTe to state

	
	public BorrowBookUI(BorrowBookControl control) {
		this.control = control;
		input = new Scanner(System.in);
		state = UiState.INITIALISED;//Replace enum
		control.setUI(this);
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	
			
	public void setState(UiState STATE) {
		this.state = STATE;
	}

	
	public void run() {
		output("Borrow Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {			
			
			case CANCELLED:
				output("Borrowing Cancelled");
				return;

				
			case READY:
				String MEM_STR = input("Swipe member card (press <enter> to cancel): ");
				if (MEM_STR.length() == 0) {
					CONTROL.cancel();
					break;
				}
				try {
					int Member_ID = Integer.valueOf(MEM_STR).intValue();
					CONTROL.Swiped(Member_ID);
				}
				catch (NumberFormatException e) {
					output("Invalid Member Id");
				}
				break;

				
			case RESTRICTED:
				input("Press <any key> to cancel");
				CONTROL.cancel();
				break;
			
				
			case SCANNING:
				String Book_Str = input("Scan Book (<enter> completes): ");
				if (Book_Str.length() == 0) {
					CONTROL.Complete();
					break;
				}
				try {
					int BiD = Integer.valueOf(Book_Str).intValue();
					CONTROL.Scanned(BiD);
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id");
				} 
				break;
					
				
			case FINALISING:
				String Ans = input("Commit loans? (Y/N): ");
				if (Ans.toUpperCase().equals("N")) {
					CONTROL.cancel();
					
				} else {
					CONTROL.Commit_LOans();
					input("Press <any key> to complete ");
				}
				break;
				
				
			case COMPLETED:
				output("Borrowing Completed");
				return;
	
				
			default:
				output("Unhandled state");
				throw new RuntimeException("BorrowBookUI : unhandled state :" + StaTe);			
			}
		}		
	}


	public void Display(Object object) {
		output(object);		
	}


}

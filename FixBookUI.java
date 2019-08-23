import java.util.Scanner;


public class FixBookUI {

	public static enum UiState { INITIALISED, READY, FIXING, COMPLETED };//change enum UI_STATE to UiState

	private FixBookControl control;//change CoNtRoL to control Author Prabashi
	private Scanner input;
	private UiState state;//change UI_STATE to UiState by MP Author Prabashi

	
	public FixBookUI(FixBookControl control) { //change CoNtRoL to control Author Prabashi
		this.control = control; //change CoNtRoL to control Author Prabashi
		input = new Scanner(System.in);
		state = UiState.INITIALISED; //change UI_STATE to UiState by MP Author Prabashi
		control.setUi(this); //change CoNtRoL to control Author Prabashi
	}


	public void setState(UiState state) { //Fix Methods Name by Prabashi //change UI_STATE to UiState by MP Author Prabashi
		this.state = state;
	}

	
	public void run() {//Fix Methods Name by Prabashi
		output("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			
			case READY:
				String bookStr = input("Scan Book (<enter> completes): "); // change Book_STR to bookStr Author Prabashi
				if (bookStr.length() == 0) { // change Book_STR to bookStr Author Prabashi
					control.scanningComplete();//fixed method name //change CoNtRoL to control Author Prabashi
				}
				else {
					try {
						int Book_ID = Integer.valueOf(Book_STR).intValue();
						control.bookScanned(Book_ID); //change CoNtRoL to control Author Prabashi
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				String ans = input("Fix Book? (Y/N) : "); // change AnS to ans Author Prabashi
				boolean FiX = false;
				if (ans.toUpperCase().equals("Y")) { // change AnS to ans Author Prabashi
					FiX = true;
				}
				control.fixBook(FiX); //change CoNtRoL to control Author Prabashi
				break;
								
			case COMPLETED:
				output("Fixing process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state);			
			
			}		
		}
		
	}

	
	private String input(String prompt) {//Fix Methods Name by Prabashi
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	

	public void display(Object object) {//Fix Methods Name by Prabashi
		output(object);
	}
	
	
}

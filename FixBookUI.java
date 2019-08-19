import java.util.Scanner;


public class FixBookUI {

	public static enum UiState { INITIALISED, READY, FIXING, COMPLETED };//change enum UI_STATE to UiState

	private FixBookControl control;//change CoNtRoL to control
	private Scanner input;
	private UiState state;//change UI_STATE to UiState by MP

	
	public FixBookUI(FixBookControl control) {
		this.control = control;
		input = new Scanner(System.in);
		state = UiState.INITIALISED;
		control.setUi(this);
	}


	public void setState(UiState state) { //Fix Methods Name by Prabashi
		this.state = state;
	}

	
	public void run() {//Fix Methods Name by Prabashi
		output("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			
			case READY:
				String Book_STR = input("Scan Book (<enter> completes): ");
				if (Book_STR.length() == 0) {
					control.scanningComplete();//fixed method name
				}
				else {
					try {
						int Book_ID = Integer.valueOf(Book_STR).intValue();
						control.bookScanned(Book_ID);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				String AnS = input("Fix Book? (Y/N) : ");
				boolean FiX = false;
				if (AnS.toUpperCase().equals("Y")) {
					FiX = true;
				}
				control.fixBook(FiX);
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

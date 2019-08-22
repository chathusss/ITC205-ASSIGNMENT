import java.util.Scanner;

public class PayFineUI {

	// UI_STATE changed to UiState - Sathsarani
	public static enum UiState {
		INITIALISED, READY, PAYING, COMPLETED, CANCELLED
	};

	// CoNtRoL changed to payFineControl - Sathsarani
	private PayFineControl payFineControl;
	private Scanner input;
	// StAtE changed to state - Sathsarani
	private UiState state;

	public PayFineUI(PayFineControl control) {
		// CoNtRoL changed to payFineControl - Sathsarani
		this.payFineControl = control;
		input = new Scanner(System.in);
		// UI_STATE changed to UiState and StAtE changed to state - Sathsarani
		state = UiState.INITIALISED;

		// changed from Set_UI to setUi - Sathsarani
		control.setUi(this);
	}

	// UI_STATE changed to UiState and Set_State to setState- Sathsarani
	public void setState(UiState state) {
		// StAtE changed to state - Sathsarani
		this.state = state;
	}

	// changed RuN to run
	public void run() {
		getOutput("Pay Fine Use Case UI\n");

		while (true) {
			// StAtE changed to state - Sathsarani
			switch (state) {

			case READY:

				// Mem_Str to memStr and input changed to setIutput - Sathsarani
				String memStr = setIutput("Swipe member card (press <enter> to cancel): ");

				// Mem_Str to memStr - Sathsarani
				if (memStr.length() == 0) {
					// CoNtRoL changed to payFineControl and CaNcEl to isCancel- Sathsarani
					payFineControl.isCancel();
					break;
				}
				try {
					// Mem_Str to memStr and Member_ID to memberId - Sathsarani
					int memberId = Integer.valueOf(memStr).intValue();
					// CoNtRoL changed to payFineControl and Member_ID to memberId and Card_Swiped
					// to isCardSwiped - Sathsarani
					payFineControl.isCardSwiped(memberId);
				} catch (NumberFormatException e) {
					// Output changed to getOutput - Sathsarani
					getOutput("Invalid memberId");
				}
				break;

			case PAYING:
				// AmouNT to amount - Sathsarani
				double amount = 0;

				// Amt_Str to amtStr and input changed to setIutput - Sathsarani- Sathsarani
				String amtStr = setIutput("Enter amount (<Enter> cancels) : ");

				// Amt_Str to amtStr - Sathsarani
				if (amtStr.length() == 0) {
					// CoNtRoL changed to payFineControl and CaNcEl to isCancel- Sathsarani
					payFineControl.isCancel();
					break;
				}
				try {
					// Amt_Str to amtStr - Sathsarani
					amount = Double.valueOf(amtStr).doubleValue();
				} catch (NumberFormatException e) {
				}
				// AmouNT to amount - Sathsarani
				if (amount <= 0) {
					getOutput("Amount must be positive");
					break;
				}
				// CoNtRoL changed to payFineControl and AmouNT to amount and PaY_FiNe changed to is isPayFine - Sathsarani
				payFineControl.isPayFine(amount);
				break;

			case CANCELLED:
				// Output changed to getOutput - Sathsarani
				getOutput("Pay Fine process cancelled");
				return;

			case COMPLETED:
				// Output changed to getOutput - Sathsarani
				getOutput("Pay Fine process complete");
				return;

			default:
				// Output changed to getOutput - Sathsarani
				getOutput("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state);

			}
		}
	}

	
	private String setIutput(String prompt) {// input changed to setIutput - Sathsarani
		System.out.print(prompt);
		return input.nextLine();
	}

	// Output changed to getOutput - Sathsarani
	private void getOutput(Object object) {
		System.out.println(object);
	}

	// DiSplAY changed to getDisplay - Sathsarani
	public void getDisplay(Object object) {
		getOutput(object);
	}

}

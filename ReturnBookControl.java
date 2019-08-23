public class ReturnBookControl {

	// Ui changed to returnBookUi - Sathsarani
	private ReturnBookUI returnBookUi;

	// CONTROL_STATE changed to ControlState - Sathsarani
	private enum ControlState {
		INITIALISED, READY, INSPECTING
	};

	// CONTROL_STATE changed to ControlState and sTaTe changed to state- Sathsarani
	private ControlState state;
	// lIbRaRy changed to library - Sathsarani
	private library library;
	// CurrENT_loan changed to currrentLoan - Sathsarani
	private loan currrentLoan;

	public ReturnBookControl() {
		// lIbRaRy changed to library
		this.library = library.INSTANCE();
		// CONTROL_STATE changed to ControlState and sTaTe changed to state- Sathsarani
		state = ControlState.INITIALISED;
	}

	//Set_UI changed to setUi - Sathsarani
	public void setUi(ReturnBookUI ui) {
		// CONTROL_STATE changed to ControlState and sTaTe changed to state- Sathsarani
		if (!state.equals(ControlState.INITIALISED)) {
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		}
		// Ui changed to returnBookUi - Sathsarani
		this.returnBookUi = ui;
		//Set_State changed to setState - Sathsarani
		ui.setState(ReturnBookUI.UiState.READY);
		// CONTROL_STATE changed to ControlState and sTaTe changed to state- Sathsarani
		state = ControlState.READY;
	}

	// Book_scanned changed to isBookScanned and Book_ID to bookID- Sathsarani
	public void isBookScanned(int bookID) {
		// CONTROL_STATE changed to ControlState and sTaTe changed to state- Sathsarani
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		}
		// lIbRaRy changed to library and CUR_book changed to currentBook and Book_ID to bookID-Sathsarani
		book currentBook = library.Book(bookID);

		if (currentBook == null) {
			// Ui changed to returnBookUi - Sathsarani
			returnBookUi.display("Invalid Book Id");
			return;
		}
		if (!currentBook.On_loan()) {
			// Ui changed to returnBookUi - Sathsarani
			returnBookUi.display("Book has not been borrowed");
			return;
		}

		// lIbRaRy changed to library and  CurrENT_loan changed to currrentLoan and Book_ID to bookID- Sathsarani
		currrentLoan = library.LOAN_BY_BOOK_ID(bookID);

		// Over_Due_Fine changed to overDueFine - Sathsarani
		double overDueFine = 0.0;
		//CurrENT_loan changed to currrentLoan - Sathsarani
		if (currrentLoan.OVer_Due()) {
			// lIbRaRy changed to library and CurrENT_loan changed to currrentLoan and Over_Due_Fine changed to overDueFine - Sathsarani
			overDueFine = library.CalculateOverDueFine(currrentLoan);
		}
		// Ui changed to returnBookUi - Sathsarani
		returnBookUi.display("Inspecting");
		// Ui changed to returnBookUi - Sathsarani
		returnBookUi.display(currentBook.toString());
		// Ui changed to returnBookUi and CurrENT_loan changed to currrentLoan - Sathsarani
		returnBookUi.display(currrentLoan.toString());

		//CurrENT_loan changed to currrentLoan - Sathsarani
		if (currrentLoan.OVer_Due()) {
			// Ui changed to returnBookUi and Over_Due_Fine changed to overDueFine - Sathsarani
			returnBookUi.display(String.format("\nOverdue fine : $%.2f", overDueFine));
		}
		// Ui changed to returnBookUi - Sathsarani
		returnBookUi.Set_State(ReturnBookUI.UiState.INSPECTING);
		// CONTROL_STATE changed to ControlState and sTaTe changed to state- Sathsarani
		state = ControlState.INSPECTING;
	}

	public void Scanning_Complete() {
		// CONTROL_STATE changed to ControlState and sTaTe changed to state- Sathsarani
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		}
		// Ui changed to returnBookUi - Sathsarani
		returnBookUi.Set_State(ReturnBookUI.UiState.COMPLETED);
	}

	public void Discharge_loan(boolean isDamaged) {
		// CONTROL_STATE changed to ControlState and sTaTe changed to state- Sathsarani
		if (!state.equals(ControlState.INSPECTING)) {
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}
		// lIbRaRy changed to library and CurrENT_loan changed to currrentLoan - Sathsarani
		library.Discharge_loan(currrentLoan, isDamaged);
		//CurrENT_loan changed to currrentLoan - Sathsarani
		currrentLoan = null;
		// Ui changed to returnBookUi - Sathsarani
		returnBookUi.Set_State(ReturnBookUI.UiState.READY);
		// CONTROL_STATE changed to ControlState and sTaTe changed to state- Sathsarani
		state = ControlState.READY;
	}

}

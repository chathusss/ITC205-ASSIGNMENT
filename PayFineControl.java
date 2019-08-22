public class PayFineControl {

	// changed the class name Ui to the class name payFineUi - Sathsarani
	private PayFineUI payFineUi;

	
	private enum ControlState {// changed CONTROL_STATE to ControlState - Sathsarani
		INITIALISED, READY, PAYING, COMPLETED, CANCELLED
	};

	// Changed StAtE to state - Sathsarani
	private ControlState state;

	// changed LiBrArY to library - Sathsarani
	private library library;

	// changed MeMbEr to member1 - Sathsarani
	private member memeber1;

	public PayFineControl() {
		// changed LiBrArY to library - Sathsarani
		this.library = library.INSTANCE();
		// changed CONTROL_STATE to ControlState and Changed StAtE to state - Sathsarani
		state = ControlState.INITIALISED;
	}

	// Changed Set_UI to setUi
	public void setUi(PayFineUI ui) {
		// changed CONTROL_STATE to ControlState and Changed StAtE to state - Sathsarani
		if (!state.equals(ControlState.INITIALISED)) {
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}

		// changed Ui to payFineUi - Sathsarani
		this.payFineUi = ui;
		// changed Ui to payFineUi and Set_State to setState - Sathsarani
		ui.setState(PayFineUI.UiState.READY);
		// changed CONTROL_STATE to ControlState and Changed StAtE to state - Sathsarani
		state = ControlState.READY;
	}

	// Card_Swiped changed to isCardSwiped
	public void isCardSwiped(int memberId) {
		// changed CONTROL_STATE to ControlState and Changed StAtE to state - Sathsarani
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
		}

		// changed LiBrArY to library and changed MeMbEr to member1 and changed Member
		// to isMember assuming that it would be same as the team member who edit this
		// class- Sathsarani
		memeber1 = library.isMember(memberId);

		// changed MeMbEr to member1 - Sathsarani
		if (memeber1 == null) {
			// changed Ui to payFineUi and changed DiSplAY to isDisplay assuming that it
			// would be same as the team member who edit this class - Sathsarani
			payFineUi.getDisplay("Invalid Member Id");
			return;
		}

		// changed Ui to payFineUi and changed MeMbEr to member1 and changed DiSplAY to
		// isDisplay assuming that it would be same as the team member who edit this
		// class - Sathsarani
		payFineUi.isDisplay(memeber1.toString());

		// changed Ui to payFineUi and Set_State to setState - Sathsarani
		payFineUi.setState(PayFineUI.UiState.PAYING);
		// changed CONTROL_STATE to ControlState Changed StAtE to state - Sathsarani
		state = ControlState.PAYING;
	}

	// Changed from CaNcEl to isCancel
	public void isCancel() {
		// changed Ui to payFineUi and Set_State to setState - Sathsarani
		payFineUi.setState(PayFineUI.UiState.CANCELLED);
		// changed CONTROL_STATE to ControlState and Changed StAtE to state - Sathsarani
		state = ControlState.CANCELLED;
	}

	// changed from PaY_FiNe to isPayFine and changed AmOuNt to amount
	public double isPayFine(double amount) {
		// changed CONTROL_STATE to ControlState and Changed StAtE to state - Sathsarani
		if (!state.equals(ControlState.PAYING)) {
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
		}

		// changed MeMbEr to member1 and changed AmOuNt to amount. Also changed ChAnGe
		// to change. Assumed Pay_Fine is changed to isPayFine by other tream member-
		// Sathsarani
		double change = memeber1.isPayFine(amount);
		if (change > 0) {
			// changed Ui to payFineUi and changed DiSplAY to isDisplay assuming that it
			// would be same as the team member who edit this class - Sathsarani -
			// Sathsarani
			payFineUi.getDisplay(String.format("Change: $%.2f", change));
		}
		// changed Ui to payFineUi and changed MeMbEr to member1 and changed DiSplAY to
		// isDisplay assuming that it would be same as the team member who edit this
		// class - Sathsarani - Sathsarani
		payFineUi.isDisplay(memeber1.toString());
		// changed Ui to payFineUi and Set_State to setState - Sathsarani
		payFineUi.setState(PayFineUI.UiState.COMPLETED);
		// changed CONTROL_STATE to ControlState and Changed StAtE to state - Sathsarani
		state = ControlState.COMPLETED;
		return change;
	}

}

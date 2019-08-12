public class FixBookControl {
	
	private FixBookUI UI;
	private enum CONTROL_STATE { INITIALISED, READY, FIXING };
	private CONTROL_STATE state; //Change StAtE to state
	
	private library lib;// Change LIB to lib Author Prabashi
	private book cur_Book; // Change cur_Book  Author Prabashi


	public FixBookControl() {
		this.lib = lib.INSTANCE();  // change LIB to lib Author Prabashi
		state = CONTROL_STATE.INITIALISED;//Change StAtE to state
	}
	
	
	public void Set_Ui(FixBookUI ui) {
		if (!state.equals(CONTROL_STATE.INITIALISED)) { //Change StAtE to state Author Prabashi
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.UI = ui;
		ui.Set_State(FixBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;	//Change StAtE to state	Author Prabashi
	}


	public void Book_scanned(int bookId) {
		if (!state.equals(CONTROL_STATE.READY)) { //Change StAtE to state Author Prabashi
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		cur_Book = lib.Book(bookId);// Change LIB to lib Author Prabashi  // Change cur_Book  Author Prabashi
		
		if (Cur_Book == null) {
			UI.display("Invalid bookId");
			return;
		}
		if (!Cur_Book.IS_Damaged()) {
			UI.display("Book has not been damaged");
			return;
		}
		UI.display(cur_Book.toString());  // Change cur_Book  Author Prabashi
		UI.Set_State(FixBookUI.UI_STATE.FIXING);
		state = CONTROL_STATE.FIXING;	//Change StAtE to state	Author Prabashi	
	}


	public void FIX_Book(boolean MUST_fix) {
		if (!state.equals(CONTROL_STATE.FIXING)) { //Change StAtE to state	Author Prabashi
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (MUST_fix) {
			lib.Repair_BOOK(Cur_Book);
		}
		cur_Book = null; // Change cur_Book  Author Prabashi
		UI.Set_State(FixBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;		
	}

	
	public void SCannING_COMplete() {
		if (!state.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		UI.Set_State(FixBookUI.UI_STATE.COMPLETED);		
	}






}

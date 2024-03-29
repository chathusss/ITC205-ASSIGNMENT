public class FixBookControl {
	
	private FixBookUI ui;
	private enum ControlState { INITIALISED, READY, FIXING }; // Change CONTROL_STATE to ControlState Author Prabashi
	private ControlState state; //Change StAtE to state
	
	private library lib;// Change LIB to lib Author Prabashi
	private book curBook; // Change cur_Book to curBook Author Prabashi


	public FixBookControl() {
		this.lib = lib.INSTANCE();  // change LIB to lib Author Prabashi
		state = ControlState.INITIALISED;//Change StAtE to state and Change CONTROL_STATE to ControlState Author Prabashi
	}
	
	
	public void setUi(FixBookUI ui) {  // change Set_Ui to set_Ui Author Prabashi
		if (!state.equals(ControlState.INITIALISED)) { //Change StAtE to state Author Prabashi
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;
		ui.setState(FixBookUI.UiState.ready);
		state = ControlState.ready;	//Change StAtE to state	Author Prabashi and change CONTROL_STATE to ControlState 
	}


	public void bookScanned(int bookId) {
		if (!state.equals(ControlState.ready)) { //Change StAtE to state Author Prabashi change CONTROL_STATE to ControlState
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		curBook = lib.Book(bookId);// Change LIB to lib Author Prabashi  // Change cur_Book  Author Prabashi
		
		if (curBook == null) {
			ui.display("Invalid bookId");
			return;
		}
		if (!curBook.IS_Damaged()) {
			ui.display("Book has not been damaged");
			return;
		}
		ui.display(curBook.toString());  // Change cur_Book  Author Prabashi
		ui.setState(FixBookUI.UiState.FIXING);
		state = ControlState.FIXING;	//Change StAtE to state	Author Prabashi	
	}


	public void fixBook(boolean mustFix) { //Fixed the method name Author Prabashi 
		if (!state.equals(ControlState.FIXING)) { //Change StAtE to state	Author Prabashi
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (mustFix) {
			lib.Repair_BOOK(curBook);
		}
		curBook = null; // Change cur_Book  Author Prabashi
		ui.setState(FixBookUI.UiState.ready);
		state = ControlState.ready;		
	}

	
	public void scanningComplete() {
		if (!state.equals(ControlState.ready)) {
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		ui.setState(FixBookUI.UiState.COMPLETED);		
	}






}

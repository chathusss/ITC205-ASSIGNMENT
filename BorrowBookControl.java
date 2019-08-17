import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {
	
	private BorrowBookUI UI;
	
	private library library;//Edit Variable library to library by CSS
	private member m;//Edit Variable M to m by css
	private enum ControlState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };//Edit Enum ControlState to ControlState
	private ControlState State;
	
	private List<book> pending; // Change  to PENDING to pending MP
	private List<loan> complete;//change to COMPLETED to complete
	private book BOOK;
	
	
	public borrowBookControl()  { //Edit method name as camelBack
		this.library = library.INSTANCE();
		State = ControlState.INITIALISED;
	}
	

	public void setUI(BorrowBookUI ui) {
		if (!State.equals(ControlState.INITIALISED)) 
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			
		this.UI = ui;
		ui.Set_State(BorrowBookUI.UI_STATE.READY);
		State = ControlState.READY;//Replace with ControlState by CSS		
	}

		
	public void swiped(int memberId) {//Change variable name to MEMMER_ID to memberId by CSS
		if (!State.equals(ControlState.READY)) //Replace with ControlState by CSS
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		M = library.MEMBER(memberId);
		if (M == null) {
			UI.Display("Invalid memberId");
			return;
		}
		if (library.MEMBER_CAN_BORROW(M)) {
			PENDING = new ArrayList<>();
			UI.Set_State(BorrowBookUI.UI_STATE.SCANNING);
			State = ControlState.SCANNING; }
		else 
		{
			UI.Display("Member cannot borrow at this time");
			UI.Set_State(BorrowBookUI.UI_STATE.RESTRICTED); }}
	
	
	public void scanned(int bookId) { //change method name Scanned to scanned  by CSS
		BOOK = null;
		if (!State.equals(ControlState.SCANNING)) {
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
		BOOK = library.Book(bookId);
		if (BOOK == null) {
			UI.Display("Invalid bookId");
			return;
		}
		if (!BOOK.AVAILABLE()) {
			UI.Display("Book cannot be borrowed");
			return;
		}
		PENDING.add(BOOK);
		for (book B : PENDING) {
			UI.Display(B.toString());
		}
		if (library.Loans_Remaining_For_Member(M) - PENDING.size() == 0) {
			UI.Display("Loan limit reached");
			Complete();
		}
	}
	
	
	public void complete() {//Edit Method name Complete to complete by CSS
		if (PENDING.size() == 0) {
			cancel();
		}
		else {
			UI.Display("\nFinal Borrowing List");
			for (book B : PENDING) {
				UI.Display(B.toString());
			}
			COMPLETED = new ArrayList<loan>();
			UI.Set_State(BorrowBookUI.UI_STATE.FINALISING);
			State = ControlState.FINALISING;
		}
	}


	public void commitLoans() {
		if (!State.equals(ControlState.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (book B : PENDING) {
			loan LOAN = library.ISSUE_LAON(B, M);
			COMPLETED.add(LOAN);			
		}
		UI.Display("Completed Loan Slip");
		for (loan LOAN : COMPLETED) {
			UI.Display(LOAN.toString());
		}
		UI.Set_State(BorrowBookUI.UI_STATE.COMPLETED);
		State = ControlState.COMPLETED;
	}

	
	public void cancel() {
		UI.Set_State(BorrowBookUI.UI_STATE.CANCELLED);
		State = ControlState.CANCELLED;
	}
	
	
}

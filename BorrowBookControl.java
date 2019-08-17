import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {
	
	private BorrowBookUI ui;
	
	private Library library;//Edit Variable library to library by CSS
	private Member m;//Edit Variable M to m by css
	private enum ControlState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };//Edit Enum ControlState to ControlState
	private ControlState state;
	
	private List<book> pending; // Change  to PENDING to pending MP
	private List<loan> complete;//change to COMPLETED to complete
	private Book book;
	
	
	public getBorrowBookControl()  { //Edit method name as camelBack
		this.library = library.INSTANCE();
		State = ControlState.INITIALISED;
	}
	

	public void setUI(BorrowBookUI ui) {
		if (!State.equals(ControlState.INITIALISED)) 
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			
		this.ui = ui;
		ui.Set_State(BorrowBookUI.UI_STATE.READY);
		State = ControlState.READY;//Replace with ControlState by CSS		
	}

		
	public void swiped(int memberId) {//Change variable name to MEMMER_ID to memberId by CSS
		if (!State.equals(ControlState.READY)) //Replace with ControlState by CSS
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		m = library.MEMBER(memberId);
		if (m == null) {
			UI.Display("Invalid memberId");
			return;
		}
		if (library.MEMBER_CAN_BORROW(m)) {
			PENDING = new ArrayList<>();
			UI.Set_State(BorrowBookUI.UI_STATE.SCANNING);
			State = ControlState.SCANNING; }
		else 
		{
			ui.Display("Member cannot borrow at this time");
			ui.Set_State(BorrowBookUI.UI_STATE.RESTRICTED); }}
	
	
	public void scanned(int bookId) { //change method name Scanned to scanned  by CSS
		book = null;
		if (!State.equals(ControlState.SCANNING)) {
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
		book = library.Book(bookId);
		if (book == null) {
			ui.Display("Invalid bookId");
			return;
		}
		if (!book.AVAILABLE()) {
			ui.Display("Book cannot be borrowed");
			return;
		}
		pending.add(book);
		for (book B : pending) {
			ui.Display(B.toString());
		}
		if (library.Loans_Remaining_For_Member(M) - pending.size() == 0) {
			ui.Display("Loan limit reached");
			Complete();
		}
	}
	
	
	public void complete() {//Edit Method name Complete to complete by CSS
		if (pending.size() == 0) {
			cancel();
		}
		else {
			ui.Display("\nFinal Borrowing List");
			for (book B : pending) {
				UI.Display(B.toString());
			}
			COMPLETED = new ArrayList<loan>();
			ui.Set_State(BorrowBookUI.UI_STATE.FINALISING);
			State = ControlState.FINALISING;
		}
	}


	public void commitLoans() {
		if (!State.equals(ControlState.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (book B : pending) {
			loan LOAN = library.ISSUE_LAON(B, M);
			COMPLETED.add(LOAN);			
		}
		ui.Display("Completed Loan Slip");
		for (loan LOAN : COMPLETED) {
			UI.Display(LOAN.toString());
		}
		ui.Set_State(BorrowBookUI.UI_STATE.COMPLETED);
		State = ControlState.COMPLETED;
	}

	
	public void cancel() {
		ui.Set_State(BorrowBookUI.UI_STATE.CANCELLED);
		State = ControlState.CANCELLED;
	}
	
	
}

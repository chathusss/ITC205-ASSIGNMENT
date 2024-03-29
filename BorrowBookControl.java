import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {
	
	private BorrowBookUI ui;
	
	private library library;//Edit Variable library to library by CSS
	private Member m;//Edit Variable M to m by css
	private enum ControlState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };//Edit Enum ControlState to ControlState
	private ControlState state;
	
	private List<book> pending; // Change  to PENDING to pending MP
	private List<Loan> completed;//change to COMPLETED to completed
	private book book;
	
	
	public BorrowBookControl()  { //Edit method name as camelBack
		this.library = library.INSTANCE();
		state = ControlState.INITIALISED;
	}
	

	public void setUi(BorrowBookUI ui) {
		if (!state.equals(ControlState.INITIALISED)) 
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			
		this.ui = ui;
		ui.setState(BorrowBookUI.UiState.READY);
		state = ControlState.READY;//Replace with ControlState by CSS		
	}

		
	public void swiped(int memberId) {//Change variable name to MEMMER_ID to memberId by CSS
		if (!state.equals(ControlState.READY)) //Replace with ControlState by CSS
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		m = library.MEMBER(memberId);
		if (m == null) {
			ui.Display("Invalid memberId");
			return;
		}
		if (library.MEMBER_CAN_BORROW(m)) {
			pending= new ArrayList<>();
			ui.setState(BorrowBookUI.UiState.SCANNING);
			state = ControlState.SCANNING; }
		else 
		{
			ui.Display("Member cannot borrow at this time");
			ui.setState(BorrowBookUI.UiState.RESTRICTED); }}
	
	
	public void scanned(int bookId) { //change method name Scanned to scanned  by CSS
		book = null;
		if (!state.equals(ControlState.SCANNING)) {
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
		pending.add(book);//Replace with edited Variable by CSS
		for (book B : pending) {
			ui.Display(B.toString());
		}
		if (library.Loans_Remaining_For_Member(m) - pending.size() == 0) {
			ui.Display("Loan limit reached");
			Complete();
		}
	}
	
	
	public void Complete() {//Edit Method name Complete to complete by CSS
		if (pending.size() == 0) {
			cancel();
		}
		else {
			ui.Display("\nFinal Borrowing List");
			for (book B : pending) {
				ui.Display(B.toString());
			}
			completed = new ArrayList<Loan>();
			ui.setState(BorrowBookUI.UiState.FINALISING);
			state = ControlState.FINALISING;
		}
	}


	public void commitLoans() {
		if (!state.equals(ControlState.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (book B : pending) {
			Loan LOAN = library.ISSUE_LAON(B, m);
			completed.add(LOAN);			
		}
		ui.Display("Completed Loan Slip");
		for (Loan LOAN : completed) {
			ui.Display(LOAN.toString());
		}
		ui.setState(BorrowBookUI.UiState.COMPLETED);
		state = ControlState.COMPLETED;
	}

	
	public void cancel() {
		ui.setState(BorrowBookUI.UiState.CANCELLED);
		state = ControlState.CANCELLED;// Change State to state
	}
	
	
}

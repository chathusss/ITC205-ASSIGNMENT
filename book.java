// author Pramodi
import java.io.Serializable;


@SuppressWarnings("serial")
public class book implements Serializable {
	
	private String title; // variable name change TITLE to title
	private String author; // variable name change AUTHOR to author
	private String callNo; // variable name cahnge CALLNO to callNo
	private int id; // variable name change ID to id
	
	private enum State { AVAILABLE, ON_LOAN, DAMAGED, RESERVED }; // name change STATE to State
	private State state; // STATE changes to State  and variable name changes to STATE to state
	
	
	public book(String author, String title, String callNo, int id) {
		this.author = author; // AUTHOR canges to author
		this.title = title; // TITLE to title
		this.callNo = callNo;  // CALLNO to callNo
		this.id = id; // ID to id
		this.state = State.AVAILABLE; // State to state and class name STATE to State
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(ID).append("\n")
		  .append("  Title:  ").append(title).append("\n") // TITLE to title
		  .append("  Author: ").append(author).append("\n") // AUTHOR to author
		  .append("  CallNo: ").append(callNo).append("\n") // CALLNO to callNo
		  .append("  State:  ").append(state); // State to state
		
		return sb.toString();
	}

	public Integer id() { // method name changes ID to id()
		return id; // varible name changes ID to id
	}

	public String title() { // TITLE method name changes to title
		return title; //  variable TITLE to title
	}


	
	public boolean available() {  // method AVAILABLE to available()
		return state == State.available; // State to state and State , AVAILABLE to available
	}

	
	public boolean onLoan() { // On_loan to onLoan
		return state == State.onStateLoan; // State to state , STATE to State ,ON_LStateOAN to onStateLoan
	}

	
	public boolean isDamaged() { // IS_Damaged to isDamaged()
		return state == State.damaged; // State to state ,STATE to state , DAMAGED to damaged
	}

	
	public void Borrow() {
		if (State.equals(State.available)) { // STATE.AVAILABLE to State.available
			State = State.onLoan; // STATE.ON_LOAN to State.onLoan 
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state)); // State to state
		}
		
	}


	public void Return(boolean damaged) { // DAMAGED to damaged
		if (State.equals(State.onloan)) { // STATE.ON_LOAN to State.onLoan
			if (damaged) { // DAMAGED to damaged
				State = State.damaged; // STATE.DAMAGED to State.damaged
			}
			else {
				State = State.available; // STATE.AVAILABLE to State.available
			}
		}
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", state)); // State to state
		}		
	}

	
	public void Repair() {
		if (State.equals(State.damaged)) { // STATE.DAMAGED to State.damaged
			State = State.available; // STATE.AVAILABLE to State.available
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state)); // State to state
		}
	}


}

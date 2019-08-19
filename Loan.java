import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable {//Edit Class Name
	
	public static enum LoanState { CURRENT, OVER_DUE, DISCHARGED };
	
	private int id;
	private book book;
	private member member;
	private Date date;
	private LoanState state;

	
	public Loan(int loanId, book book, member member, Date dueDate) {
		this.id = loanId;
		this.book = book;
		this.member = member;
		this.date= dueDate;
		this.state = LoanState.CURRENT;
	}

	
	public void checkOverDue() {
		if (state == LoanState.CURRENT &&
			Calendar.Instance().Date().after(date)) {//Edit Calling Method Name
			this.state = LoanState.OVER_DUE;			
		}
	}

	
	public boolean overDue() {
		return state == LoanState.OVER_DUE;
	}

	
	public Integer id() {
		return id;
	}


	public Date getDueDate() {
		return date;
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(id).append("\n")
		  .append("  Borrower ").append(member.GeT_ID()).append(" : ")
		  .append(member.Get_LastName()).append(", ").append(member.Get_FirstName()).append("\n")
		  .append("  Book ").append(book.ID()).append(" : " )
		  .append(book.TITLE()).append("\n")
		  .append("  DueDate: ").append(sdf.format(date)).append("\n")
		  .append("  State: ").append(state);		
		return sb.toString();
	}


	public member Member() {
		return member;
	}


	public book Book() {
		return book;
	}


	public void Discharge() {
		state = LoanState.DISCHARGED;		
	}

}

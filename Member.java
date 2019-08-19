import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Member implements Serializable { //Change Class Name member to Member

	private String lastName; /* Edit All Variable Names*/
	private String firstName;
	private String email;
	private int phoneNo;
	private int id;
	private double fines;
	
	private Map<Integer, Loan> LNS;

	
	public Member(String lastName, String firstName, String email, int phoneNo, int id) {
		this.lastName = lastName;//Replace Edited Variables Name by CSS
		this.firstName = firstName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.id = id;
		
		this.LNS = new HashMap<>();
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Member:  ").append(id).append("\n")//Replace Edited Variables Name by CSS
		  .append("  Name:  ").append(lastName).append(", ").append(firstName).append("\n")//Replace Edited Variables Name by CSS
		  .append("  Email: ").append(email).append("\n")//Replace Edited Variables Name by CSS
		  .append("  Phone: ").append(phoneNo)//Replace Edited Variables Name by CSS
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", fines))//Replace Edited Variables Name by CSS
		  .append("\n");
		
		for (Loan loan : LNS.values()) {
			sb.append(loan).append("\n");
		}		  
		return sb.toString();
	}

	
	public int getId() {
		return id;
	}

	
	public List<Loan> getLoans() {
		return new ArrayList<Loan>(LNS.values());
	}

	
	public int numberOfCurrentLoans() {//Edit Method Name by CSS
		return LNS.size();
	}

	
	public double finesOwed() {//Edit Method Name by CSS
		return fines;//Replace Edited Variables Name by CSS
	}

	
	public void takeOutLoan(Loan loan) {//Edit Method Name by CSS
		if (!LNS.containsKey(loan.id())) {
			LNS.put(loan.id(), loan);
		}
		else {
			throw new RuntimeException("Duplicate loan added to member");
		}		
	}

	
	public String getLastName() {//Edit Method Name by CSS
		return lastName;//Replace Edited Variables Name by CSS
	}

	
	public String getFirstName() {//Edit Method Name by CSS
		return firstName;//Replace Edited Variables Name by CSS
	}


	public void addFine(double fine) {//Edit Method Name by CSS
		fines += fine;//Replace Edited Variables Name by CSS
	}
	
	public double payFine(double amount) { //Edit Variables Name AmOuNt to amount
		if (amount < 0) {
			throw new RuntimeException("Member.payFine: amount must be positive");
		}
		double change = 0;
		if (amount > fines) {//Replace Edited Variables Name by CSS
			change = amount - fines;
			fines = 0;
		}
		else {
			fines -= amount;
		}
		return change;
	}


	public void dischargeLoan(Loan loan) { //Edit Method Name by CSS
		if (LNS.containsKey(loan.id())) {
			LNS.remove(loan.id());
		}
		else {
			throw new RuntimeException("No such loan held by member");
		}		
	}

}

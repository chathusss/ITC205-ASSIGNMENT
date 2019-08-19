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

	
	public int get_ID() {
		return id;
	}

	
	public List<loan> GeT_LoAnS() {
		return new ArrayList<loan>(LNS.values());
	}

	
	public int Number_Of_Current_Loans() {
		return LNS.size();
	}

	
	public double Fines_OwEd() {
		return fines;//Replace Edited Variables Name by CSS
	}

	
	public void Take_Out_Loan(loan loan) {
		if (!LNS.containsKey(loan.ID())) {
			LNS.put(loan.ID(), loan);
		}
		else {
			throw new RuntimeException("Duplicate loan added to member");
		}		
	}

	
	public String Get_LastName() {
		return lastName;//Replace Edited Variables Name by CSS
	}

	
	public String Get_FirstName() {
		return firstName;//Replace Edited Variables Name by CSS
	}


	public void Add_Fine(double fine) {
		fines += fine;//Replace Edited Variables Name by CSS
	}
	
	public double Pay_Fine(double amount) { //Edit Variables Name AmOuNt to amount
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


	public void dIsChArGeLoAn(loan loan) {
		if (LNS.containsKey(loan.ID())) {
			LNS.remove(loan.ID());
		}
		else {
			throw new RuntimeException("No such loan held by member");
		}		
	}

}

// author pramodi
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class library implements Serializable {
	
	private static final String LIBRARYFILE = "library.obj"; // libraryFile to LIBRARYFILE
	private static final int LOANLIMIT = 2; // loanLimit to LOANLIMIT
	private static final int LOANPERIOD = 2; // loanPeriod to LOANPERIOD
	private static final double FINEPERDAY = 1.0; // finePerDay to FINEPERDAY
	private static final double MAXFINESOWED = 1.0; // maxFinesOwed to MAXFINESOWED
	private static final double DAMAGEFEE = 2.0; // damageFee to DAMAGEFEE
	
	private static library self; // SeLf to self 
	private int bookId; // BOOK_ID to bookId
	private int memberId; // MEMBER_ID to memberId
	private int loanId; // LOAN_ID to loanId
	private Date loanDate; // LOAN_DATE to loanDate
	
	private Map<Integer, book> catalog; // CATALOG to catalog
	private Map<Integer, member> member; // MEMBERS to member
	private Map<Integer, loan> loan; // LOANS to loan
	private Map<Integer, loan> currentLoan; // CURRENT_LOANS to currentLoan
	private Map<Integer, book> damagedBooks; // DAMAGED_BOOKS to damagedBooks
	

	private library() {
		catalog = new HashMap<>(); // CATALOG to catalog
		member = new HashMap<>(); // MEMBERS to member
		loan = new HashMap<>(); // LOANS to loan
		currentLoan = new HashMap<>(); // CURRENT_LOANS to currentLoan
		damagedBook = new HashMap<>(); // DAMAGED_BOOKS to  damagedBook
		bookId = 1; // BOOK_ID to bookId
		memberId = 1; // MEMBER_ID to memberId		
		loanId = 1; // LOAN_ID to loanId		
	}

	
	public static synchronized library instance() {		// INSTANCE to instance
		if (self == null) { // SeLf to self
			Path path= Paths.get(libraryFile);	 // PATH  to path		
			if (Files.exists(path)) {	 // PATH to path
				try (ObjectInputStream LiF = new ObjectInputStream(new FileInputStream(libraryFile));) {
			    
					self = (library) lif.readObject(); // SeLf to self , LiF to lif
					Calendar.instance().setDate(self.loanDate); // INSTANCE to instance , Set_dATE to setDate , SeLf to self, LOAN_DATE to loanDate
					lif.close(); // LiF to lif
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else self= new library(); // SeLf  to self
		}
		return self; // SeLf to self
	}

	
	public static synchronized void save() { // SAVE to save
		if (self != null) { // SeLf to self
			self.loanDate= Calendar.instance().date(); // SeLf to self , LOAN_DATE  to loanDate , INSTANCE to instance , Date to date
			try (ObjectOutputStream lof = new ObjectOutputStream(new FileOutputStream(libraryFile));) { LoF to lof
				lof.writeObject(self); // LoF to lof , SeLf to self
				lof.flush(); // LoF to lof
				lof.close(); // LoF to lof	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	public int  bookId() { // BookID to bookId
		return bookId; // BOOK_ID to bookId
	}
	
	
	public int memberId() { // MemberID to memberId
		return memberId; // MEMBER_ID to memberId
	}
	
	
	private int nextBid() { // NextBID to nextBid
		return bookId++; // BOOK_ID to bookId
	}

	
	private int nextMid() { // NextMID to nextMid
		return memberId++; // MEMBER_ID to memberId
	}

	
	private int nextId() { // NextLID to nextId
		return loanId++; // LOAN_ID to loanId
	}

	
	public List<member> member() {	 // MEMBERS to member	
		return new ArrayList<member>(MEMBERS.values()); 
	}


	public List<book> books() {	 // BOOKS to books	
		return new ArrayList<book>(catalog.values()); // CATALOG to catalog
	}


	public List<loan> CurrentLoans() {
		return new ArrayList<loan>(currentLoans.values()); // CURRENT_LOANS to currentLoans
	}


	public member Add_mem(String lastName, String firstName, String email, int phoneNo) {		
		member member = new member(lastName, firstName, email, phoneNo, nextMid()); // NextMID to nextMid
		MEMBERS.put(member.getId(), member); // GeT_ID to getId		
		return member;
	}

	
	public book addBook(String a, String t, String c) {	// Add_book to addBook	
		book b = new book(a, t, c, nextBid()); // NextBID to nextBid
		catalog.put(b.id(), b);		// CATALOG to catalog , ID to id
		return b;
	}

	
	public member member(int memberId) { //  MEMBER to member
		if (member.containsKey(memberId))  // MEMBERS to member
			return member.get(memberId); // MEMBERS to member
		return null;
	}

	
	public book  book(int bookId) { // Book to book
		if (catalog.containsKey(bookId))  // CATALOG to catalog
			return catalog.get(bookId);	// CATALOG to catalog	
		return null;
	}

	
	public int loanLimit() { // LOAN_LIMIT to loanLimit
		return loanLimit;
	}

	
	public boolean memberCanBorrow(member member) {		// MEMBER_CAN_BORROW to memberCanBorrow
		if (member.numberOfCurrentLoans() == loanLimit ) // Number_Of_Current_Loans to numberOfCurrentLoans
			return false;
				
		if (member.finesOwed() >= maxFfinesOwedinesOwed) // Fines_OwEd to finesOwed
			return false;
				
		for (loan loan : member.getLoans())  // GeT_LoAnS to getLoans
			if (loan.overDue()) // OVer_Due to overDue
				return false;
			
		return true;
	}

	
	public int loansRemainingForMember(member member) {	// Loans_Remaining_For_Member to loans remainingForMember	
		return loanLimit - member.numberOfCurrentLoans(); // Number_Of_Current_Loans to numberOfCurrentLoans
	}

	
	public loan issueLoan(book book, member member) { // ISSUE_LAON to issueLoan
		Date dueDate = Calendar.instance().Due_Date(loanPeriod); // INSTANCE to instance
		loan loan = new loan(nextId(), book, member, dueDate); // NextLID to nextId
		member.takeOutLoan(loan); // Take_Out_Loan to takeOutLoan
		book.Borrow();
		loans.put(loan.id(), loan);// LOANS to loans , ID to id
		currentLoans.put(book.id(), loan); // CURRENT_LOANS to currentLoans , ID to id
		return loan;
	}
	
	
	public loan loanByBookId(int bookId) { // LOAN_BY_BOOK_ID to loanByBookId
		if (currentLoans.containsKey(bookId)) { // CURRENT_LOANS to currentLoans
			return currentLoans.get(bookId);  // CURRENT_LOANS to currentLoans
		}
		return null;
	}

	
	public double calculateOverDueFines(loan loan) { // CalculateOverDueFine to calculateOverDueFines
		if (loan.overDue()) { // OVer_Due to overDue
			long daysOverDue = Calendar.instance().getDayDifference(loan.getDueDate()); // INSTANCE to instance , Get_Days_Difference to getDayDifference, Get_Due_Date to getDueDate
			double fine = daysOverDue * finePerDay;
			return fine;
		}
		return 0.0;		
	}


	public void dischageLoan(loan currentLoan, boolean isDamaged) { // Discharge_loan to dischargeLoan
		member member = currentLoan.member(); // Member to member()
		book book  = currentLoan.book(); // Book to book()
		
		double overDueFine = calculateOverDueFine(currentLoan); // CalculateOverDueFine to calculateOverDuefine
		member.addFine(overDueFine);	// Add_Fine to addFine
		
		member.dischargeLoan(currentLoan); // dIsChArGeLoAn to dischargeLoan
		book.return(isDamaged); // Return to return
		if (isDamaged) {
			member.addFine(damageFee); // Add_Fine to addFine
			damagedBooks.put(book.id(), book); // DAMAGED_BOOKS to damageBooks , ID to id
		}
		currentLoan.discharge(); // DiScHaRgE to discharge
		currentLoan.remove(book.id()); // CURRENT_LOANS to currentLoan , ID to id
	}


	public void checkCurrentLoans() {
		for (loan loan : currentLoans.values()) { // CURRENT_LOANS to currentLoan
			loan.checkOverDue();
		}		
	}


	public void repairBook(book currentBook) {// Repair_BOOK to repairBook
		if (damagedBooks.containsKey(currentBook.id())) { // DAMAGED_BOOKS to damagedBooks , ID to id
			currentBook.Repair();
			damagedBook.remove(currentBook.id()); // DAMAGED_BOOKS to damagedBook  ID to id
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
		
	}
	
	
}

import java.util.*;

public class Basket {

	private static final double BOOK_PRICE = 8.0;
	private List<Book> _basket = new ArrayList<Book>();
	
	
	
	private static Map<Integer, Double> _discountRateMap = new HashMap<Integer, Double>();
	static {
		_discountRateMap.put(0, 1.0);
		_discountRateMap.put(1, 1.0);
		_discountRateMap.put(2, 0.95);
		_discountRateMap.put(3, 0.9);
		_discountRateMap.put(4, 0.8);
		_discountRateMap.put(5, 0.75);
	}

	int fiveDifferentBookCombination = 0;
	
	public double checkout() {
		double due = 0;
		
		while (getDiffBooksCount() > 1) {
			due += getDue(getDiffBooksCount());
			removeDifferentBook();
		}
		
		return due + getNoDiscountDue(_basket.size());
	}
	
	private void increaseFiveDifferentBookCombinationCountAsNeeded() {
		if (getDiffBooksCount() == 5)
			fiveDifferentBookCombination++;
	}

	private double getDue(int currentCount) {
		if (isEdgeCase(currentCount)) {
			fiveDifferentBookCombination--;
			return getEdgeCaseDue();
		} else {
			increaseFiveDifferentBookCombinationCountAsNeeded();
			return getDiscountDue(currentCount);
		}
	}

	private double getEdgeCaseDue() {
		return getDiscountDue(4) * 2 - getDiscountDue(5);
	}

	private boolean isEdgeCase(int currentCount) {
		return fiveDifferentBookCombination > 0 && currentCount == 3;
	}

	private double getNoDiscountDue(int sameBookCount) {
		return sameBookCount * BOOK_PRICE;
	}

	private double getDiscountDue(int differentBookCount) {
		return differentBookCount * BOOK_PRICE * _discountRateMap.get(differentBookCount);
	}
	
	private void removeDifferentBook()
	{
		for(Book book: getDifferentBook())
			_basket.remove(book);
	}

	private int getDiffBooksCount() {
		return getDifferentBook().size();
	}

	private HashSet<Book> getDifferentBook() {
		return new HashSet<Book>(_basket);
	}	
	
	public void add(Book book) {
		_basket.add(book);
	}

}

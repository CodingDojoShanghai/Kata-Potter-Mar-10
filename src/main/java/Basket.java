import java.util.*;

public class Basket {

	private static final double BOOK_PRICE = 8.0;
	private final List<Book> _basket = new ArrayList<Book>();

	private static final Map<Integer, Double> _discountRateMap = new HashMap<Integer, Double>();
	static {
		_discountRateMap.put(0, 1.0);
		_discountRateMap.put(1, 1.0);
		_discountRateMap.put(2, 0.95);
		_discountRateMap.put(3, 0.9);
		_discountRateMap.put(4, 0.8);
		_discountRateMap.put(5, 0.75);
	}

	public double checkout() {
		return checkoutAssist(0, 0, new ArrayList<Book>(_basket));
	}
	
	private double checkoutAssist(double due, int fiveDifferentBookCombination, List<Book> basket) {
		final int currentDiffBooksCount = getDiffBooksCount(basket);
		if (currentDiffBooksCount <= 1) {
			return due + getNoDiscountDue(basket.size());
		} else {
			if (isEdgeCase(fiveDifferentBookCombination, currentDiffBooksCount)) {
				return checkoutAssist(due + getEdgeCaseDue(), 
						fiveDifferentBookCombination - 1, 
						removeDifferentBook(basket));
			} else {
				return checkoutAssist(due + getDiscountDue(currentDiffBooksCount),
						currentDiffBooksCount == 5 ? fiveDifferentBookCombination + 1 : fiveDifferentBookCombination,
						removeDifferentBook(basket));
			}
		}
	}

	private double getEdgeCaseDue() {
		return getDiscountDue(4) * 2 - getDiscountDue(5);
	}

	private boolean isEdgeCase(int fiveDifferentBookCombination, int currentCount) {
		return fiveDifferentBookCombination > 0 && currentCount == 3;
	}

	private double getNoDiscountDue(int sameBookCount) {
		return sameBookCount * BOOK_PRICE;
	}

	private double getDiscountDue(int differentBookCount) {
		return differentBookCount * BOOK_PRICE
				* _discountRateMap.get(differentBookCount);
	}

	private List<Book> removeDifferentBook(List<Book> basket) {
		for (Book book : getDiffBooks(basket))
			basket.remove(book);
		return basket;
	}

	private int getDiffBooksCount(List<Book> basket) {
		return getDiffBooks(basket).size();
	}

	private HashSet<Book> getDiffBooks(List<Book> basket) {
		return new HashSet<Book>(basket);
	}

	public void add(Book book) {
		_basket.add(book);
	}

}

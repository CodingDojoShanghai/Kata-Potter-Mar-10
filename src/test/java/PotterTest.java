import junit.framework.Assert;

import org.junit.Test;


public class PotterTest {

	private static final double FIVE_DIFFERENT_BOOK_DISCOUNT = 0.75;
	private static final double FOUR_DIFFERENT_BOOK_DISCOUNT = 0.8;
	private static final double THREE_DIFFERENT_BOOK_DISCOUNT = 0.9;
	private static final double TWO_DIFFERENT_BOOK_DISCOUNT = 0.95;
	private static final double ONE_BOOK_PRICE = 8.0d;

	private Basket basket = new Basket();
	
	@Test
	public void test_empty_basket() {
		toCheckout(0d);
	}
	
	@Test public void test_basket_with_1_book() {
		basket.add(Book.FIRST);
		toCheckout(ONE_BOOK_PRICE);
	}

	@Test public void test_basket_with_2_same_books() {
		basket.add(Book.FIRST);
		basket.add(Book.FIRST);
		toCheckout(ONE_BOOK_PRICE*2);
	}
	
	@Test public void test_basket_with_2_different_books() {
		basket.add(Book.FIRST);
		basket.add(Book.SECOND);
		toCheckout(ONE_BOOK_PRICE*2*TWO_DIFFERENT_BOOK_DISCOUNT);
	}
	
	@Test public void test_basket_with_3_different_books() {
		 basket.add(Book.FIRST);
		 basket.add(Book.SECOND);
		 basket.add(Book.THIRD);
		 toCheckout(ONE_BOOK_PRICE*3*THREE_DIFFERENT_BOOK_DISCOUNT);
	}
	
	@Test public void test_basket_with_1_2_2_books() {
		 basket.add(Book.FIRST);
		 basket.add(Book.SECOND);
		 basket.add(Book.SECOND);
		 toCheckout(ONE_BOOK_PRICE*2*TWO_DIFFERENT_BOOK_DISCOUNT + ONE_BOOK_PRICE);
	}	
	
	
	@Test public void test_basket_with_3_same_books() {
		 basket.add(Book.FIRST);
		 basket.add(Book.FIRST);
		 basket.add(Book.FIRST);
		 toCheckout(ONE_BOOK_PRICE*3);
	}
	
	@Test public void test_basket_with_4_different_books() {
		 basket.add(Book.FIRST);
		 basket.add(Book.SECOND);
		 basket.add(Book.THIRD);
		 basket.add(Book.FORTH);
		 
		 toCheckout(ONE_BOOK_PRICE*4* FOUR_DIFFERENT_BOOK_DISCOUNT);
	}
	
	@Test public void test_basket_with_2_2_different_books() {
		basket.add(Book.FIRST);
		 basket.add(Book.FIRST);
		 basket.add(Book.SECOND);
		 basket.add(Book.SECOND);
		 
		 toCheckout(ONE_BOOK_PRICE*2* TWO_DIFFERENT_BOOK_DISCOUNT*2);
		
	}
	
	@Test public void test_basket_with_1_1_2_2_3_3_4_5_different_books() {
		basket.add(Book.FIRST);
		 basket.add(Book.FIRST);
		 basket.add(Book.SECOND);
		 basket.add(Book.SECOND);
		 basket.add(Book.THIRD);
		 basket.add(Book.THIRD);
		 basket.add(Book.FORTH);
		 basket.add(Book.FIFTH);
		 toCheckout(ONE_BOOK_PRICE*4 * FOUR_DIFFERENT_BOOK_DISCOUNT*2 );
		
	}
	
	@Test public void test_basket_with_1_1_1_2_2_2_3_3_3_4_5_different_books() {
		basket.add(Book.FIRST);
		 basket.add(Book.FIRST);
		 basket.add(Book.FIRST);
		 basket.add(Book.SECOND);
		 basket.add(Book.SECOND);
		 basket.add(Book.SECOND);
		 basket.add(Book.THIRD);
		 basket.add(Book.THIRD);
		 basket.add(Book.THIRD);
		 basket.add(Book.FORTH);
		 basket.add(Book.FIFTH);
		 toCheckout(ONE_BOOK_PRICE*4 * FOUR_DIFFERENT_BOOK_DISCOUNT*2 +  ONE_BOOK_PRICE*3 * THREE_DIFFERENT_BOOK_DISCOUNT);
		
	}
	
	@Test public void test_basket_with_5_different_books() {
		 basket.add(Book.FIRST);
		 basket.add(Book.SECOND);
		 basket.add(Book.THIRD);
		 basket.add(Book.FORTH);
		 basket.add(Book.FIFTH);
		 
		 toCheckout(ONE_BOOK_PRICE*5* FIVE_DIFFERENT_BOOK_DISCOUNT);
	}	
	
    public void toCheckout(double expectedDueAmount) {
		 double dueAmount = basket.checkout();
		 Assert.assertEquals(expectedDueAmount, dueAmount, 0.001); 
    }
}

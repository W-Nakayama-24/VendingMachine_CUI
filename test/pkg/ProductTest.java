package pkg;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ProductTest {

    @Test
    public void testGetNum() {
        Product prdct = new Product(1, null, 0);
        int result = prdct.getNum();
        assertThat(result, is(1));
    }

    @Test
    public void testGetName() {
        Product prdct = new Product(0, "きのみジュース", 0);
        String result = prdct.getName();
        assertThat(result, is("きのみジュース"));
    }

    @Test
    public void testGetPrice() {
        Product prdct = new Product(0, null, 200);
        int result = prdct.getPrice();
        assertThat(result, is(200));
    }

}

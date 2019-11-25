package pkg;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author W-Nakayama
 *
 */
public class ProductTest {

    @Test
    public void testGetNum() {
        Product product = new Product(1, null, 0);
        int result = product.getNum();
        assertThat(result, is(1));
    }

    @Test
    public void testGetName() {
        Product product = new Product(0, "きのみジュース", 0);
        String result = product.getName();
        assertThat(result, is("きのみジュース"));
    }

    @Test
    public void testGetPrice() {
        Product product = new Product(0, null, 200);
        int result = product.getPrice();
        assertThat(result, is(200));
    }

}

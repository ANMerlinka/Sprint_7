import clients.OrderClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.Test;

public class ListOrderTest {
    OrderClient orderClient = new OrderClient();

    @Test
    @DisplayName("List order")
    @Description("Check that the list of orders is returned to the response body")
    public void orderList() {
        orderClient.list()
                .body("orders", Matchers.notNullValue());
    }
}

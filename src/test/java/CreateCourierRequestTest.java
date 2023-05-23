import clients.CourierClient;
import dataprovider.CourierProvider;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import pojo.CreateCourierRequest;

public class CreateCourierRequestTest {
    private CourierClient courierClient = new CourierClient();

    private Integer id;

    @Test
    @DisplayName("Creation new courier") // имя теста
    @Description("Checking response body (ok: true;) and status code 201")
    public void courierCreated() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequest();

        courierClient.create(createCourierRequest)
                .statusCode(201)
                .body("ok", Matchers.equalTo(true));
    }


    @Test
    @DisplayName("Checking for the creation of two identical couriers")
    @Description("Checking response body and status code 409")
    public void checkCreateDuplicateCourier() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequest();

        courierClient.create(createCourierRequest)
                .statusCode(201)
                .body("ok", Matchers.equalTo(true));

        courierClient.create(createCourierRequest)
                .statusCode(409)
                .assertThat().body("message", Matchers.equalTo("Этот логин уже используется. Попробуйте другой."));

    }

    @Test
    @DisplayName("Negative test for creating a courier without a login")
    @Description("Checking response body and status code 400")
    public void checkCourierResponseWithoutLogin() {
        CreateCourierRequest createCourierRequestWithoutLogin = CourierProvider.getRandomCreateCourierRequestWithoutLogin();

        courierClient.create(createCourierRequestWithoutLogin)
                .statusCode(400)
                .assertThat().body("message", Matchers.equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Negative test for creating a courier without a password")
    @Description("Checking response body and status code 400")
    public void checkCourierResponseWithoutPassword() {
        CreateCourierRequest createCourierRequestWithoutPassword = CourierProvider.getRandomCreateCourierRequestWithoutPassword();

        courierClient.create(createCourierRequestWithoutPassword)
                .statusCode(400)
                .assertThat().body("message", Matchers.equalTo("Недостаточно данных для создания учетной записи"));
    }

    @After
    public void tearDown() {
        if (id != null) {
            courierClient.delete(id)
                        .statusCode(200);
        }
    }

}

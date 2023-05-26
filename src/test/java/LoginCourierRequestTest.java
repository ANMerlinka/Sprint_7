import clients.CourierClient;
import dataprovider.CourierProvider;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import pojo.CreateCourierRequest;
import pojo.LoginCourierRequest;

public class LoginCourierRequestTest {
    private CourierClient courierClient = new CourierClient();

    private Integer id;

    @Test
    @DisplayName("Authorization of the courier with the correct login & password") // имя теста
    @Description("Checking response body (id) and status code 200")
    public void courierLogin() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequest();

        courierClient.create(createCourierRequest)
                .statusCode(201)
                .body("ok", Matchers.equalTo(true));

        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(createCourierRequest);

        id = courierClient.login(loginCourierRequest)
                .statusCode(200)
                .extract().jsonPath().get("id");
    }

    @Test
    @DisplayName("Authorization of a courier with an incorrect login (request without login)") // имя теста
    @Description("Checking response body and status code 400")
    public void courierLoginWithoutLogin() {
        CreateCourierRequest createCourierRequestWithoutLogin = CourierProvider.getRandomCreateCourierRequestWithoutLogin();

        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(createCourierRequestWithoutLogin);

        courierClient.login(loginCourierRequest)
                .statusCode(400)
                .assertThat().body("message", Matchers.equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Authorization of a courier with an incorrect login (request without password)") // имя теста
    @Description("Checking response body and status code 400")
    public void courierLoginWithoutPassword() {
        CreateCourierRequest createCourierRequestWithoutPassword = CourierProvider.getRandomCreateCourierRequestWithoutPassword();

        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(createCourierRequestWithoutPassword);

        courierClient.login(loginCourierRequest)
                .statusCode(400)
                .assertThat().body("message", Matchers.equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Authorization of a courier with a non-existent username") // имя теста
    @Description("Checking response body and status code 200")
    public void courierLoginNoExistLogin() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequest();

        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(createCourierRequest);

        courierClient.login(loginCourierRequest)
                .statusCode(404)
                .assertThat().body("message", Matchers.equalTo("Учетная запись не найдена"));
    }

    @After
    public void tearDown() {
        if (id != null) {
            courierClient.delete(id)
                    .statusCode(200);
        }
    }

}

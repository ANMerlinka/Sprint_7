package dataprovider;

import org.apache.commons.lang3.RandomStringUtils;
import pojo.CreateCourierRequest;

public class CourierProvider {
    public static CreateCourierRequest getRandomCreateCourierRequest() {
        CreateCourierRequest createCourierRequest = new CreateCourierRequest();

        createCourierRequest.setLogin(RandomStringUtils.randomAlphabetic(8));
        createCourierRequest.setPassword(RandomStringUtils.randomAlphabetic(8));
        createCourierRequest.setFirstName(RandomStringUtils.randomAlphabetic(8));

        return createCourierRequest;
    }

    public static CreateCourierRequest getRandomCreateCourierRequestWithoutLogin() {
        CreateCourierRequest createCourierRequest = new CreateCourierRequest();

        createCourierRequest.setLogin("");
        createCourierRequest.setPassword(RandomStringUtils.randomAlphabetic(8));
        createCourierRequest.setFirstName(RandomStringUtils.randomAlphabetic(8));

        return createCourierRequest;
    }

    public static CreateCourierRequest getRandomCreateCourierRequestWithoutPassword() {
        CreateCourierRequest createCourierRequest = new CreateCourierRequest();

        createCourierRequest.setLogin(RandomStringUtils.randomAlphabetic(8));
        createCourierRequest.setPassword("");
        createCourierRequest.setFirstName(RandomStringUtils.randomAlphabetic(8));

        return createCourierRequest;
    }
}

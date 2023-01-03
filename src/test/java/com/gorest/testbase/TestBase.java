package com.gorest.testbase;

import com.gorest.constants.Path;
import com.gorest.utils.PropertyReader;
import com.gorest.utils.TestsUtils;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase extends TestsUtils {
    public static PropertyReader propertyReader;

    @BeforeClass
    public static void inIt() {
        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("baseURI");
        RestAssured.basePath = Path.RESOURCE;
    }
}

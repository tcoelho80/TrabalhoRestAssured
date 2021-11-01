package impacta.restassured;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.get;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class countryApiTest {
    
    @BeforeAll
    public static void setBaseURI() {
        RestAssured.baseURI = "https://api.country.is";
    }

    //Verifica se o retorno é do tipo JSON
    @Test
    public void testGetContentTypeJson(){

        var bodyContentType = get("/9.9.9.9").then().statusCode(200).extract().contentType();
        assertEquals("application/json; charset=utf-8", bodyContentType);

    }

    //teste 200 sem parametros
    @Test
    public void testGetPais200OKSemParametro() {
        get().then().statusCode(200);
    }

    //teste 200 com parametros
    @Test
    public void testGetPais200OKComParametro() {
        get("/9.9.9.9").then().statusCode(200);
    }

    //teste código de erro 422
    @Test
    public void testGetPaisErro422() {
        get("/abc").then().statusCode(422);
    }

    //retorna AssertEquals do Json Retornado código 200
    @Test
    public void testGetPais200OkResponse() {
        var body = get("/9.9.9.9").then().statusCode(200).extract().body();

        assertEquals("US", body.path("country"));
        assertEquals("9.9.9.9", body.path("ip"));
    }

}

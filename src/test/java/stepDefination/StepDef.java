package stepDefination;

//import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDef {

    RequestSpecification req;
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;

    @Given("^Add page payload$")
    public void add_page_payload()  {
        //throw new PendingException();
        RestAssured.baseURI="https://rahulshettyacademy.com";

        AddPlace p =new AddPlace();
        p.setAccuracy(50);
        p.setAddress("29, side layout, cohen 09");
        p.setLanguage("French-IN");
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName("Frontline house");
        List<String> myList =new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        p.setTypes(myList);
        Location l =new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);

         req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();
         resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        res =given().spec(req)
                .body(p);
    }

    @When("^user calls \"([^\"]*)\" api using post request$")
    public void user_calls_something_api_using_post_request(String strArg1)  {
        //throw new PendingException();
         response =res.when().post("/maps/api/place/add/json").
                then().spec(resspec).extract().response();
    }

    @Then("^Api is call is success and status code should be 201$")
    public void api_is_call_is_success_and_status_code_should_be_201()  {
        assertEquals(response.statusCode(),200);

    }

    @And("^\"([^\"]*)\" is \"([^\"]*)\"  Given Add place payload$")
    public void something_is_something_given_add_place_payload(String keyvalue, String actualvalue) {
        String msg_resp = response.asString();
        JsonPath js = new JsonPath(msg_resp);
        assertEquals(js.get(keyvalue).toString(), actualvalue);
        //js.getString("message");


    }

 /*   @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
    public void something_in_response_body_is_something(String key, String val)  {
        String msg_rep = response.asString();
        JsonPath js = new JsonPath(msg_rep);
        assertEquals(js.get("key").toString(), val);

        //throw new PendingException();
    }
*/
}

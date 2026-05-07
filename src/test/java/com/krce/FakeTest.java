package com.krce;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;


public class FakeTest {
    private int id;

    @BeforeClass
    public void beforeClass() {
        RestAssured.baseURI = "https://api.escuelajs.co/api/v1";
    }
    @Test(priority=1)
    public void testGetProduct() {
        given().
                when().
                get("/products").
                then().
                statusCode(200).
                body("size()", Matchers.greaterThan(0));

    }
    @Test(priority=2)
    public void testGetPrice() {
        given().
                queryParam("price",100).
                when().
                get("/products").
                then().
                statusCode(200).
                body("[0].price",Matchers.equalTo(100));

    }
    @Test(priority=3)
    public void testGetCategory() {
        given().
                queryParam("price",100).
                when().
                get("/products").
                then().
                statusCode(200).
                body("$",Matchers.instanceOf(List.class));
    }
    @Test(priority=4)
    public void testGetTitle() {
        given().
                when().
                get("?title=Generic");
    }
    @Test(priority = 5)
    public void testGetId() {
        given().
                pathParam("id",1).
                when().
                get("/categories/{id}").
                then().
                statusCode(200).
                body("id", Matchers.equalTo(1));
    }
}
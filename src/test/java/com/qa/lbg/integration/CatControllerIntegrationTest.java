package com.qa.lbg.integration;
// MUST start or end with the word 'Test'
// avoid the temptation to call them just 'Test'

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.lbg.domain.Cat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest // loads the whole application so we can test it
@AutoConfigureMockMvc // tells Spring to set up the MockMVC object
@Sql(scripts = {"classpath:cat-schema.sql", "classpath:cat-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class CatControllerIntegrationTest {

    @Autowired // replaces the constructor because JUnit doesn't actually instantiate test classes
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;


    @Test
    void testCreate() throws Exception {
        // Requirements: URl, POST, body, header?
        // Test: body, status code
        Cat toCreate = new Cat(null, "Tiddles", "Black", 6, "Shy", 9);
        System.out.println("CAT: " + toCreate);
        String reqBody = this.mapper.writeValueAsString(toCreate);
        System.out.println("BODY: " + reqBody);

        RequestBuilder req = MockMvcRequestBuilders
                .post("/cat/new")
                .content(reqBody)
                .contentType(MediaType.APPLICATION_JSON);

        Cat created = new Cat(2, "Tiddles", "Black", 6, "Shy", 9);
        System.out.println("CREATED: " + created);
        String resBody = this.mapper.writeValueAsString(created);
        System.out.println("BODY: " + resBody);

        ResultMatcher checkStatus = MockMvcResultMatchers.status().isCreated();
        ResultMatcher checkBody = MockMvcResultMatchers.content().json(resBody);


        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
//        If you need to do a custom assertion you can extract the response body and use that in a standard assertEquals
//        String result = this.mvc.perform(req).andReturn().getResponse().getContentAsString();
//        Cat resultCat = this.mapper.readValue(result, Cat.class);

    }

    @Test
    void testGetByID() throws Exception {
        // Finds a cat by its id
        // GET, id, URL
        // Expected: body + code

        RequestBuilder mockRequest = MockMvcRequestBuilders.get("/cat/1");


        ResultMatcher checkStatus = MockMvcResultMatchers.status().isOk();

        Cat testCat = new Cat(1, "Garfield", "Orange", 56, "Lazy", 9);
        String testCatAsJSON = this.mapper.writeValueAsString(testCat);
        ResultMatcher checkBody = MockMvcResultMatchers.content().json(testCatAsJSON);

        this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
    }


    @Test
    void testGetAll() throws Exception {
        // build the request -  URL, method
        RequestBuilder mockRequest = MockMvcRequestBuilders.get("/cat/all");


        // check the res body and status code
        ResultMatcher checkStatus = MockMvcResultMatchers.status().isOk();
        Cat testCat = new Cat(1, "Garfield", "Orange", 56, "Lazy", 9);
        List<Cat> testCats = new ArrayList<>();
        testCats.add(testCat);
        System.out.println("testCats: " + testCats);
        String testCatsAsJSON = this.mapper.writeValueAsString(testCats);
        System.out.println("testCatsJSON: " + testCatsAsJSON);
        ResultMatcher checkBody = MockMvcResultMatchers.content().json(testCatsAsJSON);

        this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
    }


    @Test
    void testUpdate() throws Exception {
        Cat updatedCat = new Cat(1, "Jess", "black", 6, "timid", 8);
        String updatedCatAsJSON = this.mapper.writeValueAsString(updatedCat);
//        RequestBuilder mockRequest = MockMvcRequestBuilders
//                .patch("http://localhost:4494/cat/1?name=Jess&age=6&nature=moody&lives=8&colour=black");
//        RequestBuilder mockRequest = MockMvcRequestBuilders
//                .patch("http://localhost:4494/cat/" + updatedCat.getId() + "?name=" + updatedCat.getName() +
//                        "&age=" + updatedCat.getAge() + "&nature=" + updatedCat.getNature()
//                        + "&lives=" + updatedCat.getLives() + "&colour=" + updatedCat.getColour());
        RequestBuilder mockRequest = MockMvcRequestBuilders
                .patch("http://localhost:4494/cat/" + updatedCat.getId())
                .param("name", updatedCat.getName())
                .param("age", String.valueOf(updatedCat.getAge()))
                .param("nature", updatedCat.getNature())
                .param("lives", String.valueOf(updatedCat.getLives()))
                .param("colour", updatedCat.getColour());
        ResultMatcher checkStatus = MockMvcResultMatchers.status().isOk();

        ResultMatcher checkBody = MockMvcResultMatchers.content().json(updatedCatAsJSON);


        this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
    }


    @Test
    void testDelete() throws Exception {
        Cat deletedCat = new Cat(1, "Garfield", "Orange", 56, "Lazy", 9);
        String deletedCatASJSON = this.mapper.writeValueAsString(deletedCat);

        RequestBuilder mockRequest = MockMvcRequestBuilders.delete("/cat/" + deletedCat.getId());


        ResultMatcher checkStatus = MockMvcResultMatchers.status().isOk();
        ResultMatcher checkBody  = MockMvcResultMatchers.content().json(deletedCatASJSON);

        this.mvc.perform(mockRequest).andExpect(checkBody).andExpect(checkStatus);
    }

    @Test
    void testDeleteNotFound() throws Exception {
        final int id = 99;
        RequestBuilder mockRequest = MockMvcRequestBuilders.delete("/cat/" + id);

        String msg = "No cat found with id: " + id;

        ResultMatcher checkStatus = MockMvcResultMatchers.status().isNotFound();
        ResultMatcher checkBody  = MockMvcResultMatchers.content().string(msg);

        this.mvc.perform(mockRequest).andExpect(checkBody).andExpect(checkStatus);
    }
}

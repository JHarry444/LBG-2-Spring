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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest // loads the whole application so we can test it
@AutoConfigureMockMvc // tells Spring to set up the MockMVC object
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

        Cat created = new Cat(1, "Tiddles", "Black", 6, "Shy", 9);
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


}

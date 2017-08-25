package com.ekstraklasa.football;

import com.ekstraklasa.football.app.FootballApplication;
import com.ekstraklasa.football.controller.FlatController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

//@RunWith(SpringRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { FootballApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@WebAppConfiguration
public class FlatControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetProducts() throws Exception {
        System.out.println("th12: działą");
        this.mockMvc.perform(get("/products"))
                .andExpect(model().attributeExists("products"));
        assertEquals("Foo", "gfhfgh");
    }

    @Test
    public void testGetProductById() throws Exception {
        //Arrange
        //Product product = new Product("P1234","iPhone 5s", new BigDecimal(500));

        //Act & Assert
        //this.mockMvc.perform(get("/products/product")
        //        .param("id", "P1234"))
        //        .andExpect(model().attributeExists("product"))
        //        .andExpect(model().attribute("product", product));
    }

}

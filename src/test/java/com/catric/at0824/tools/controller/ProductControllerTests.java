package com.catric.at0824.tools.controller;
import com.catric.at0824.tools.model.Tool;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ProductControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();
    private Tool tool1;
    private Tool tool2;
    private Tool tool3;
    private Tool tool4;
    private Tool tool5;
    private Tool tool6;

    @BeforeEach
    public void init() {
        tool1 = new Tool("JAKR", 5, 101, "09-03-2015");
        tool2 = new Tool("LADW", 3, 10, "07-02-2020");
        tool3 = new Tool("CHNS", 5, 25, "07-02-2015");
        tool4 = new Tool("JAKD", 6, 0, "09-03-2015");
        tool5 = new Tool("JAKR", 9, 0, "07-02-2015");
        tool6 = new Tool("JAKR", 4, 50, "07-02-2020");
    }

    @Test
    public void ProductController_CreateRentalAgreement1() throws Exception {
        try {
            mockMvc.perform(post("/createRentalAgreement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tool1)));
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Request processing failed: java.lang.IllegalArgumentException: Error: discountPercentage should be between 0-100%");
        }
    }

    @Test
    public void ProductController_CreateRentalAgreement2() throws Exception {
        try {
            ResultActions response = mockMvc.perform(post("/createRentalAgreement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tool2)));

            response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.toolType").value("Ladder"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.toolBrand").value("Werner"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dueDate").value("07-05-2020"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numChargeableDays").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dailyRentalCharge").value(1.99))
                .andExpect(MockMvcResultMatchers.jsonPath("$.preDiscountCharge").value(3.98))
                .andExpect(MockMvcResultMatchers.jsonPath("$.discountAmount").value(0.40))
                .andExpect(MockMvcResultMatchers.jsonPath("$.finalChargeAmount").value(3.58));

        } catch (Exception e) {
            fail("Should not throw an exception");
        }
    }

    @Test
    public void ProductController_CreateRentalAgreement3() throws Exception {
        try {
            ResultActions response = mockMvc.perform(post("/createRentalAgreement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tool3)));

            response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.toolType").value("Chainsaw"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.toolBrand").value("Stihl"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dueDate").value("07-07-2015"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numChargeableDays").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dailyRentalCharge").value(1.49))
                .andExpect(MockMvcResultMatchers.jsonPath("$.preDiscountCharge").value(4.47))
                .andExpect(MockMvcResultMatchers.jsonPath("$.discountAmount").value(1.12))
                .andExpect(MockMvcResultMatchers.jsonPath("$.finalChargeAmount").value(3.35));

        } catch (Exception e) {
            fail("Should not throw an exception");
        }
    }

    @Test
    public void ProductController_CreateRentalAgreement4() throws Exception {
        try {
            ResultActions response = mockMvc.perform(post("/createRentalAgreement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tool4)));

            response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.toolType").value("Jackhammer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.toolBrand").value("DeWalt"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dueDate").value("09-09-2015"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numChargeableDays").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dailyRentalCharge").value(2.99))
                .andExpect(MockMvcResultMatchers.jsonPath("$.preDiscountCharge").value(8.97))
                .andExpect(MockMvcResultMatchers.jsonPath("$.discountAmount").value(0.00))
                .andExpect(MockMvcResultMatchers.jsonPath("$.finalChargeAmount").value(8.97));

        } catch (Exception e) {
            fail("Should not throw an exception");
        }
    }

    @Test
    public void ProductController_CreateRentalAgreement5() throws Exception {
        try {
            ResultActions response = mockMvc.perform(post("/createRentalAgreement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tool5)));

            response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.toolType").value("Jackhammer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.toolBrand").value("Ridgid"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dueDate").value("07-11-2015"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numChargeableDays").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dailyRentalCharge").value(2.99))
                .andExpect(MockMvcResultMatchers.jsonPath("$.preDiscountCharge").value(14.95))
                .andExpect(MockMvcResultMatchers.jsonPath("$.discountAmount").value(0.00))
                .andExpect(MockMvcResultMatchers.jsonPath("$.finalChargeAmount").value(14.95));

        } catch (Exception e) {
            fail("Should not throw an exception");
        }
    }

    @Test
    public void ProductController_CreateRentalAgreement6() throws Exception {
        try {
            ResultActions response = mockMvc.perform(post("/createRentalAgreement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tool6)));

            response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.toolType").value("Jackhammer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.toolBrand").value("Ridgid"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dueDate").value("07-06-2020"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numChargeableDays").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dailyRentalCharge").value(2.99))
                .andExpect(MockMvcResultMatchers.jsonPath("$.preDiscountCharge").value(2.99))
                .andExpect(MockMvcResultMatchers.jsonPath("$.discountAmount").value(1.50))
                .andExpect(MockMvcResultMatchers.jsonPath("$.finalChargeAmount").value(1.49));

        } catch (Exception e) {
            fail("Should not throw an exception");
        }
    }

    @Test
    public void ProductController_GetAllRentalAgreements() throws Exception {
        try {
            mockMvc.perform(post("/createRentalAgreement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tool6)));


            ResultActions response = mockMvc.perform(get("/getAllRentalAgreements"));

            response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].toolType").value("Jackhammer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].toolBrand").value("Ridgid"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].dueDate").value("07-06-2020"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].numChargeableDays").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].dailyRentalCharge").value(2.99))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].preDiscountCharge").value(2.99))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].discountAmount").value(1.50))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].finalChargeAmount").value(1.49));

        } catch (Exception e) {
            fail("Should not throw an exception");
        }
    }

    @Test
    public void ProductController_GetRentalAgreementById() throws Exception {
        try {
            MvcResult initialResponse = mockMvc.perform(post("/createRentalAgreement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tool6)))
                .andReturn();

            String id = JsonPath.read(initialResponse.getResponse().getContentAsString(), "$.uuid");
            ResultActions response = mockMvc.perform(get("/getRentalAgreement/" + id)
                .contentType(MediaType.APPLICATION_JSON));

            response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.toolType").value("Jackhammer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.toolBrand").value("Ridgid"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dueDate").value("07-06-2020"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numChargeableDays").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dailyRentalCharge").value(2.99))
                .andExpect(MockMvcResultMatchers.jsonPath("$.preDiscountCharge").value(2.99))
                .andExpect(MockMvcResultMatchers.jsonPath("$.discountAmount").value(1.50))
                .andExpect(MockMvcResultMatchers.jsonPath("$.finalChargeAmount").value(1.49))
                .andExpect(MockMvcResultMatchers.jsonPath("$.uuid").value(id));

            ResultActions failedResponse = mockMvc.perform(get("/getRentalAgreement/not-a-uuid")
                .contentType(MediaType.APPLICATION_JSON));

            failedResponse.andExpect(MockMvcResultMatchers.status().isNotFound());
        } catch (Exception e) {
            fail("Should not throw an exception");
        }
    }

    @Test
    public void ProductController_UpdateRentalAgreement() throws Exception {
        try {
            MvcResult initialResponse = mockMvc.perform(post("/createRentalAgreement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tool6)))
                .andReturn();

            String id = JsonPath.read(initialResponse.getResponse().getContentAsString(), "$.uuid");
            ResultActions response = mockMvc.perform(patch("/updateRentalAgreement/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tool5)));

            response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.toolType").value("Jackhammer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.toolBrand").value("Ridgid"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dueDate").value("07-11-2015"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numChargeableDays").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dailyRentalCharge").value(2.99))
                .andExpect(MockMvcResultMatchers.jsonPath("$.preDiscountCharge").value(14.95))
                .andExpect(MockMvcResultMatchers.jsonPath("$.discountAmount").value(0.00))
                .andExpect(MockMvcResultMatchers.jsonPath("$.finalChargeAmount").value(14.95))
                .andExpect(MockMvcResultMatchers.jsonPath("$.uuid").value(id));

            ResultActions failedResponse = mockMvc.perform(patch("/updateRentalAgreement/not-a-uuid")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tool6)));

            failedResponse.andExpect(MockMvcResultMatchers.status().isNotFound());
        } catch (Exception e) {
            fail("Should not throw an exception");
        }
    }

    @Test
    public void ProductController_DeleteRentalAgreement() throws Exception {
        try {
            MvcResult initialResponse = mockMvc.perform(post("/createRentalAgreement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tool5)))
                .andReturn();

            String id = JsonPath.read(initialResponse.getResponse().getContentAsString(), "$.uuid");
            ResultActions response = mockMvc.perform(delete("/deleteRentalAgreement/" + id)
                .contentType(MediaType.APPLICATION_JSON));

            response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.toolType").value("Jackhammer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.toolBrand").value("Ridgid"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dueDate").value("07-11-2015"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numChargeableDays").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dailyRentalCharge").value(2.99))
                .andExpect(MockMvcResultMatchers.jsonPath("$.preDiscountCharge").value(14.95))
                .andExpect(MockMvcResultMatchers.jsonPath("$.discountAmount").value(0.00))
                .andExpect(MockMvcResultMatchers.jsonPath("$.finalChargeAmount").value(14.95))
                .andExpect(MockMvcResultMatchers.jsonPath("$.uuid").value(id));

            ResultActions failedResponse = mockMvc.perform(delete("/deleteRentalAgreement/not-a-uuid")
                .contentType(MediaType.APPLICATION_JSON));

            failedResponse.andExpect(MockMvcResultMatchers.status().isNotFound());
        } catch (Exception e) {
            fail("Should not throw an exception");
        }
    }
}

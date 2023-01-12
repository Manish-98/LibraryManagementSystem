package com.personal.librarymanagementsystem.controller;

import com.personal.librarymanagementsystem.builder.AddressBuilder;
import com.personal.librarymanagementsystem.model.Address;
import com.personal.librarymanagementsystem.model.Library;
import com.personal.librarymanagementsystem.service.LibraryService;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LibraryController.class)
class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryService libraryService;

    @Test
    void shouldSaveLibrary() throws Exception {
        @Language("JSON")
        String requestBody = """
                {
                    "name": "Central Library",
                    "address": {
                        "zipCode": "100001"
                    }
                }
                """.stripIndent();

        Address address = new AddressBuilder("100001").build();
        Library centralLibrary = new Library(UUID.randomUUID(), "Central Library", address);
        when(libraryService.save(any())).thenReturn(centralLibrary);

        mockMvc.perform(post("/lms/api/v1/library")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json(requestBody, false));
    }

    @Test
    void shouldReturnBadRequestIfLibraryNameIsBlank() throws Exception {
        @Language("JSON")
        String requestBody = """
                {
                    "address": {
                        "zipCode": "100001"
                    }
                }
                """.stripIndent();

        @Language("JSON")
        String responseBody = """
                {
                    "name": ["Library name should not be blank"]
                }
                """;

        mockMvc.perform(post("/lms/api/v1/library")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(responseBody, false));
    }

    @Test
    void shouldReturnBadRequestIfAddressIsMissing() throws Exception {
        @Language("JSON")
        String requestBody = """
                {
                    "name": "Central Library"
                }
                """.stripIndent();

        @Language("JSON")
        String responseBody = """
                {
                    "address": ["Library address is missing"]
                }
                """;

        mockMvc.perform(post("/lms/api/v1/library")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(responseBody, false));
    }

    @Test
    void shouldReturnBadRequestIfAddressZipCodeIsInvalid() throws Exception {
        @Language("JSON")
        String requestBody = """
                {
                    "name": "Central Library",
                    "address": {
                        "zipCode": "someZipCode"
                    }
                }
                """.stripIndent();

        @Language("JSON")
        String responseBody = """
                {
                    "address.zipCode": ["Invalid zip code"]
                }
                """;

        mockMvc.perform(post("/lms/api/v1/library")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(responseBody, false));
    }
}

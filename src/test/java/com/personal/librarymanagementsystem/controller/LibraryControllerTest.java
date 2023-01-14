package com.personal.librarymanagementsystem.controller;

import com.personal.librarymanagementsystem.builder.AddressBuilder;
import com.personal.librarymanagementsystem.builder.LibraryBuilder;
import com.personal.librarymanagementsystem.model.Address;
import com.personal.librarymanagementsystem.model.Library;
import com.personal.librarymanagementsystem.service.LibraryService;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Nested
    class SaveLibrary {
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
            Library centralLibrary = new Library(UUID.randomUUID(), "Central Library", address, LocalDateTime.of(2023, 1, 1, 0, 0));
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
                    .andExpect(content().json(responseBody, true));
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
                    .andExpect(content().json(responseBody, true));
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
                    .andExpect(content().json(responseBody, true));
        }
    }

    @Nested
    class ReadAllLibraries {
        @Test
        void shouldReturnAllLibrariesInSystem() throws Exception {
            UUID libraryIdOne = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
            UUID libraryIdTwo = UUID.fromString("123e4567-e89b-12d3-a456-426614174001");
            Library libraryOne = new LibraryBuilder(libraryIdOne).withName("Library 1").build();
            Library libraryTwo = new LibraryBuilder(libraryIdTwo).withName("Library 2").build();
            when(libraryService.getAll()).thenReturn(List.of(libraryOne, libraryTwo));

            @Language("JSON")
            String expectedResponse = """
                [
                    {
                        "id": "123e4567-e89b-12d3-a456-426614174000",
                        "name": "Library 1",
                        "address": {
                            "street": null,
                            "city": null,
                            "state": null,
                            "country": null,
                            "zipCode": "100000"
                        },
                        "createdAt": "2023-01-01T00:00:00"
                    },
                    {
                        "id": "123e4567-e89b-12d3-a456-426614174001",
                        "name": "Library 2",
                        "address": {
                            "street": null,
                            "city": null,
                            "state": null,
                            "country": null,
                            "zipCode": "100000"
                        },
                        "createdAt": "2023-01-01T00:00:00"
                    }
                ]
                """.stripIndent();

            mockMvc.perform(get("/lms/api/v1/libraries")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(expectedResponse, true));
        }
    }
}

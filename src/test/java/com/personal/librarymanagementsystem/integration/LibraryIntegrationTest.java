package com.personal.librarymanagementsystem.integration;

import com.personal.librarymanagementsystem.builder.LibraryBuilder;
import com.personal.librarymanagementsystem.model.Address;
import com.personal.librarymanagementsystem.model.Library;
import com.personal.librarymanagementsystem.repository.LibraryRepository;
import com.personal.librarymanagementsystem.utils.AssertUtils;
import com.personal.librarymanagementsystem.utils.TestUtils;
import org.intellij.lang.annotations.Language;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public class LibraryIntegrationTest extends IntegrationTest{
    @Autowired
    LibraryRepository libraryRepository;

    @BeforeEach
    void setUp() {
        libraryRepository.deleteAll();
    }

    @Test
    void shouldCreateLibrary() throws JSONException {
        @Language("JSON")
        String requestBody = """
                {
                    "name": "Central Library",
                    "address": {
                        "street": "M.G. Road",
                        "city": "Delhi",
                        "state": "New Delhi",
                        "country": "India",
                        "zipCode": "100001"
                    }
                }
                """.stripIndent();

        @Language("JSON")
        String responseBody = """
                {
                    "name": "Central Library",
                    "address": {
                        "street": "M.G. Road",
                        "city": "Delhi",
                        "state": "New Delhi",
                        "country": "India",
                        "zipCode": "100001"
                    },
                    "createdAt": "2023-01-01T00:00:00"
                }
                """.stripIndent();

        String url = "http://localhost:" + randomServerPort + "/lms/api/v1/library";
        HttpEntity<String> request = TestUtils.getHttpEntity(requestBody);

        ResponseEntity<String> response = testRestTemplate.exchange(url, HttpMethod.POST, request, String.class);

        AssertUtils.assertResponseLenient(responseBody, response, HttpStatus.OK);

        List<Library> libraries = libraryRepository.findAll();
        Assertions.assertEquals(1, libraries.size());
        Assertions.assertEquals("Central Library", libraries.get(0).name());
        Assertions.assertEquals(new Address("M.G. Road", "Delhi", "New Delhi", "India", "100001"), libraries.get(0).address());
    }

    @Test
    void shouldGetAllLibraries() throws JSONException {
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
                    }, {
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

        UUID libraryIdOne = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID libraryIdTwo = UUID.fromString("123e4567-e89b-12d3-a456-426614174001");
        Library libraryOne = new LibraryBuilder(libraryIdOne).withName("Library 1").build();
        Library libraryTwo = new LibraryBuilder(libraryIdTwo).withName("Library 2").build();
        libraryRepository.saveAll(List.of(libraryOne, libraryTwo));

        String url = "http://localhost:" + randomServerPort + "/lms/api/v1/libraries";

        HttpEntity<String> request = TestUtils.getHttpEntity();
        ResponseEntity<String> response = testRestTemplate.exchange(url, HttpMethod.GET, request, String.class);

        AssertUtils.assertResponseNonExtensible(expectedResponse, response, HttpStatus.OK);
    }
}

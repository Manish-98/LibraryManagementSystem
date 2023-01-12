package com.personal.librarymanagementsystem.integration;

import com.personal.librarymanagementsystem.model.Address;
import com.personal.librarymanagementsystem.model.Library;
import com.personal.librarymanagementsystem.repository.LibraryRepository;
import com.personal.librarymanagementsystem.utils.AssertUtils;
import com.personal.librarymanagementsystem.utils.TestUtils;
import org.intellij.lang.annotations.Language;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@Testcontainers
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LibraryControllerIntegrationTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @LocalServerPort
    int randomServerPort;

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.2").withReuse(true);

    @Autowired
    LibraryRepository libraryRepository;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
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

        String url = "http://localhost:" + randomServerPort + "/lms/api/v1/library";
        HttpEntity<String> request = TestUtils.getHttpEntity(requestBody);

        ResponseEntity<String> response = testRestTemplate.exchange(url, HttpMethod.POST, request, String.class);

        AssertUtils.assertResponse(requestBody, response, HttpStatus.OK);

        List<Library> libraries = libraryRepository.findAll();
        Assertions.assertEquals(1, libraries.size());
        Assertions.assertEquals("Central Library", libraries.get(0).name());
        Assertions.assertEquals(new Address("M.G. Road", "Delhi", "New Delhi", "India", "100001"), libraries.get(0).address());
    }

}

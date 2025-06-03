package org.estore.estore.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class MediaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetMedia() throws Exception {
        String blobId = "kYgL7J7x_1y3WxMt4bD4Ho59mm8YIf5Y7lr5O9a33Os";
        mockMvc.perform(get("/api/v1/media")
                .queryParam("blobId", blobId))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

    }
}

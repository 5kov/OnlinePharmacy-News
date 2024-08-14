package bg.softuni.onlinepharmacynews.controller;

import bg.softuni.onlinepharmacynews.model.dto.AddNewsDTO;
import bg.softuni.onlinepharmacynews.model.dto.NewsDTO;
import bg.softuni.onlinepharmacynews.service.impl.NewsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class NewsControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NewsServiceImpl newsServiceImpl;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    public void testGetAllNews() throws Exception {
        NewsDTO newsDTO = new NewsDTO();
        List<NewsDTO> newsList = Collections.singletonList(newsDTO);

        when(newsServiceImpl.getAllNews()).thenReturn(newsList);

        mockMvc.perform(get("/news"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    public void testCreateNews() throws Exception {

        mockMvc.perform(post("/news")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titleEn\":\"Test News EN\",\"titleBg\":\"Test News BG\",\"contentEn\":\"This is a test news content in English.\",\"contentBg\":\"This is a test news content in Bulgarian.\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteNews() throws Exception {
        mockMvc.perform(delete("/news/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindById() throws Exception {
        NewsDTO newsDTO = new NewsDTO();
        when(newsServiceImpl.getNewsById(anyLong())).thenReturn(newsDTO);

        mockMvc.perform(get("/news/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void testFindByTitleContaining() throws Exception {
        NewsDTO newsDTO = new NewsDTO();
        List<NewsDTO> newsList = Collections.singletonList(newsDTO);

        when(newsServiceImpl.getNewsByTitleContaining(anyString())).thenReturn(newsList);

        mockMvc.perform(get("/news/title/{title}", "Test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    public void testFindByTitle() throws Exception {
        NewsDTO newsDTO = new NewsDTO();
        List<NewsDTO> newsList = Collections.singletonList(newsDTO);

        when(newsServiceImpl.getNewsByTitle(anyString())).thenReturn(newsList);

        mockMvc.perform(get("/news/titleCheck/{title}", "Test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    public void testFindByTitleNotFound() throws Exception {
        when(newsServiceImpl.getNewsByTitle(anyString())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/news/titleCheck/{title}", "Nonexistent"))
                .andExpect(status().isNotFound());
    }
}

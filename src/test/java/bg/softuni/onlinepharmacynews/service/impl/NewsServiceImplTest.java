package bg.softuni.onlinepharmacynews.service.impl;

import bg.softuni.onlinepharmacynews.model.dto.AddNewsDTO;
import bg.softuni.onlinepharmacynews.model.dto.NewsDTO;
import bg.softuni.onlinepharmacynews.model.entity.News;
import bg.softuni.onlinepharmacynews.repository.NewsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.Instant;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class NewsServiceImplTest {

    @Mock
    private NewsRepository newsRepository;

    @InjectMocks
    private NewsServiceImpl newsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Period retentionPeriod = Period.ofDays(30);
        newsService = new NewsServiceImpl(newsRepository, retentionPeriod);
    }

    @Test
    void testCreateNews() {
        AddNewsDTO addNewsDTO = new AddNewsDTO();
        addNewsDTO.setTitleEn("Test Title EN");
        addNewsDTO.setTitleBg("Тест Заглавие BG");
        addNewsDTO.setContentEn("Test Content EN");
        addNewsDTO.setContentBg("Тест Съдържание BG");

        newsService.createNews(addNewsDTO);

        ArgumentCaptor<News> newsCaptor = ArgumentCaptor.forClass(News.class);
        verify(newsRepository).save(newsCaptor.capture());

        News savedNews = newsCaptor.getValue();
        assertThat(savedNews.getTitleEn()).isEqualTo("Test Title EN");
        assertThat(savedNews.getTitleBg()).isEqualTo("Тест Заглавие BG");
        assertThat(savedNews.getContentEn()).isEqualTo("Test Content EN");
        assertThat(savedNews.getContentBg()).isEqualTo("Тест Съдържание BG");
        assertThat(savedNews.getCreated()).isNotNull();
    }

    @Test
    void testGetAllNews() {
        News news1 = new News();
        news1.setId(1L);
        news1.setTitleEn("Test Title EN 1");
        news1.setTitleBg("Тест Заглавие BG 1");
        news1.setContentEn("Test Content EN 1");
        news1.setContentBg("Тест Съдържание BG 1");
        news1.setCreated(Instant.now());

        News news2 = new News();
        news2.setId(2L);
        news2.setTitleEn("Test Title EN 2");
        news2.setTitleBg("Тест Заглавие BG 2");
        news2.setContentEn("Test Content EN 2");
        news2.setContentBg("Тест Съдържание BG 2");
        news2.setCreated(Instant.now());

        when(newsRepository.findAll()).thenReturn(Arrays.asList(news1, news2));

        List<NewsDTO> result = newsService.getAllNews();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo(1L);
        assertThat(result.get(1).getId()).isEqualTo(2L);
    }

    @Test
    void testDeleteNews() {
        Long newsId = 1L;

        newsService.deleteNews(newsId);

        verify(newsRepository).deleteById(newsId);
    }

    @Test
    void testGetNewsById_Success() {
        News news = new News();
        news.setId(1L);
        news.setTitleEn("Test Title EN");
        news.setTitleBg("Тест Заглавие BG");
        news.setContentEn("Test Content EN");
        news.setContentBg("Тест Съдържание BG");
        news.setCreated(Instant.now());

        when(newsRepository.findById(1L)).thenReturn(Optional.of(news));

        NewsDTO result = newsService.getNewsById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitleEn()).isEqualTo("Test Title EN");
    }

    @Test
    void testGetNewsById_NotFound() {
        when(newsRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> newsService.getNewsById(1L));
    }

    @Test
    void testGetNewsByTitleContaining() throws UnsupportedEncodingException {
        News news = new News();
        news.setId(1L);
        news.setTitleEn("Test Title EN");
        news.setTitleBg("Тест Заглавие BG");
        news.setContentEn("Test Content EN");
        news.setContentBg("Тест Съдържание BG");
        news.setCreated(Instant.now());

        String encodedTitle = URLEncoder.encode("Test", "UTF-8");

        when(newsRepository.findByTitleEnContainingIgnoreCaseOrTitleBgContainingIgnoreCase("Test", "Test"))
                .thenReturn(List.of(news));

        List<NewsDTO> result = newsService.getNewsByTitleContaining(encodedTitle);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitleEn()).isEqualTo("Test Title EN");
    }

    @Test
    void testGetNewsByTitle() throws UnsupportedEncodingException {
        News news = new News();
        news.setId(1L);
        news.setTitleEn("Test Title EN");
        news.setTitleBg("Тест Заглавие BG");
        news.setContentEn("Test Content EN");
        news.setContentBg("Тест Съдържание BG");
        news.setCreated(Instant.now());

        String encodedTitle = URLEncoder.encode("Test Title EN", "UTF-8");

        when(newsRepository.findByTitleEnEqualsOrTitleBgEquals("Test Title EN", "Test Title EN"))
                .thenReturn(List.of(news));

        List<NewsDTO> result = newsService.getNewsByTitle(encodedTitle);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitleEn()).isEqualTo("Test Title EN");
    }

    @Test
    void testCleanupOldNews() {
        Period retentionPeriod = Period.ofDays(30);
        newsService = new NewsServiceImpl(newsRepository, retentionPeriod);

        // Capture the argument passed to deleteOldOffers
        ArgumentCaptor<Instant> instantCaptor = ArgumentCaptor.forClass(Instant.class);

        newsService.cleanupOldNews();

        // Verify that the method was called and capture the argument
        verify(newsRepository).deleteOldOffers(instantCaptor.capture());

        // Get the captured Instant value
        Instant capturedInstant = instantCaptor.getValue();

        // Calculate the expected Instant value
        Instant expectedInstant = Instant.now().minus(retentionPeriod);

        // Assert that the captured instant is within a reasonable range of the expected value
        Assertions.assertThat(capturedInstant)
                .isCloseTo(expectedInstant, Assertions.within(5, ChronoUnit.SECONDS));
    }
}

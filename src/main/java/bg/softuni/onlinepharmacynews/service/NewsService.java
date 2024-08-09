package bg.softuni.onlinepharmacynews.service;

import bg.softuni.onlinepharmacynews.model.dto.AddNewsDTO;
import bg.softuni.onlinepharmacynews.model.dto.NewsDTO;
import bg.softuni.onlinepharmacynews.model.entity.News;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface NewsService {
    void createNews(AddNewsDTO addNewsDTO);

    List<NewsDTO> getAllNews();

    void deleteNews(Long id);

    NewsDTO getNewsById(Long id);

    List<NewsDTO> getNewsByTitleContaining(String title) throws UnsupportedEncodingException;

    List<NewsDTO> getNewsByTitle(String title) throws UnsupportedEncodingException;

    void cleanupOldNews();

}

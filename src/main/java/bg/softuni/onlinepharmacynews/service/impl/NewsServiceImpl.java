package bg.softuni.onlinepharmacynews.service.impl;

import bg.softuni.onlinepharmacynews.model.dto.AddNewsDTO;
import bg.softuni.onlinepharmacynews.model.dto.NewsDTO;
import bg.softuni.onlinepharmacynews.model.entity.News;
import bg.softuni.onlinepharmacynews.repository.NewsRepository;
import bg.softuni.onlinepharmacynews.service.NewsService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(NewsServiceImpl.class);
    private final Period retentionPeriod;
    private final ModelMapper modelMapper;
//"${news.retention.period}"
    public NewsServiceImpl(NewsRepository newsRepository, @Value("${news.retention.period}") Period retentionPeriod, ModelMapper modelMapper) {
        this.newsRepository = newsRepository;
        this.retentionPeriod = retentionPeriod;
        this.modelMapper = modelMapper;
    }
    @Override
    public void createNews(AddNewsDTO addNewsDTO) {
        newsRepository.save(map(addNewsDTO));

    }

    private News map(AddNewsDTO addNewsDTO) {
        return modelMapper.map(addNewsDTO, News.class);
    }
    @Override
    public List<NewsDTO> getAllNews() {
        List<NewsDTO> list = new ArrayList<>();
        for (News news : newsRepository.findAll()) {
            NewsDTO map = map(news);
            list.add(map);
        }
        return list;
    }

    private NewsDTO map(News news) {
        return modelMapper.map(news, NewsDTO.class);
    }
    @Override
    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }

    @Override
    public NewsDTO getNewsById(Long id) {
        return newsRepository.findById(id).map(this::map).orElseThrow(() -> new IllegalArgumentException("Not found!"));
    }
    @Override
    public List<NewsDTO> getNewsByTitleContaining(String title){
        String decodedString = URLDecoder.decode(title, StandardCharsets.UTF_8);
        List<NewsDTO> list = new ArrayList<>();
        for (News news : newsRepository.findByTitleEnContainingIgnoreCaseOrTitleBgContainingIgnoreCase(decodedString, decodedString)) {
            NewsDTO map = map(news);
            list.add(map);
        }
        return list;
    }
    @Override
    public List<NewsDTO> getNewsByTitle(String title){
        String decodedString = URLDecoder.decode(title, StandardCharsets.UTF_8).trim();
        List<NewsDTO> list = new ArrayList<>();
        for (News news : newsRepository.findByTitleEnEqualsOrTitleBgEquals(decodedString, decodedString)) {
            NewsDTO map = map(news);
            list.add(map);
        }
        return list;
    }
    @Override
    public void cleanupOldNews() {
        Instant now = Instant.now();
        Instant deleteBefore = now.minus(retentionPeriod);
        LOGGER.info("Removing all news older than {}", deleteBefore);
        newsRepository.deleteOldOffers(deleteBefore);
        LOGGER.info("Old news were removed");
    }

}

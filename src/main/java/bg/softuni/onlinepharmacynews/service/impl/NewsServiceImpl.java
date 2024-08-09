package bg.softuni.onlinepharmacynews.service.impl;

import bg.softuni.onlinepharmacynews.model.dto.AddNewsDTO;
import bg.softuni.onlinepharmacynews.model.dto.NewsDTO;
import bg.softuni.onlinepharmacynews.model.entity.News;
import bg.softuni.onlinepharmacynews.repository.NewsRepository;
import bg.softuni.onlinepharmacynews.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.Instant;
import java.time.Period;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(NewsServiceImpl.class);
    private final Period retentionPeriod;
//"${news.retention.period}"
    public NewsServiceImpl(NewsRepository newsRepository, @Value("${news.retention.period}") Period retentionPeriod) {
        this.newsRepository = newsRepository;
        this.retentionPeriod = retentionPeriod;
    }
    @Override
    public void createNews(AddNewsDTO addNewsDTO) {
        newsRepository.save(map(addNewsDTO));

    }

    private static News map(AddNewsDTO addNewsDTO) {
        News news = new News();
        news.setContentBg(addNewsDTO.getContentBg());
        news.setContentEn(addNewsDTO.getContentEn());
        news.setTitleBg(addNewsDTO.getTitleBg());
        news.setTitleEn(addNewsDTO.getTitleEn());

        // todo use model mapper
        return news;
    }
    @Override
    public List<NewsDTO> getAllNews() {
        return newsRepository.findAll().stream().map(NewsServiceImpl::map).toList();
    }

    private static NewsDTO map(News news) {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(news.getId());
        newsDTO.setContentBg(news.getContentBg());
        newsDTO.setContentEn(news.getContentEn());
        newsDTO.setTitleBg(news.getTitleBg());
        newsDTO.setTitleEn(news.getTitleEn());
        return newsDTO;
    }
    @Override
    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }

    @Override
    public NewsDTO getNewsById(Long id) {
        return newsRepository.findById(id).map(NewsServiceImpl::map).orElseThrow(() -> new IllegalArgumentException("Not found!"));
    }
    @Override
    public List<NewsDTO> getNewsByTitleContaining(String title) throws UnsupportedEncodingException {
        String decodedString = URLDecoder.decode(title, "UTF-8");
        return newsRepository.findByTitleEnContainingIgnoreCaseOrTitleBgContainingIgnoreCase(decodedString, decodedString).stream().map(NewsServiceImpl::map).toList();
    }
    @Override
    public List<NewsDTO> getNewsByTitle(String title) throws UnsupportedEncodingException {
        String decodedString = URLDecoder.decode(title, "UTF-8").trim();
        return newsRepository.findByTitleEnEqualsOrTitleBgEquals(decodedString, decodedString).stream().map(NewsServiceImpl::map).toList();
    }
    @Override
    public void cleanupOldNews() {
        Instant now = Instant.now();
        Instant deleteBefore = now.minus(retentionPeriod);
        LOGGER.info("Removing all news older than " + deleteBefore);
        newsRepository.deleteOldOffers(deleteBefore);
        LOGGER.info("Old news were removed");
    }

}

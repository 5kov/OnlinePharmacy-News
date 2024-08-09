package bg.softuni.onlinepharmacynews.service;

import bg.softuni.onlinepharmacynews.service.impl.NewsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RetentionScheduler {

  private final Logger LOGGER = LoggerFactory.getLogger(RetentionScheduler.class);
  private final NewsServiceImpl newsServiceImpl;

  public RetentionScheduler(NewsServiceImpl newsServiceImpl) {
      this.newsServiceImpl = newsServiceImpl;
  }

//  @Scheduled(cron = "0 0 2 * * SAT")
//  @Scheduled(cron = "*/10 * * * * *") // run the task every 10 seconds
  @Scheduled(cron = "* * * 1 * *") // run the task every month
  public void deleteOldRecords() {
    LOGGER.info("Start deletion of old objects.");
    newsServiceImpl.cleanupOldNews();
    LOGGER.info("Start deletion finished.");
  }
}

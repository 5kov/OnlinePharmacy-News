package bg.softuni.onlinepharmacynews.repository;

import bg.softuni.onlinepharmacynews.model.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByTitleEnContainingIgnoreCaseOrTitleBgContainingIgnoreCase(String title, String title2);

    List<News> findByTitleEnEqualsOrTitleBgEquals(String title, String title2);

    @Transactional
    @Modifying
    @Query("DELETE FROM News o WHERE o.created < :olderThan")
    void deleteOldOffers(Instant olderThan);

}

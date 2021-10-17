package bg.softuni.mobilele.repos;

import bg.softuni.mobilele.models.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findAll();
    Optional<Offer> findById(Long id);

}

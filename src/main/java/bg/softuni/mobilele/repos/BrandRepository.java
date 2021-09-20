package bg.softuni.mobilele.repos;

import bg.softuni.mobilele.models.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findById(Long id);

    @Override
    List<Brand> findAll();
}

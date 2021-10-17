package bg.softuni.mobilele.repos;

import bg.softuni.mobilele.models.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findById(Long id);

    @Override
    List<Brand> findAll();

    @Query(value = "select b from Brand b join Model m on b.id = m.brand.id where m.name = :name")
    Optional<Brand> findByModel(@Param("name") String model);
}

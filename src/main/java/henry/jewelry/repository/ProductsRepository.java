package henry.jewelry.repository;

import henry.jewelry.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT u FROM Product u WHERE u.type = ?1 AND u.metal = ?2")
    List<Product> findAllByTypeAndMetal(String type, String metal);

    Product findById(int productId);
}

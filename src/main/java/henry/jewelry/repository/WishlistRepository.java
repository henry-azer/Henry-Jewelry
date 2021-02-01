package henry.jewelry.repository;

import henry.jewelry.entity.Product;
import henry.jewelry.entity.User;
import henry.jewelry.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

    Collection<Wishlist> findWishesByUser(User user);

    @Query("SELECT u FROM Wishlist u WHERE u.user = ?1 AND u.product = ?2")
    Wishlist findWishesByUserAndProduct(User user, Product product);

    @Modifying
    @Query("DELETE FROM Wishlist u WHERE u.user.id =:#{#user.id} AND u.product.id =:#{#product.id}")
    void deleteByUserAndProduct(@Param("user") User user, @Param("product") Product product);

}

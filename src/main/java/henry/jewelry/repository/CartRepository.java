package henry.jewelry.repository;

import henry.jewelry.entity.Cart;
import henry.jewelry.entity.Product;
import henry.jewelry.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CartRepository extends JpaRepository <Cart, Integer> {

    Collection<Cart> findCartsByUser(User user);

    @Query("SELECT u FROM Cart u WHERE u.user = ?1 AND u.product = ?2")
    Cart findCartsByUserAndProduct(User user, Product product);

    @Modifying
    @Query("DELETE FROM Cart u WHERE u.user.id =:#{#user.id} AND u.product.id =:#{#product.id}")
    void deleteByUserAndProduct(@Param("user") User user, @Param("product") Product product);

    @Modifying
    @Query("DELETE FROM Cart u WHERE u.user.id =:#{#user.id}")
    void deleteAllByUser(@Param("user") User user);

}

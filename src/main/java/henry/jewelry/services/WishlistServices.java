package henry.jewelry.services;

import henry.jewelry.entity.User;
import henry.jewelry.entity.Wishlist;
import javassist.NotFoundException;

import java.util.Collection;

public interface WishlistServices {

    Collection<Wishlist> getAllWishlistProductsByUser(User user);
    void addProductToWishlist(Integer productId, User user) throws NotFoundException;
    void deleteProductFromWishlist(Integer productId, User user) throws NotFoundException;

}

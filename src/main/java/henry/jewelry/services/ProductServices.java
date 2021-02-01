package henry.jewelry.services;


import henry.jewelry.entity.Product;
import javassist.NotFoundException;

import java.util.Collection;
import java.util.List;

public interface ProductServices {

    List<Product> getAllProductsByTypeAndMetal(String type, String metal);
    Product getProductById(int productId) throws NotFoundException;
    List getSuggestedProducts(String type, String metal);

    Collection<Product> getAllNecklaceDiamond();
    Collection<Product> getAllNecklaceSilver();
    Collection<Product> getAllNecklaceGold();

    Collection<Product> getAllRingsDiamond();
    Collection<Product> getAllRingsSilver();
    Collection<Product> getAllRingsGold();

    Collection<Product> getAllEarringsDiamond();
    Collection<Product> getAllEarringsSilver();
    Collection<Product> getAllEarringsGold();


}

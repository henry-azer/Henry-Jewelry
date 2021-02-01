package henry.jewelry.services.impelementations;

import henry.jewelry.entity.Product;
import henry.jewelry.repository.ProductsRepository;
import henry.jewelry.services.ProductServices;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ProductServicesImpl implements ProductServices {

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    @Transactional
    public List<Product> getAllProductsByTypeAndMetal(String type, String metal) {
        return productsRepository.findAllByTypeAndMetal(type, metal);
    }

    @Override
    @Transactional
    public Product getProductById(int productId) throws NotFoundException {
        Product product = productsRepository.findById(productId);
        if (product == null) throw new NotFoundException("Product not Found.");

        return product;
    }

    @Override
    public List<Product> getSuggestedProducts(String type, String metal) {
        List<Product> productList = getAllProductsByTypeAndMetal(type, metal);
        int listSize = productList.size();

        ThreadLocalRandom random = ThreadLocalRandom.current();
        while (listSize > 3) {
            productList.remove(random.nextInt(0,listSize));
            listSize--;
        }

        return productList;
    }

    @Override
    public Collection<Product> getAllNecklaceDiamond() {
        return getAllProductsByTypeAndMetal("Necklace", "Diamond");
    }

    @Override
    public Collection<Product> getAllNecklaceSilver() {
        return getAllProductsByTypeAndMetal("Necklace", "Silver");
    }

    @Override
    public Collection<Product> getAllNecklaceGold() {
        return getAllProductsByTypeAndMetal("Necklace", "Gold");
    }

    @Override
    public Collection<Product> getAllRingsDiamond() {
        return getAllProductsByTypeAndMetal("Ring", "Diamond");
    }

    @Override
    public Collection<Product> getAllRingsSilver() {
        return getAllProductsByTypeAndMetal("Ring", "Silver");
    }

    @Override
    public Collection<Product> getAllRingsGold() {
        return getAllProductsByTypeAndMetal("Ring", "Gold");
    }

    @Override
    public Collection<Product> getAllEarringsDiamond() {
        return getAllProductsByTypeAndMetal("Earrings", "Diamond");
    }

    @Override
    public Collection<Product> getAllEarringsSilver() {
        return getAllProductsByTypeAndMetal("Earrings", "Silver");
    }

    @Override
    public Collection<Product> getAllEarringsGold() {
        return getAllProductsByTypeAndMetal("Earrings", "Gold");
    }
}

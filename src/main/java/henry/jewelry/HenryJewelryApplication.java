package henry.jewelry;

import henry.jewelry.entity.Product;
import henry.jewelry.entity.Role;
import henry.jewelry.entity.User;
import henry.jewelry.repository.ProductsRepository;
import henry.jewelry.repository.RoleRepository;
import henry.jewelry.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class HenryJewelryApplication{

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductsRepository productsRepository;


    public static void main(final String[] args) {
        SpringApplication.run(HenryJewelryApplication.class, args);
    }

    @PostConstruct
    public void initUsers() {
        Collection<Role> roles = Stream.of(
                new Role(1, "ROLE_USER"),
                new Role(2, "ROLE_ADMIN")).collect(Collectors.toList());
        roleRepository.saveAll(roles);

        String passEncoded = new BCryptPasswordEncoder().encode("pass");
        List<User> userList = Stream.of(new User(1, "user", passEncoded, Collections.singletonList(roleRepository.findRoleByName("ROLE_USER"))))
                .collect(Collectors.toList());
        userRepository.saveAll(userList);
    }

    @PostConstruct
    public void initProducts() throws FileNotFoundException, URISyntaxException {
        Scanner scan = new Scanner(new File(getClass().getResource("/static/csv/productlist.csv").toURI()));
        ArrayList<String[]> records = new ArrayList<String[]>();
        String[] record = new String[2];
        while(scan.hasNext()) {
            record = scan.nextLine().split(",");
            records.add(record);
        }

        Collection<Product> productCollection = new ArrayList<>();
        for(String[] temp : records) {
            Product product = new Product();
            int counter = 0;
            for(String temp1 : temp) {
                if (counter == 0) product.setId(Integer.parseInt(temp1));
                else if (counter == 1) product.setImgURL(temp1);
                else if (counter == 2) product.setType(temp1);
                else if (counter == 3) product.setMetal(temp1);
                else if (counter == 4) product.setDescription(temp1);
                else if (counter == 5) product.setOriginalPrice(Double.parseDouble(temp1));
                else if (counter == 6) product.setDiscount(Integer.parseInt(temp1));
                else product.setPrice(Double.parseDouble(temp1));
                counter++;
            }
            productCollection.add(product);
        }
        productsRepository.saveAll(productCollection);
    }

}

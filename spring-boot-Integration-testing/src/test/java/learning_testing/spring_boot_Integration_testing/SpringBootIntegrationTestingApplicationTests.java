package learning_testing.spring_boot_Integration_testing;

import learning_testing.spring_boot_Integration_testing.entity.Product;
import learning_testing.spring_boot_Integration_testing.repository.TestH2ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootIntegrationTestingApplicationTests {

	@LocalServerPort
	private int port;

	private String baseUrl="http://localhost";

	private static RestTemplate restTemplate;

	@Autowired
	private TestH2ProductRepository repository;

	@BeforeAll
	public static void init(){
		restTemplate=new RestTemplate();
	}

	@BeforeEach
	public void setUp(){
		baseUrl=baseUrl.concat(":").concat(Integer.toString(port)).concat("/product");
	}

	@Test
	public void testAddProduct(){
		Product product=new Product("headset",2,7999);
		Product response=restTemplate.postForObject(baseUrl,product,Product.class);
		assertEquals(product.getName(),response.getName());
		assertEquals(1,repository.findAll().size());
	}

	@Test
	@Sql(statements = "INSERT INTO PRODUCT (id,name,quantity,price) VALUES(4,'AC',1,34000)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM PRODUCT WHERE name='AC'",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetProducts(){
		List<Product> products=restTemplate.getForObject(baseUrl,List.class);
		assertEquals(1,products.size());
		assertEquals(1,repository.findAll().size());
	}

	@Test
	@Sql(statements = "INSERT INTO PRODUCT (id,name,quantity,price) VALUES(1,'AC',1,34000)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM PRODUCT WHERE id=1",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetProductById(){
		Product product=restTemplate.getForObject(baseUrl+"/1",Product.class);
		assertEquals("AC", product.getName());
		assertAll(
				()->assertNotNull(product),
				()->assertEquals(1,product.getId())
		);
	}

	@Test
	@Sql(statements = "INSERT INTO PRODUCT (id,name,quantity,price) VALUES(1,'shoes',1,999)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM PRODUCT WHERE id=1",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateProduct(){
		Product newProduct=new Product(1L,"shoes",800,2);
		restTemplate.put(baseUrl,newProduct);

		Product product=restTemplate.getForObject(baseUrl+"/1",Product.class);

		assertEquals(product.getName(), newProduct.getName());
		assertAll(
				()->assertNotNull(product),
				()->assertEquals(1,product.getId()),
				()->assertEquals(2,product.getQuantity())
		);
	}

	@Test
	@Sql(statements = "INSERT INTO PRODUCT (id,name,quantity,price) VALUES(8,'books',5,1255)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void testDeleteProduct(){
		int recordCount=repository.findAll().size();
		assertEquals(1,recordCount);
		restTemplate.delete(baseUrl+"/{id}",8);
		assertEquals(0,repository.findAll().size());
	}
}

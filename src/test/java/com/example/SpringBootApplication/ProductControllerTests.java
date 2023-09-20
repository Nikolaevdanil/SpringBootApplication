package com.example.SpringBootApplication;


import com.example.SpringBootApplication.model.Product;
import com.example.SpringBootApplication.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private List<Product> productList;

    @BeforeEach
    void setUp() {
        productList = Arrays.asList(
                new Product(1L, "Type1", "Brand1"),
                new Product(2L, "Type2", "Brand2"),
                new Product(3L, "Type3", "Brand3")
        );
    }

    @Test
    void testListProducts() throws Exception {

        Mockito.when(productService.findAll()).thenReturn(productList);

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("list"))
                .andExpect(MockMvcResultMatchers.model().attribute("products", productList));
    }

    @Test
    void testShowAddForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("product"));
    }

    @Test
    void testAddProduct() throws Exception {
        Product newProduct = new Product(null, "NewType", "NewBrand");

        mockMvc.perform(MockMvcRequestBuilders.post("/products/add")
                        .param("type", newProduct.getType())
                        .param("brand", newProduct.getBrand()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/products"));

        Mockito.verify(productService, Mockito.times(1)).saveProduct(Mockito.any(Product.class));
    }



    @Test
    void testDeleteProduct() throws Exception {
        Long productId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/products/delete/{id}", productId))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/products"));

        Mockito.verify(productService, Mockito.times(1)).deleteById(productId);
    }
}
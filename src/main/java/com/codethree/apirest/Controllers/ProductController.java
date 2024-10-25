package com.codethree.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codethree.apirest.Entities.Product;
import com.codethree.apirest.Repositories.ProductRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController //Decoradir oara indicar que es una Api rest
@RequestMapping("/products") //Indica la url que esta escuchando
public class ProductController {
    //Direcciones de las cuales va a consumir el cliente
    @Autowired //Indica el repositorio-instaciancion del repositorio (Base De Datos)
    private ProductRepository productRepository;

    @GetMapping //Indica que es una peticion GET(Trae todo)
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/{id}") //Indica que es una peticion GET(Trae solo por ID)
    public Product getProductById(@PathVariable Long id){
        return productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No product found with ID: " + id));
    }

    @PostMapping //Indica que es una peticion POS(subir-guardar)
    public Product createProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product detailsProduct){
        Product product = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No product found with ID: " + id));

        product.setNombre(detailsProduct.getNombre());
        product.setPrecio(detailsProduct.getPrecio());

        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        Product product = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No product found with ID: " + id));

        productRepository.delete(product);
        return "The Product with ID " + id + " was removed.";
    }
}

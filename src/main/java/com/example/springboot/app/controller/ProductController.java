package com.example.springboot.app.controller;

import com.example.springboot.app.exception.Mensaje;
import com.example.springboot.app.model.Product;
import com.example.springboot.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/listaproductos")
    public ResponseEntity<?> getAllProduct(){
        List<Product> lista = productService.getAllProduct();
        if(lista.isEmpty()){
            return new ResponseEntity<>(new Mensaje("Sin productos en la base de datos"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(productService.getAllProduct());
    }

    @GetMapping("/detalleproducto/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        return ResponseEntity.ok().body(this.productService.getProductById(id));
    }

    @PostMapping("/creaproducto")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok().body(this.productService.createProduct(product));
    }


    @PutMapping("/actualizaproducto/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product){
        product.setId(id);
        return ResponseEntity.ok().body(this.productService.updateProduct(product));
    }

    @DeleteMapping("/eliminaproducto/{id}")
    public HttpStatus deleteProduct(@PathVariable long id){
        this.productService.deleteProduct(id);
        return HttpStatus.OK;
    }
}

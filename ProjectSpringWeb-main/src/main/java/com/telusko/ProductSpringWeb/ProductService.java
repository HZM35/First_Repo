package com.telusko.ProductSpringWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductDB db;
    List<Product> products = new ArrayList<>();

    public ResponseEntity<String> addProduct(Product p){
//        products.add(p);

        db.save(p);
        return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
    }
//
    public ResponseEntity<List<Product>> getAllProducts() {

        try {
            return new ResponseEntity<>(db.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<Product> getProduct(String name){
        try{
            return new ResponseEntity<>(db.findByName(name), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ResponseEntity<>(new Product(), HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity< List<Product>> getProductWithText(String text) {
        String str = text.toLowerCase();
        List<Product> prods = new ArrayList<>();

        for(Product p : products){
            String name = p.getName().toLowerCase();
            String type = p.getType().toLowerCase();
            String place = p.getPlace().toLowerCase();
            if(name.contains(str) || type.contains(str) || place.contains(str))
                prods.add(p);
        }
            try {
                return new ResponseEntity<>(prods, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();

            }
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteProduct(String name) {
        try{
            Product p = db.findByName(name);
            db.delete(p);
            return new ResponseEntity<>("DELETED", HttpStatus.FOUND);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ResponseEntity<>("FAILURE", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateProduct(Product p) {
        try{
            db.save(p);
            return new ResponseEntity<>("UPDATED", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("FAILURE", HttpStatus.BAD_REQUEST);
    }
}

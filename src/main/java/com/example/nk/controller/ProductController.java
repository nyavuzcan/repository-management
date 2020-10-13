package com.example.nk.controller;

import com.example.nk.entities.Product;
import com.example.nk.service.ProductService;
import com.example.nk.serviceImpl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping(value="/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
  @Autowired
  FileServiceImpl fileService;
  @Autowired
  ProductService productService;

  @PostMapping("/uploadFile")
  public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

    fileService.uploadFile(file);

    redirectAttributes.addFlashAttribute("message",
        "You successfully uploaded " + file.getOriginalFilename() + "!");

    return "redirect:/";
  }



  @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Product> addProduct(@RequestBody Product product) {
    return productService.addProduct(product);
  }

 @RequestMapping(value = "/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Product> editProduct(@RequestBody Product product) {
    return productService.editProduct(product);
  }

 @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Product> deleteProduct(@RequestBody Long id) {
    return productService.deleteProduct(id);
  }


}

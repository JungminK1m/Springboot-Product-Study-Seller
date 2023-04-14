package shop.mtcoding.productapp_seller.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shop.mtcoding.productapp_seller.model.Product;
import shop.mtcoding.productapp_seller.model.ProductRepository;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product")
    public String productList(Model model) {

        List<Product> productList = productRepository.findAll();
        model.addAttribute("productList", productList);

        return "productList";
    }

    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable Integer id, Model model) {

        Product product = productRepository.findById(id);
        model.addAttribute("product", product);

        return "productDetail";
    }

    @GetMapping("/productSave")
    public String productSave() {
        return "productSave";
    }

    @PostMapping("/product/save")
    public String save(String name, Integer price, Integer qty) {

        Product product = new Product();
        product.setProductName(name);
        product.setProductPrice(price);
        product.setProductQty(qty);

        int result = productRepository.insert(product);
        if (result != 1) {
            System.out.println("상품 등록 실패");
        }

        return "redirect:/product";
    }

    // 상품명 중복체크 컨트롤러
    @PostMapping("/productSave/checkName")
    public ResponseEntity<?> checkProductName(@RequestParam String name) {

        Product productName = productRepository.findByName(name);
        if (productName != null) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/product/{id}/updateForm")
    public String productUpdate(@PathVariable Integer id, Model model) {

        // Product product = productRepository.findById(id);
        // model.addAttribute("product", product);
        Product product = productRepository.findById(id);
        model.addAttribute("product", product);

        return "productUpdate";
    }

    // 첫 번째 방법, @Param으로 받기

    // @PostMapping("/product/{id}/update")
    // public String update(@PathVariable Integer id, String name, Integer price,
    // Integer qty) {

    // int result = productRepository.update(id, name, price, qty);

    // System.out.println("result : " + result);

    // if (result != 1) {
    // System.out.println("업데이트 실패");
    // return "redirect:/product/" + id + "/updateForm";
    // }
    // System.out.println("업데이트 완료");
    // return "redirect:/product/" + id;
    // }

    // 실험중 - Product 객체로 업데이트 하는 방법
    // 두 번째 방법

    @PostMapping("/product/{id}/update")
    public String update(@PathVariable Integer id, Model model, String name, Integer price,
            Integer qty) {

        Product p = productRepository.findById(id);
        model.addAttribute("product", p);

        // 테스트
        System.out.println("p 아이디: " + p.getProductId());
        System.out.println("p 이름: " + p.getProductName());
        System.out.println("p 가격: " + p.getProductPrice());
        System.out.println("p 재고: " + p.getProductQty());

        // Product product = new Product();
        p.setProductName(name);
        p.setProductPrice(price);
        p.setProductQty(qty);
        System.out.println("데이터 담음");

        // 업데이트
        int result = productRepository.update(p);

        // 테스트
        System.out.println("product 아이디: " + p.getProductId());
        System.out.println("product 이름: " + p.getProductName());
        System.out.println("product 가격: " + p.getProductPrice());
        System.out.println("product 재고: " + p.getProductQty());

        System.out.println("result : " + result);

        if (result != 1) {
            System.out.println("업데이트 실패");
            return "redirect:/product/" + id + "/updateForm";
        }
        System.out.println("업데이트 완료");
        return "redirect:/product/" + id;
    }

    @PostMapping("/product/{id}/delete")
    public String delete(@PathVariable Integer id) {
        int result = productRepository.deleteById(id);
        if (result != 1) {
            System.out.println("삭제 실패");
        }
        return "redirect:/product";
    }
}

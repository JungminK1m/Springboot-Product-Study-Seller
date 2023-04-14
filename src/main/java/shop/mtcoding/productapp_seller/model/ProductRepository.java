package shop.mtcoding.productapp_seller.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductRepository {

    public List<Product> findAll();

    public Product findById(Integer id);

    public int insert(Product product);

    // @Param으로 받는 방법
    // public int update(@Param("id") Integer id, @Param("name") String name,
    // @Param("price") Integer price,
    // @Param("qty") Integer qty);

    // Product 객체로 받는 방법
    public int update(Product product);

    // ajax 중복체크를 위한 메서드
    public Product findByName(String name);

    public int deleteById(Integer id);

}

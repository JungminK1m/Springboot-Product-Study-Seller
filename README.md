# 쇼핑몰 프로젝트 3단계 판매자 서버📦
3단계는 판매자(=관리자)와 구매자를 **다른 포트**를 사용하는 서버로 만들고<br/>
하나의 DB를 공유하는 방식으로 구현해 보았다.<br/>
구매자 포트 번호 : **8000**<br/>
판매자 포트 번호 : **8080**<br/>
[🏡스프링부트 ↔ MyBatis ↔ MySQL 연동하기 공부 정리](https://whiteclouds-dev.tistory.com/14)

<br/>

# 화면 구현 📺 

- 상품 목록보기
  - 상품 상세보기
- 상품 등록 페이지

<br/>

#### [판매자 상품 목록보기]
![image](https://github.com/JungminK1m/Springboot-Product-Study-V3-Seller/assets/118741874/8b289082-5457-4c68-b143-f732e1757c7d)
#### [⏫판매자 상품내역 DB 연동 확인]
![image](https://github.com/JungminK1m/Springboot-Product-Study-V3-Seller/assets/118741874/7303e1a0-6799-40bc-86a6-d6faf6ec6fc1)
#### [판매자 상품 상세보기]
![image](https://github.com/JungminK1m/Springboot-Product-Study-V3-Seller/assets/118741874/9191260b-c0ee-483d-a1ac-b41d270d9b67)
#### [판매자 상품 등록하기]
![image](https://github.com/JungminK1m/Springboot-Product-Study-V3-Seller/assets/118741874/33fa0303-5250-4d38-9d48-755b8de5a57c)


<br/>
  
# 사용기술 🧪
![Springboot](https://img.shields.io/badge/-Springboot-6DB33F)
![Java](https://img.shields.io/badge/-Java-F09820)
![CSS](https://img.shields.io/badge/-CSS-1572B6)
![HTML](https://img.shields.io/badge/-HTML-E34F26)
![JS](https://img.shields.io/badge/-JavaScript-F7DF1E)
![Bootstrap](https://img.shields.io/badge/-Bootstrap-7952B3)
![MyBatis](https://img.shields.io/badge/-MyBatis-B10000)
![MySQL](https://img.shields.io/badge/-MySQL-%2300758f)

<br/>

  
# 기능구현 🔧
#### ✔ 상품등록
- 상품명 Ajax 중복체크
```javascript
<script>

            // 중복체크 여부 = false - 아직 체크 안했으니까
            let sameCheck = false;

            // 상품명 중복체크
            $('#CheckproductName').on('click', function () {

                // 이렇게 데이터를 변수로 만들면 보기가 편하다
                let data = { name: $('#name').val() }

                $.ajax({
                    url: '/productSave/checkName/',
                    type: 'post',
                    data: data,
                    contentType: "application/x-www-form-urlencoded; charset=utf-8"

                }).done((res) => {
                    alert("등록 가능한 상품입니다")
                    // 콘솔창 확인용
                    console.log(res);
                    // 등록 가능하니까 체크 여부를 true로 변경
                    sameCheck = true;

                }).fail((err) => {
                    alert("이미 등록한 상품입니다")
                    // 콘솔창 확인용
                    console.log(err);
                    // 등록 불가이기 때문에 중복체크를 안한 것으로 설정 (아래에 이벤트 처리를 위해)
                    sameCheck = false;
                });
            });

            // 상품명을 입력하는 input 태그에 값이 변경될 때마다 sameCheck 를 false로 설정하는 이벤트
            // => false가 됐으니 상품명을 다른 걸로 바뀌면 꼭 중복체크를 다시 해야되게 만든다.
            $('#name').on('input', function (e) {
                sameCheck = false;
                console.log(sameCheck);
            });
        
            // 동일 상품명 등록하지 못하게 처리하는 이벤트 (최종 상품 등록 버튼)
            // form이 submit 될 때 실행되는 이벤트
            $('form').on('submit', function(e) {
                // == 주의
                if (sameCheck == false) {
                    alert("상품명 중복확인을 해 주세요.");
                    // e.preventDefault(); = 브라우저가 이벤트를 처리하는 동작을 중단시키는 메서드
                    // submit 이벤트를 중단시키기 위해 사용됨
                     e.preventDefault();
                     console.log(sameCheck);
                }else if (sameCheck == true) {
                    alert("상품이 등록되었습니다.");
                    console.log(sameCheck);
                }
            });
        </script>
```
   <br/>

# 테이블 생성 📁
```sql
CREATE TABLE user(
	user_id INT PRIMARY KEY auto_increment,
	user_name VARCHAR(20) NOT null,
	user_password VARCHAR(20) NOT null,
	user_email VARCHAR(20) NOT null,
	created_at TIMESTAMP NOT null
);
CREATE TABLE product(
	product_id INT PRIMARY KEY auto_increment,
	product_name VARCHAR(20) NOT null,
	product_price INT NOT null,
	product_qty INT NOT null,
	created_at TIMESTAMP NOT null
);
create table orders(
    orders_id int primary KEY auto_increment,
    orders_name varchar(20) NOT null,
    orders_price int NOT null,
    orders_qty int NOT null,
    product_id int NOT null,
    user_id int NOT null,
    created_at TIMESTAMP
);
```
# 더미 데이터 📰
```sql
INSERT INTO user(user_name, user_password, user_email, created_at) VALUES ('ssar', '1234', 'ssar@nate.com', NOW());
INSERT INTO user(user_name, user_password, user_email, created_at) VALUES ('kaka', '2345', 'kaka@nate.com', NOW());

INSERT INTO product(product_name, product_price, product_qty, created_at) VALUES('바나나', 3000, 98, NOW());
INSERT INTO product(product_name, product_price, product_qty, created_at) VALUES('딸기', 2000, 100, NOW());

INSERT INTO orders(orders_name, orders_price, orders_qty, product_id, user_id, created_at) VALUES ('바나나', 3000, 2, 1, 1, NOW());
INSERT INTO orders(orders_name, orders_price, orders_qty, product_id, user_id, created_at)  VALUES ('딸기', 2000, 5, 2, 2, NOW());
```


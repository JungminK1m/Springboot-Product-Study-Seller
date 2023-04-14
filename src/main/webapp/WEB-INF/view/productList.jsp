<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="layout/header.jsp" %>

        <div class="container">
            <table class="table table-striped mt-4">
                <thead>
                    <tr>
                        <th>상품 번호</th>
                        <th>상품 이름</th>
                        <th>상품 가격</th>
                        <th>상품 재고</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${productList}" var="product" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td><a href="/product/${product.productId}">${product.productName}</a></td>
                            <td>${product.productPrice}원</td>
                            <td>${product.productQty}개</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <%@ include file="layout/footer.jsp" %>
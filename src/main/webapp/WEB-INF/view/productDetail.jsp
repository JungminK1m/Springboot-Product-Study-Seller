<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="layout/header.jsp" %>

        <div class="center">
            <div style="margin: 20px;">
                <table border="1" style="width: 500px; height: 200px; text-align: center;">
                    <tr style="border: 1px solid">
                        <th style="background-color: rgb(185, 185, 185)">상품명</th>
                        <th>${product.productName}</th>
                    </tr>
                    <tr style="border: 1px solid">
                        <th style="background-color: rgb(185, 185, 185)">상품명</th>
                        <td>${product.productPrice}원</td>
                    </tr>
                    <tr style="border: 1px solid">
                        <th style="background-color: rgb(185, 185, 185)">상품명</th>
                        <td>${product.productQty}개</td>
                    </tr>
                </table>
                <div class="center" style="margin-top: 20px; text-align: center;">
                    <form type="submit" action="/product/${product.productId}/updateForm" method="get">
                        <button
                            style="width: 240px; height: 50px; margin-right: 20px; background-color: rgb(255, 210, 199);">수정하기</button>
                    </form>
                    <form type="submit" action="/product/${product.productId}/delete" method="post">
                        <button
                            style="width: 240px; height: 50px; margin: auto; background-color: rgb(250, 255, 182);">삭제하기</button>
                    </form>
                </div>
            </div>
        </div>

        <%@ include file="layout/footer.jsp" %>
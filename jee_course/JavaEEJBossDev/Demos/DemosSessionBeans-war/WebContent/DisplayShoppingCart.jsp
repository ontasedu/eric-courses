<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- Does session scope have a "Cart" attribute? --%>
<c:if test="${not empty sessionScope.Cart}">

    <%-- Get the items in the ShoppingCart bean, and assign to local variable --%>
    <c:set var="itemsInCart"  value="${sessionScope.Cart.items}" />

    <%-- Iterate through the items --%>
    Items in shopping cart: <br/>
    <ul>
    <c:forEach var="item" items="${itemsInCart}">
        <li>${item}</li>
    </c:forEach>
    </ul>

</c:if>
    
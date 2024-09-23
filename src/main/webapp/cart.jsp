<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Map, com.foodDelivery.model.Cart, com.foodDelivery.model.CartItem" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shopping Cart</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css">
</head>
<body>
	<h1>Shopping Cart</h1>
	<div class="cart-items">
			<%
			//HttpSession session = request.getSession();
			Cart cart = (Cart) session.getAttribute("cart");
			if(cart != null && !cart.getItems().isEmpty()) {
				for(CartItem item : cart.getItems().values()) {
			%>
			<div class="cart-item">
				<h3><%=item.getName() %></h3>
				<p>
					&#x2089;
					<%=item.getPrice() %></p>
				<form action="Checkout.jsp" method="post">
					<input type="hidden" name="itemid" value="<%=item.getItemId() %>">
					<label>Quantity: <input type="number" name="quantity"
							value="<%=item.getQuantity() %>" min="1">
					</label> 
					<input type="submit" name="action" value="update"
							class="update-btn"> 
					<input type="submit" name="action"
							value="remove" class="remove-btn">
				</form>
			</div>
			
			<%
				} //closing the for loop
			
			} else {
			%>
			<p>your cart is empty</p>
			<%
			}
			%>
			
			
			
			<%-----Add More Items Button  --%>
			<a href="menu?restaurantId=<%=session.getAttribute("restaurantId") %>"
					class="btn add-more-items-btn">Add More Items</a>
					
			<%---Proceed to Checkout Button --%>
			<%
			if(session.getAttribute("cart") != null) {
			%>
			<form action="Checkout.jsp" method="post">
				<input type="submit" value="Proceed to Checkout"
						class="btn proceed-to-checkout-btn">
			</form>
			<%
			}
			%>
			
			
	</div>
</body>
</html>
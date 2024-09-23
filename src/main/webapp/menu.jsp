<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, com.foodDelivery.model.Menu"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Menu</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
	</head>
	<body>
		<h1>Menu</h1>
		<div class="menu-items">
			<%
			List<Menu> menuList = (List<Menu>)request.getAttribute("menuList");
			if(menuList != null) {
				for(Menu menuItem : menuList) {
			%>
			<div class="menu-item">
				<h3><%=menuItem.getItemName() %></h3>
				<p><%=menuItem.getDescription() %></p>
				<p>Price: &#8377 <%=menuItem.getPrice() %></p>
				
				<form action="cart" method="post">
					<input type="hiddden" name="itemId" value="<%=menuItem.getMenuId() %>">
					Quantity: <input type="number" name="quantity" value="1" min="1" 
						class="quantity-input"> 
					<input type="submit"
						value="Add to cart" class="add-to-cart-btn"> 
					<input type="hidden" name="action" value="add">
				</form>
			</div>
			<%
				}
			} else {
			%>
				<p>No menu available at the moment.</p>
			<%
			}
			%>

		</div>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, com.foodDelivery.model.Restaurant" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Food Delivery - Home</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
	</head>
	<body>
		<h1>Available Restaurants</h1>
		<div class="restaurant-list">
			<%
			List<Restaurant> restaurants = (List<Restaurant>) request.getAttribute("restaurantList");
			if(restaurants != null) {
				for(Restaurant restaurant : restaurants)
				{
			%>
			<div class="restaurant-item">
				<%--Use appropriate attributes from your Restaurant model --%>
				<img src="images/<%=restaurant.getImagePath()%>" alt="Image of <%=restaurant.getName()%>">
				<h3> <%= restaurant.getName() %></h3>
				<p><%=restaurant.getCuisineType() %> - <%=restaurant.getDeliveryTime()%>mins </p>
				<a href="menu?RestaurantId=<%=restaurant.getRestaurantId()%>" class="view-menu-btn">View
						Menu</a>
			</div>
			<%
				}
			} else {
			%>
			<p>No restaurants available at the moment.</p>
			<%
			}
			%>
		</div>
	</body>
</html>
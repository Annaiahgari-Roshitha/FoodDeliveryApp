<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.foodDelivery.model.Order" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Order Confirmation</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/confirmation.css">
</head>
<body>
	<h1>Order Confirmation</h1>
	<%
	Order order = (Order) session.getAttribute("order");
	if(order != null) {
	%>
	<div class="order-details">
		<p>Thank you for your order</p>
		<p>
			Order ID:
			<%=order.getOrderId() %></p>
		<p>
			Total Amount: &#8377;
			<%=order.getTotalAmount() %></p>
		<p>
			Status:
			<%=order.getStatus() %></p>
		<p>
			Payment Method:
			<%=order.getPaymentMethod() %></p>
			
	</div>
	<%
	}
	%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Checkout</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css">
</head>
<body>
	<h2>Checkout</h2>
	<form action="${pageContext.request.contextPath}/Checkout"" method="post">
		<label for="address">Delivery Address: </label>
		<textarea id="address" name="address" required></textarea><br><br>
		
		<label>Payment Method:</label>
		<select name="paymentMethod">
			<option value="Online">Credit Card</option>
			<option value="Online">Debit Card</option>
			<option value="Cash">Cash on Delivery</option>
		</select><br><br>
		
		<input type="submit" value="Place Order">
	</form>
</body>
</html>
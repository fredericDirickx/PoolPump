<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/stylePump.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Pump on Of</title>
</head>
<body>

<div class="container">
    <h1>PoolPump</h1>
    <form action="${pageContext.request.contextPath}/pumpOnOf" method="post">
        <button  type="submit" > turn ${pumpStatus}</button>
    </form>
</div>

<span>${exceptionMessage}</span>

</body>
</html>
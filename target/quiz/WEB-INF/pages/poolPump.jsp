<html>
<head>
    <link type="text/css" rel="stylesheet" href="/stylePump.css"/>
    <title>Quiz Settings</title>
</head>
<body>

<div class="container">
    <h1>PoolPump</h1>

    <form action="${pageContext.request.contextPath}/pumpOnOf" method="post">
        <button  type="submit" > turn ${pumpStatus}</button>
    </form>
</div>

</body>
</html>
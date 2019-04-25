<html>
    <body>
        <h2>Hello World!</h2>
        <form action="${pageContext.request.contextPath}/User" method="put">
            Name<input type="text" name="nameInput"/>
            Password <input type="password" name="passwordInput"/>
            <input type="submit" value="Enter"/>
        </form>

    <form action="${pageContext.request.contextPath}/admin">
        <input type="submit" value="adminpage"/>
    </form>

    <form action="home.jsp">
        <input type="submit" value="home"/>
    </form>
    </body>
</html>

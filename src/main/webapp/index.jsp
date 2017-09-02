<%@ page import="jetbrains.exodus.entitystore.PersistentEntityStore" %>
<html>
<title>BookShelf</title>
<body>
<h2>Hello World!</h2>
<span>data store : <%= ((PersistentEntityStore)application.getAttribute("store")).getLocation()%></span>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Book List</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div class="container">
    <h2>List of Books</h2>
    <div style="text-align: right; margin-bottom: 10px;">
        <a href="/books/new" class="btn">Add New Book</a>
        <a href="/" class="btn" style="background-color: #7f8c8d;">Home</a>
    </div>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Publication Year</th>
                <th>Author Name</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.title}</td>
                    <td>${book.publicationYear}</td>
                    <td>${book.author.name}</td>
                    <td>
                        <a href="/books/edit/${book.id}" class="btn" style="padding: 5px 10px; font-size: 14px;">Edit</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty books}">
                <tr>
                    <td colspan="5" style="text-align: center;">No books found.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>
</body>
</html>

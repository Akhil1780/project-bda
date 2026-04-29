<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>${book.id == null ? 'Add New Book' : 'Edit Book'}</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div class="container">
    <h2>${book.id == null ? 'Add New Book' : 'Edit Book'}</h2>

    <c:if test="${not empty errorMessage}">
        <div class="global-error">
            ${errorMessage}
        </div>
    </c:if>

    <form:form action="${book.id == null ? '/books' : '/books/update/'.concat(book.id)}" method="post" modelAttribute="book">
        
        <div class="form-group">
            <form:label path="title">Title</form:label>
            <form:input path="title" />
            <form:errors path="title" cssClass="error" />
        </div>

        <div class="form-group">
            <form:label path="publicationYear">Publication Year</form:label>
            <form:input path="publicationYear" type="number" />
            <form:errors path="publicationYear" cssClass="error" />
        </div>

        <div class="form-group">
            <form:label path="author">Author</form:label>
            <form:select path="author.id">
                <form:option value="" label="-- Select Author --" />
                <form:options items="${authors}" itemValue="id" itemLabel="name" />
            </form:select>
            <form:errors path="author" cssClass="error" />
        </div>

        <div>
            <button type="submit" class="btn">${book.id == null ? 'Save Book' : 'Update Book'}</button>
            <a href="/books" class="btn" style="background-color: #7f8c8d;">Cancel</a>
        </div>
    </form:form>
</div>
</body>
</html>

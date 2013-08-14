<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Upload your bot here </title>
</head>
<body>
<h1>Upload your bot here</h1>

<form:form method="post" action="save.html"
        modelAttribute="uploadForm" enctype="multipart/form-data">
    <p>${resultMessage}</p>
    <table id="fileTable">
        <tr>
            <td><input name="files[0]" type="file" /></td>
            <td><form:errors path="files[0]" cssClass="error"></form:errors></td>
        </tr>
    </table>
    <br/><input type="submit" value="Upload" />
</form:form>
<a href="main_page">Try to play</a>
</body>
</html>
<!DOCTYPE html>
<html>
<head>
    <title>Spring Security Example </title>
</head>
<body>

<#assign aDateTime = .now>

${aDateTime?iso_nz("GMT+02")}<br>

<form action="/login" method="post"  >
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><input type="submit" value="Sign In"/></div>
</form>
</body>
</html>
<html xmlns:th="http://www.w3.org/1999/xhtml">
<link><title>File Info</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
<div class="btn-warning">
    <H2 style="color: red" >
        File information web service Your level of access: <#--${HttpServletRequest.getRemouteUser}-->
    </H2>
</div>

<table class="table table-bordered">
    <tr>
        <th>File path</th>  <th>File size</th> <th>Date of modification</th> <th>Text from file</th>
    </tr>

<#list model["fileInfoList"] as fileInfo>
    <tr class="btn-success">
        <td>${fileInfo.filePath}</td> <td>${fileInfo.fileSize}</td><td>${fileInfo.dateModification}</td><td>${fileInfo.fileText}</td>
    </tr>
</#list>
</table>

<form action="/logout" >
    <input type="submit" value="Sign Out"/>
</form>
</body>
</html>
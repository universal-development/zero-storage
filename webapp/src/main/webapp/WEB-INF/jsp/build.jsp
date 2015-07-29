<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Build Info @name@</title>
</head>
<body>
<h1>Build info</h1>
<hr/>
<table border="1">
    <tr><td width="20%;">Project</td><td>@group@ : @name@</td></tr>
    <tr><td>Version</td><td>@version@</td></tr>
    <tr><td>Build</td><td>@build_number@</td></tr>
    <tr><td>Build id</td><td> @build_id@  </td></tr>
    <tr><td>Commit</td><td>@git_commit@</td></tr>
</table>
<hr/>
<p><small>done by Universal Development</small></p>
</body>
</html>
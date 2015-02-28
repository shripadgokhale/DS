<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Enter File Name</title>
</head>
<body>

<h2>Enter File name</h2>
<form method="POST" action="fileinfo">
   <table>
    <tr>
        <td><label >Name</label></td>
        <td><input type='text' name='name' value=''></td>
    </tr>
    
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form>
</body>
</html>
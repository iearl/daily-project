<%@ page contentType="text/html;charset=UTF-8" %>
<HTML>
<HEAD>
    <title>上传文件</title>
</HEAD>
<body>
<div>
    <form action="uploadController/uploadImg" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>姓名</td>
                <td><input name="name" type="text"></td>
                <td>年龄</td>
                <td><input name="age" type="text"></td>
                <td>上传文件</td>
                <td><input name="file" type="file"></td>
                <td><input type="submit" value="上传"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</HTML>

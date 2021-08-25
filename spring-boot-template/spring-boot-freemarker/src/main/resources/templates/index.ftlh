<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Spring Boot Freemarker</title>
</head>
<body>
Date: ${time?date}
<br>
Time: ${time?time}
<br>
<p>Hello, ${name} !</p>
<p>${.now?string("yyyy-MM-dd HH:mm:ss.sss")}</p>
</body>
</html>
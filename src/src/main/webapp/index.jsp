<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hi</title>
</head>
<body>
<h2>Добро пожаловать!</h2>

<h3>Вход</h3>
<form action="/home" method="post">
    <label>Логин:</label>
    <input type="text" name="username" required />
    <br/>

    <label>Пароль:</label>
    <input type="password" name="password" required />
    <br/>

    <button type="submit">Войти</button>
</form>

<p>Тебе сюда если нет аккаунта - <a href="/auth/register">Зарегистрироваться</a></p>
</body>
</html>
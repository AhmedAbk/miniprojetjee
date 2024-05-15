<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update User</title>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.3.1/css/all.min.css" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Open Sans', sans-serif;
            background-color: #f2f2f2;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            background-color: #fff;
            padding: 30px;
            border-radius: 5px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            margin-bottom: 30px;
            color: #6c5ce7;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            font-weight: 600;
            color: #6c5ce7;
        }
        input[type="text"],
        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            font-size: 16px;
        }
        input[type="submit"] {
            background-color: #6c5ce7;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 3px;
            font-size: 16px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #5a5a5a;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Update User</h1>
        <form action="${pageContext.request.contextPath}/UpdateUser" method="post">
            <input type="hidden" name="userId" value="${user.idUser}">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" value="${user.name}">
            </div>
            <div class="form-group">
                <label for="prenom">Prenom:</label>
                <input type="text" id="prenom" name="prenom" value="${user.prenom}">
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${user.email}">
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" value="${user.password}">
            </div>
            <div class="form-group text-center">
                <input type="submit" value="Update">
            </div>
        </form>
    </div>
</body>
</html>
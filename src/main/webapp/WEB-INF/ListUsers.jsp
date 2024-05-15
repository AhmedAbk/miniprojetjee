<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.3.1/css/all.min.css" rel="stylesheet">
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Custom styles for the page */
        .header {
            background-color: #6c5ce7;
            padding: 1rem;
            text-align: center;
        }
        .stats-card {
            margin-bottom: 1rem;
        }
        .stats-card .card-body {
            padding: 1rem;
        }
        .stats-card .card-body .row {
            margin-bottom: 1rem;
        }
        .stats-card .card-body .row .col {
            padding: 0.5rem;
        }
        .stats-card .card-body .row .col h5 {
            font-size: 1.2rem;
            font-weight: bold;
            margin-bottom: 0.5rem;
        }
        .stats-card .card-body .row .col span {
            font-size: 2.5rem;
            font-weight: bold;
        }
        .table {
            margin-top: 2rem;
        }
        .table thead th {
            font-size: 1.2rem;
            font-weight: bold;
            border-bottom: 1px solid #6c5ce7;
        }
        .table tbody tr {
            border-bottom: 1px solid #f2f2f2;
        }
        .table tbody tr:nth-child(2n+1) {
            background-color: #f9f9f9;
        }
        .table tfoot {
            font-size: 1.2rem;
            font-weight: bold;
            background-color: #6c5ce7;
            color: #fff;
        }
        .table tfoot tr {
            border-top: 1px solid #6c5ce7;
        }
    
        .btn {
            border: none;
            border-radius: 0.25rem;
            padding: 0.5rem 1rem;
            font-size: 1.2rem;
            font-weight: bold;
            cursor: pointer;
        }
        .btn-primary {
            background-color: #6c5ce7;
            color: #fff;
        }
        .btn-primary:hover {
            background-color: #5a5a5a;
        }
        .btn-danger {
            background-color: #dc3545;
            color: #fff;
        }
        .btn-danger:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body class="bg-default">
  <div class="main-content">
    <!-- Header Section -->
    <div class="header bg-gradient-primary pb-8 pt-5 pt-md-8">
      <div class="container-fluid">
        <h1 class="text-white">User List</h1>
      </div>
    </div>
    <!-- Stats Card Section -->
    <div class="stats-card">
      <div class="card-body">
        <div class="row">
          <div class="col">
            <h5 class="card-title text-uppercase text-muted mb-0">Total Users</h5>
            <span class="h2 font-weight-bold mb-0">${totalUsers}</span>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h5 class="card-title text-uppercase text-muted mb-0">Users with name starting with 'A'</h5>
            <span class="h2 font-weight-bold mb-0">${usersWithNameN}</span>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h5 class="card-title text-uppercase text-muted mb-0">Users with prenom 'Abdelkader'</h5>
            <span class="h2 font-weight-bold mb-0">${usersWithPrenomChouchen}</span>
          </div>
        </div>
      </div>
    </div>
    <!-- User List Table -->
    <div class="table">
      <table class="table">
          <thead>
              <tr>
                  <th>ID</th>
                  <th>Name</th>
                  <th>Prenom</th>
                  <th>Email</th>
                  <th>Image</th>
                  <th>Password (Hashed)</th>
                  <th>Action</th>
              </tr>
          </thead>
          <tbody>
              <c:forEach var="user" items="${userList}">
                  <tr>
                      <td>${user.idUser}</td>
                      <td>${user.name}</td>
                      <td>${user.prenom}</td>
                      <td>${user.email}</td>
<td><img src="${user.image}" style="width: 100px; height: 100px;"></td>

                      <td>${user.password}</td>
                      <td>
                          <form action="${pageContext.request.contextPath}/AllUsers" method="post">
                              <input type="hidden" name="action" value="delete">
                              <input type="hidden" name="userId" value="${user.idUser}">
                              <button class="btn btn-danger">Delete</button>
                          </form>
                          <form action="${pageContext.request.contextPath}/UpdateUser" method="get">
                              <input type="hidden" name="id" value="${user.idUser}">
                              <button class="btn btn-primary">Update</button>
                          </form>
                      </td>
                  </tr>
              </c:forEach>
          </tbody>
      </table>
    </div>
  </div>
  <!-- Footer Section -->
  <footer class="footer">
    <div class="row align-items-center justify-content-xl-between">
      <div class="col-xl-6 m-auto text-center">
        
      </div>
    </div>
  </footer>
  <!-- Bootstrap JS -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
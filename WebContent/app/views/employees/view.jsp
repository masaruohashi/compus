<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="br.com.compus.models.Employee"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <title>Compus - Visualizar</title>
  <script src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
  <script src="bower_components/jquery/dist/jquery.min.js"></script>
  <link rel="stylesheet" type="text/css" href="bower_components/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="bower_components/font-awesome/css/font-awesome.css">
  <link rel="stylesheet" type="text/css" href="app/assets/stylesheets/admin_panel.css">
  <link rel="stylesheet" type="text/css" href="app/assets/stylesheets/registration.css">
</head>
<body>
  <header class="container-fluid">
    <div class="header-row row">
      <div class="col-sm-7 pull-left">
        <h1 class="logo">COMPUS</h1>
      </div>
      <div class="col-sm-5">
        <span class="current-username pull-right">username</h3>
        <a href="index.html">
          <span class="fa-stack fa-lg">
            <i class="fa fa-circle-thin fa-stack-2x"></i>
            <i class="fa fa-sign-out fa-stack-1x"></i>
          </span>
        </a>
      </div>
    </div>
  </header>
  <div class="container-fluid page-body">
    <div class="row">
      <nav class="col-sm-2">
        <ul class="nav">
          <li class="nav-item">
            <a class="nav-link active" href="${pageContext.request.contextPath }/funcionario"><span>Listagem de Usuários</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath }/funcionario/novo"><span>Cadastro de Usuário</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#"><span>Relatório Geral</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#"><span>Relatório Individual</span></a>
          </li>
        </ul>
      </nav>
      <div class="content col-sm-10">
        <span class="h2">Visualização de Usuário</span>
        <%Employee employee = (Employee) request.getAttribute("employee"); %>
        <hr>
        <div class="row">
          <div class="col-sm-6 col-sm-offset-3">
            <form class="user-form">
              <div class="row">
                <div class="col-sm-3">
                  <label>Nome: </label>
                </div>
                <div class="col-sm-9">
                  <input class="user-input pull-right" type="text" value="<%=employee.getName() %>" disabled>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-3">
                  <label>CPF: </label>
                </div>
                <div class="col-sm-9">
                  <input class="user-input pull-right" type="text" value="<%=employee.getCpf() %>" disabled>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-3">
                  <label>Email: </label>
                </div>
                <div class="col-sm-9">
                  <input class="user-input pull-right" type="text" value="<%=employee.getEmail() %>" disabled>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-3">
                  <label>Telefone: </label>
                </div>
                <div class="col-sm-9">
                  <input class="user-input pull-right" type="text" value="<%=employee.getPhone() %>" disabled>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-3">
                  <label>Endereço: </label>
                </div>
                <div class="col-sm-9">
                  <input class="user-input pull-right" type="text" value="<%=employee.getAddress() %>" disabled>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-3">
                  <label>Função: </label>
                </div>
                <div class="col-sm-9">
                  <input class="user-input pull-right" type="text" value="<%=employee.getRole() %>" disabled>
                </div>
              </div>
              <div class="row form-buttons">
                  <div class="col-sm-5 col-sm-offset-7">
                    <div class="row">
                      <div class="col-sm-6">
                        <a href="funcionario" class="form-button btn btn-warning pull-right">Voltar</a>
                      </div>
                    </div>
                  </div>
                </div>
            </form>
          </div>
        </div>
      </div>
  </div>
</body>
</html>

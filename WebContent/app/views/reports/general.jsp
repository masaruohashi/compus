<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <title>Compus - Relatório Geral</title>
  <script src="../bower_components/bootstrap/dist/js/bootstrap.js"></script>
  <script src="../bower_components/jquery/dist/jquery.min.js"></script>
  <link rel="stylesheet" type="text/css" href="../bower_components/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="../bower_components/font-awesome/css/font-awesome.css">
  <link rel="stylesheet" type="text/css" href="../app/assets/stylesheets/admin_panel.css">
  <link rel="stylesheet" type="text/css" href="../app/assets/stylesheets/registration.css">
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
            <a class="nav-link" href="../funcionario"><span>Listagem de Usuários</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled" href="../funcionario/novo"><span>Cadastro de Usuário</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" href="#"><span>Relatório Geral</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="../relatorio/individual"><span>Relatório Individual</span></a>
          </li>
        </ul>
      </nav>
      <div class="content col-sm-10">
        <span class="h2">Relatório Geral</span>
        <hr>
        <div class="row">
          <div class="col-sm-6 col-sm-offset-3">
            <form class="user-form" method="post">
              <div class="row">
                <div class="col-sm-3">
                  <label>Mês: </label>
                </div>
                <div class="col-sm-9">
                  <% Calendar calendar = Calendar.getInstance(); %>
                  <% int actualMonth = calendar.get(Calendar.MONTH); %>
                  <% int actualYear = calendar.get(Calendar.YEAR); %>
                  <select class="user-input pull-right" name="date">
                    <% for (int i = 11; i >= 0; i--) { %>
                      <% int month = actualMonth - i; %>
                      <% calendar.set(Calendar.MONTH, month); %>
                      <% calendar.set(Calendar.YEAR, actualYear); %>
                      <% String stringMonth = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, Locale.getDefault()); %>
                      <% String date = stringMonth + "/" + calendar.get(Calendar.YEAR); %>
                      <option value=<%=calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR) %>><%=date %></option>
                    <% } %>
                  </select>
                </div>
              </div>
              <div class="row form-buttons">
                <div class="col-xs-6 col-sm-offset-6">
                  <div class="row">
                    <div class="col-sm-5">
                      <a href="#" class="form-button btn btn-warning pull-right">Cancelar</a>
                    </div>
                    <div class="col-sm-7">
                      <!-- <a class="btn btn-primary" href="report_general_result.html">Gerar relatório</a> -->
                      <button type="submit" class="form-button btn btn-primary pull-right">Gerar relatório</button>
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
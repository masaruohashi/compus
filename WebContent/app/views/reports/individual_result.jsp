<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <title>Compus - Relatório Individual</title>
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
            <a class="nav-link" href="../relatorio/geral"><span>Relatório Geral</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" href=""><span>Relatório Individual</span></a>
          </li>
        </ul>
      </nav>
      <div class="content col-sm-10">
        <span class="h2">Relatório Individual</span>
        <hr>
          <div class="content-table">
            <table class="table" align="right">
              <thead>
                <tr>
                  <th>Funcionário</th>
                  <th>Componente</th>
                  <th>Quantidade vendidas</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>John</td>
                  <td>Processador Core i9</td>
                  <td>0</td>
                </tr>
                <tr>
                  <td></td>
                  <td>Processador Core i7</td>
                  <td>1</td>
                </tr>
                <tr>
                  <td></td>
                  <td>Placa-Mãe</td>
                  <td>1</td>
                </tr>
                <tr>
                  <td></td>
                  <td>Memória</td>
                  <td>6</td>
                </tr>
                <tr>
                  <td></td>
                  <td>HD</td>
                  <td>5</td>
                </tr>
              </tbody>
            </table>
          </div>
      </div>
  </div>
</body>
</html>
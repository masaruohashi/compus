<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <title>Compus - Cadastro</title>
  <script src="../bower_components/jquery/dist/jquery.min.js"></script>
  <script src="../bower_components/bootstrap/dist/js/bootstrap.js"></script>
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
            <a class="nav-link active" href="registration.html"><span>Cadastro de Usuário</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="report.html"><span>Relatório Geral</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#"><span>Relatório Individual</span></a>
          </li>
        </ul>
      </nav>
      <div class="content col-sm-10">
        <span class="h2">Cadastro de Usuário</span>
        <hr>
        <div class="row">
          <div class="col-sm-6 col-sm-offset-3">
            <form class="user-form" action="novo" method="post">
              <div class="row">
                <div class="col-sm-3">
                  <label>Nome: </label>
                </div>
                <div class="col-sm-9">
                  <input class="user-input pull-right" type="text" name="name" />
                </div>
              </div>
              <div class="row">
                <div class="col-sm-3">
                  <label>CPF: </label>
                </div>
                <div class="col-sm-9">
                  <input class="user-input pull-right" type="text" name="cpf" />
                </div>
              </div>
              <div class="row">
                <div class="col-sm-3">
                  <label>Email: </label>
                </div>
                <div class="col-sm-9">
                  <input class="user-input pull-right" type="text" name="email" />
                </div>
              </div>
              <div class="row">
                <div class="col-sm-3">
                  <label>Função: </label>
                </div>
                <div class="col-sm-9">
                  <select class="user-input pull-right" name="role">
                    <option value="vendedor">Vendedor</option>
                    <option value="montador">Montador</option>
                    <option value="estoquista">Estoquista</option>
                    <option value="administrador">Administrador</option>
                  </select>
                </div>
              </div>
              <div class="row form-buttons">
                <div class="col-sm-5 col-sm-offset-7">
                  <div class="row">
                    <div class="col-sm-6">
                      <a href="${pageContext.request.contextPath }/funcionario" class="form-button btn btn-warning pull-right">Cancelar</a>
                    </div>
                    <div class="col-sm-6">
                      <input type="submit" value="Enviar" class="form-button btn btn-primary pull-right" />
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
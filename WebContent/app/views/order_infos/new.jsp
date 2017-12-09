<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>Compus - Identifique-se</title>
  <script type="text/javascript" src="bower_components/jquery/dist/jquery.min.js"></script>
  <script type="text/javascript" src="bower_components/jquery/dist/jquery.mask.min.js"></script>
  <script src="bower_components/jquery/dist/masks.js"></script>
  <script src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
  <link rel="stylesheet" type="text/css" href="bower_components/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="bower_components/font-awesome/css/font-awesome.css">
  <link rel="stylesheet" type="text/css" href="app/assets/stylesheets/login.css">
</head>
<body>
  <div class="row">
    <div class="col-sm-4 col-sm-offset-4 login-box">
      <h1 class="logo text-center">COMPUS</h1>
      <hr>
      <form class="form-group" action="identificacao" method="POST">
        <label for="employee_cpf">CPF do Vendedor:</label>
        <input class="cpf form-control" type="text" id="employee_cpf" name="employee_cpf" />
        <br>
        <label for="client_cpf">CPF do Cliente:</label>
        <input class="cpf form-control" type="text" id="client_cpf" name="client_cpf" />
        <br>
        <div class="row">
          <div class="col-sm-12 text-center">
            <input class="btn btn-primary" type="submit" value="Entrar" id="client_cpf" name="client_cpf" />
          </div>
        </div>
      </form>
    </div>
  </div>
  <% if(request.getAttribute("message") != null) { %>
    <div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-labelledby="messageModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="messageModalLabel">Mensagem</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <%= request.getAttribute("message") %>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
          </div>
        </div>
      </div>
    </div>
    <script>$('#messageModal').modal({show: true})</script>
  <% } %>
</body>
</html>
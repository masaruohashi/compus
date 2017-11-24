<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>Compus - Finalizar Pedido</title>
  <script src="bower_components/jquery/dist/jquery.min.js"></script>
  <script src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
  <link rel="stylesheet" type="text/css" href="bower_components/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="bower_components/font-awesome/css/font-awesome.css">
  <link rel="stylesheet" type="text/css" href="app/assets/stylesheets/index.css">
</head>
<body>
  <header>
    <h1 class="logo">COMPUS</h1>
  </header>
  <nav>
    <ul class="nav-list">
      <li class="nav-item"><a href="processadores">Processador</a></li>
      <li class="nav-item"><a href="placas-mae">Placa-Mãe</a></li>
      <li class="nav-item"><a href="memorias">Memória</a></li>
      <li class="nav-item"><a href="hds">HD</a></li>
      <li class="nav-item"><a href="computadores">Computador</a></li>
      <li class="nav-end-request">
        <a href="cart.html">
          <span class="fa-lg">
            <i class="fa fa-shopping-cart"></i>
          </span>
          <span>Carrinho</span>
        </a>
      </li>
    </ul>
  </nav>
  <div class="content">
    <span class="content-header">Resumo do Pedido</span>
    <hr class="content-line" />
    <form>
      <table class="table">
        <thead>
          <tr>
            <th>Nome do Produto</th>
            <th>Preço</th>
            <th class="col-sm-2">Quantidade</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>Processador Intel Core i9 7900x 3.3Ghz</td>
            <td>R$ 4999,99</td>
            <td><input class="form-control" type="number" value="1" min="1" /></td>
          </tr>
          <tr>
            <td>Processador Intel Core i9 7900x 3.3Ghz</td>
            <td>R$ 4999,99</td>
            <td><input class="form-control" type="number" value="1" min="1" /></td>
          </tr>
          <tr>
            <td>Processador Intel Core i9 7900x 3.3Ghz</td>
            <td>R$ 4999,99</td>
            <td><input class="form-control" type="number" value="1" min="1" /></td>
          </tr>
        </tbody>
      </table>
      <div class="row">
        <div class="col-sm-12">
          <div class="pull-right">
            <a href="login.html" class="btn btn-warning">Cancelar</a>
            <a href="login.html" class="btn btn-primary">Finalizar</a>
          </div>
        </div>
      </div>
    </form>
  </div>
</body>
</html>

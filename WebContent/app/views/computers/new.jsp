<%@page import="br.com.compus.models.Hd"%>
<%@page import="br.com.compus.models.Memory"%>
<%@page import="br.com.compus.models.Cpu"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.compus.models.Motherboard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>Compus - Computadores</title>
  <script src="bower_components/jquery/dist/jquery.min.js"></script>
  <script src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
  <link rel="stylesheet" type="text/css" href="bower_components/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="bower_components/font-awesome/css/font-awesome.css">
  <link rel="stylesheet" type="text/css" href="app/assets/stylesheets/index.css">
</head>
<body>
  <header class="container-fluid">
    <h1 class="logo">COMPUS</h1>
  </header>
  <nav>
    <ul class="nav-list">
      <li class="nav-item"><a href="processadores">Processador</a></li>
      <li class="nav-item"><a href="placas-mae">Placa-Mãe</a></li>
      <li class="nav-item"><a href="memorias">Memória</a></li>
      <li class="nav-item"><a href="hds">HD</a></li>
      <li class="nav-item active"><a href="#">Computador</a></li>
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
    <span class="content-header">Montagem de Computador</span>
    <hr class="content-line" />
    <div class="products">
      <div class="row">
        <div class="col-sm-10 col-sm-offset-1">
          <% List<Motherboard> motherboards = (ArrayList<Motherboard>) request.getAttribute("motherboards"); %>
          <% List<Cpu> cpus = (ArrayList<Cpu>) request.getAttribute("cpus"); %>
          <% List<Memory> memories = (ArrayList<Memory>) request.getAttribute("memories"); %>
          <% List<Hd> hds = (ArrayList<Hd>) request.getAttribute("hds"); %>
          <% if(motherboards.isEmpty() || cpus.isEmpty() || memories.isEmpty() || hds.isEmpty()) { %>
            <div class="col-sm-12 text-center">
              <span>Não existem peças suficientes para a montagem!</span>
            </div>
          <% } else { %>
            <form action="computadores" method="POST">
              <div class="form-group">
                <label for="motherboard">Placa-Mãe:</label>
                <select class="form-control" id="motherboard" name="motherboard">
                  <% for(Motherboard motherboard : motherboards) { %>
                    <option value="<%= motherboard.getId() %>">
                      <%= motherboard.getName() %> - <%= motherboard.getFormatedPrice() %>
                    </option>
                  <% } %>
                </select>
                <br />
                <label for="cpu">Processador:</label>
                <select class="form-control" id="cpu" name="cpu">
                  <% for(Cpu cpu : cpus) { %>
                    <option value="<%= cpu.getId() %>">
                      <%= cpu.getName() %> - <%= cpu.getFormatedPrice() %>
                    </option>
                  <% } %>
                </select>
                <br />
                <div class="row">
                  <div class="col-sm-9">
                    <label for="memory">Memória:</label>
                    <select class="form-control col-sm-6" id="memory" name="memory">
                      <% for(Memory memory : memories) { %>
                        <option value="<%= memory.getId() %>">
                          <%= memory.getName() %> - <%= memory.getFormatedPrice() %>
                        </option>
                      <% } %>
                    </select>
                  </div>
                  <div class="col-sm-3">
                    <label for="memory_quantity">Quantidade:</label>
                    <input type="number" class="form-control" id="memory_quantity" min="1" value="1" name="memory_quantity" />
                  </div>
                </div>
                <br />
                <div class="row">
                  <div class="col-sm-9">
                    <label for="hd">HD:</label>
                    <select class="form-control" id="hd">
                      <% for(Hd hd : hds) { %>
                        <option value="<%= hd.getId() %>">
                          <%= hd.getName() %> - <%= hd.getFormatedPrice() %>
                        </option>
                      <% } %>
                    </select>
                  </div>
                  <div class="col-sm-3">
                    <label for="hd_quantity">Quantidade:</label>
                    <input type="number" class="form-control" id="hd_quantity" min="1" value="1" name="hd_quantity" />
                  </div>
                </div>
              </div>
              <input type="submit" class="btn btn-primary pull-right" value="Enviar" />
            </form>
          <% } %>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
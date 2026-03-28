<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%> 
<!DOCTYPE html> 
<html lang="ru"> 
<head> 
<link rel="stylesheet" type="text/css" href="css/style.css"> 
<meta charset="UTF-8"> 
 
<!-- Настройка viewport --> 
 <meta name="viewport" content="width=device-width, initial-scale=1"> 
 
<!-- Bootstrap CSS --> 
<link rel="stylesheet" href="css/bootstrap.min.css"> 
 
<title>Управление персоналом</title> 
 
</head> 
<body> 
 
 <div class="container-fluid"> 
 <nav class="navbar navbar-light bg-primary"> 
   <div class="container-fluid"> 
     <a class="navbar-brand" href="#"> 
       <img src="images/employees.png" alt="" width="80" height="80" > 
     </a> 
     <h2>Управление персоналом</h2> 
     </div> 
 </nav> 
 
 <div class="container"> 
  <br><br><br> 
    <div class="list-group text-center py-3 px-3"> 
    <h2>Функции системы</h2> 
    <ul class="list-group list-group-flush"> 
     <li class="list-group-item list-group-item primary"><a href="#">Сотрудники</a> 
     <li class="list-group-item list-group-item info"><a href="#">Должности</a> 
    </ul> 
     </div> 
  <br><br> 
 </div> 
 
 <!-- Footer --> 
 <footer style="background: #d7d7d7 ; " class="page-footer font
small black"> 
   <!-- Copyright --> 
   <div class="footer-copyright text-center py-3 px-3" >
   Демонстрационное веб-приложение JavaEE © 2023 Copyright: 
     <a href="https://dolsoft.com/"> Dolsoft.com</a> 
   </div>  
   <button type="button" class="btn btn-primary" data-toggle="popover" title="Сообщение" data-content="Ура, Bootstrap 5 работает">Поднеси ко мне курсор 
</button>
   <!-- Copyright --> 
 </footer> 
  <!-- Footer --> 
</div> 
<!-- jQuery --> 
<script src="js/jquery-3.6.4.js"></script> 
<!-- Bootstrap JS + Popper JS -->  
<script defer src="js/bootstrap.bundle.min.js"></script> 
</body> 
</html>
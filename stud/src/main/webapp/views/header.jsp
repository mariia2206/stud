<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%> 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<!-- Настройка viewport --> 
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<style>
    .navbar-custom {
        background: linear-gradient(135deg, #1e3c72 0%, #2a5298 100%);
        padding: 15px 0;
        margin-bottom: 20px;
        box-shadow: 0 4px 12px rgba(0,0,0,0.1);
    }
    .navbar-container {
        display: flex;
        align-items: center;
        justify-content: center;
        position: relative;
        max-width: 1200px;
        margin: 0 auto;
        padding: 0 20px;
    }
    .navbar-icon {
        position: absolute;
        left: 20px;
    }
    .navbar-icon img {
        width: 60px;
        height: 60px;
        border-radius: 10px;
        transition: transform 0.3s ease;
    }
    .navbar-icon img:hover {
        transform: scale(1.05);
    }
    .navbar-title {
        margin: 0;
        color: white;
        font-weight: bold;
        font-size: 32px;
        text-shadow: 2px 2px 4px rgba(0,0,0,0.3);
        letter-spacing: 2px;
        text-align: center;
    }
    @media (max-width: 768px) {
        .navbar-title { font-size: 24px; }
        .navbar-icon img { width: 45px; height: 45px; }
    }
</style>
</head> 
<body> 
<!-- Header --> 
<nav class="navbar-custom"> 
    <div class="navbar-container">
        <div class="navbar-icon">
            <img src="images/employees.png" alt="Логотип" 
                 onerror="this.src='data:image/svg+xml,%3Csvg xmlns=\'http://www.w3.org/2000/svg\' width=\'60\' height=\'60\' viewBox=\'0 0 24 24\' fill=\'white\'%3E%3Cpath d=\'M12 3L1 9l11 6 11-6-11-6zm0 11.5L3.5 10.5 12 15l8.5-4.5L12 14.5zm0 4L3.5 14.5 12 19l8.5-4.5L12 18.5z\'/%3E%3C/svg%3E'">
        </div>
        <h1 class="navbar-title">Университет</h1>
    </div>
</nav> 
<!-- /Header --> 
</body> 
</html>
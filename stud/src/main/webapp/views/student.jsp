<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Студенты</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
<style>
    .btn-sync {
        background: linear-gradient(135deg, #1e3c72 0%, #2a5298 100%);
        border: none;
        color: white;
        padding: 10px 30px;
        font-size: 16px;
        font-weight: bold;
        border-radius: 25px;
        transition: all 0.3s ease;
        cursor: pointer;
    }
    .btn-sync:hover {
        background: linear-gradient(135deg, #16325e 0%, #1e3c72 100%);
        transform: translateY(-2px);
        box-shadow: 0 5px 15px rgba(0,0,0,0.2);
    }
</style>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/header.jsp" />
    <div class="container-fluid">
        <div class="row justify-content-start">
            <div class="col-12 border bg-light px-4" style="overflow-x: auto;">
                <h3>Список студентов</h3>
                <table class="table" style="min-width: 1200px;">
                    <thead>
                        <tr>
                            <th>Код</th><th>Фамилия</th><th>Имя</th><th>Отчество</th>
                            <th>Дата рождения</th><th>Телефон</th><th>Эл.почта</th>
                            <th>Группа</th><th>Редактировать</th><th>Удалить</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="student" items="${students}">
                            <tr>
                                <td>${student.id}</td><td>${student.lastName}</td>
                                <td>${student.firstName}</td><td>${student.patronymic}</td>
                                <td>${student.birthDate}</td><td>${student.phone}</td>
                                <td>${student.email}</td><td>${student.group.groupName}</td>
                                <td><a href="editStudent?id=${student.id}">Редактировать</a></td>
                                <td><a href="deleteStudent?id=${student.id}" onclick="return confirm('Удалить студента ${student.lastName} ${student.firstName}?')">Удалить</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        
        <div class="row justify-content-start mt-4">
            <div class="col-12 border px-4">
                <form method="POST" action="student">
                    <h3>Новый студент</h3>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="mb-3"><label>Фамилия</label><input type="text" name="lastname" class="form-control" required></div>
                            <div class="mb-3"><label>Имя</label><input type="text" name="firstname" class="form-control" required></div>
                            <div class="mb-3"><label>Отчество</label><input type="text" name="patronymic" class="form-control"></div>
                        </div>
                        <div class="col-md-6">
                            <div class="mb-3"><label>Дата рождения</label><input type="date" name="birthDate" class="form-control" required></div>
                            <div class="mb-3"><label>Телефон</label><input type="text" name="phone" class="form-control" required></div>
                            <div class="mb-3"><label>Email</label><input type="email" name="email" class="form-control" required></div>
                            <div class="mb-3">
                                <label>Группа</label>
                                <select name="groupId" class="form-control">
                                    <option value="">Выберите группу</option>
                                    <c:forEach var="group" items="${groups}">
                                        <option value="${group.id}">${group.groupName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn-sync">Добавить</button>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="/views/footer.jsp" />
</div>
<script src="js/jquery-3.6.4.js"></script>
<script defer src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
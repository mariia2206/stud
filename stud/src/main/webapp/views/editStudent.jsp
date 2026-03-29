<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Редактирование студента</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/header.jsp" />
    <div class="container-fluid">
        <div class="row justify-content-start">
            <div class="col-8 border bg-light px-4">
                <h3>Список студентов</h3>
                <table class="table">
                    <thead>
                        <tr><th>Код</th><th>Фамилия</th><th>Имя</th><th>Отчество</th><th>Дата рождения</th><th>Телефон</th><th>Email</th><th>Группа</th></tr>
                    </thead>
                    <tbody>
                        <c:forEach var="student" items="${students}">
                            <tr>
                                <td>${student.id}</td><td>${student.lastName}</td><td>${student.firstName}</td>
                                <td>${student.patronymic}</td><td>${student.birthDate}</td>
                                <td>${student.phone}</td><td>${student.email}</td><td>${student.group.groupName}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            
            <div class="col-4 border px-4">
                <form method="POST" action="editStudent">
                    <h3>Редактирование студента</h3>
                    <br>
                    
                    <input type="hidden" name="id" value="${studentEdit.id}">
                    
                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Код студента</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" readonly value="${studentEdit.id}">
                        </div>
                    </div>
                    
                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Фамилия</label>
                        <div class="col-sm-8">
                            <input type="text" name="lastname" class="form-control" value="${studentEdit.lastName}" required>
                        </div>
                    </div>
                    
                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Имя</label>
                        <div class="col-sm-8">
                            <input type="text" name="firstname" class="form-control" value="${studentEdit.firstName}" required>
                        </div>
                    </div>
                    
                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Отчество</label>
                        <div class="col-sm-8">
                            <input type="text" name="patronymic" class="form-control" value="${studentEdit.patronymic}">
                        </div>
                    </div>
                    
                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Дата рождения</label>
                        <div class="col-sm-8">
                            <input type="date" name="birthDate" class="form-control" value="${studentEdit.birthDate}" required>
                        </div>
                    </div>
                    
                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Телефон</label>
                        <div class="col-sm-8">
                            <input type="text" name="phone" class="form-control" value="${studentEdit.phone}" required>
                        </div>
                    </div>
                    
                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Email</label>
                        <div class="col-sm-8">
                            <input type="email" name="email" class="form-control" value="${studentEdit.email}" required>
                        </div>
                    </div>
                    
                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Группа</label>
                        <div class="col-sm-8">
                            <select name="groupId" class="form-control">
                                <option value="">Выберите группу</option>
                                <c:forEach var="group" items="${groups}">
                                    <option value="${group.id}" ${studentEdit.group.id == group.id ? 'selected' : ''}>
                                        ${group.groupName}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    
                    <p>
                        <button type="submit" class="btn btn-primary">Сохранить</button>
                        <a href="student" class="btn btn-secondary">Отмена</a>
                    </p>
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
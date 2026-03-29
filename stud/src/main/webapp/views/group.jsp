<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Группы</title>
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
                <h3>Список групп</h3>
                <table class="table" style="min-width: 900px;">
                    <thead>
                        <tr>
                            <th>Код</th><th>Наименование</th><th>Факультет</th>
                            <th>Курс</th><th>Вид обучения</th><th>Редактировать</th><th>Удалить</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="group" items="${groups}">
                            <tr>
                                <td>${group.id}</td><td>${group.groupName}</td>
                                <td>${group.faculty}</td><td>${group.course}</td>
                                <td>${group.studyType}</td>
                                <td><a href="editGroup?id=${group.id}">Редактировать</a></td>
                                <td><a href="deleteGroup?id=${group.id}" onclick="return confirm('Удалить группу ${group.groupName}? Все студенты потеряют связь!')">Удалить</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        
        <div class="row justify-content-start mt-4">
            <div class="col-12 border px-4">
                <form method="POST" action="group">
                    <h3>Новая группа</h3>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="mb-3"><label>Наименование</label><input type="text" name="groupName" class="form-control" required></div>
                            <div class="mb-3"><label>Факультет</label>
                                <select name="faculty" class="form-control" required>
                                    <option value="Факультет информационных технологий">Факультет информационных технологий</option>
                                    <option value="Факультет экономики и управления">Факультет экономики и управления</option>
                                    <option value="Инженерный факультет">Инженерный факультет</option>
                                    <option value="Факультет гуманитарных наук">Факультет гуманитарных наук</option>
                                    <option value="Юридический факультет">Юридический факультет</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="mb-3"><label>Курс</label>
                                <select name="course" class="form-control" required>
                                    <option value="1">1</option><option value="2">2</option><option value="3">3</option>
                                    <option value="4">4</option><option value="5">5</option>
                                </select>
                            </div>
                            <div class="mb-3"><label>Вид обучения</label>
                                <select name="studyType" class="form-control" required>
                                    <option value="Очное">Очное</option><option value="Заочное">Заочное</option>
                                    <option value="Очно-заочное">Очно-заочное</option><option value="Дистанционное">Дистанционное</option>
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Редактирование группы</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/header.jsp" />
    <div class="container-fluid">
        <div class="row justify-content-start">
            <div class="col-8 border bg-light px-4">
                <h3>Список групп</h3>
                <table class="table">
                    <thead><tr><th>Код</th><th>Наименование</th><th>Факультет</th><th>Курс</th><th>Вид обучения</th></tr></thead>
                    <tbody>
                        <c:forEach var="group" items="${groups}">
                            <tr>
                                <td>${group.id}</td><td>${group.groupName}</td>
                                <td>${group.faculty}</td><td>${group.course}</td><td>${group.studyType}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            
            <div class="col-4 border px-4">
                <form method="POST" action="editGroup">
                    <h3>Редактирование группы</h3>
                    <input type="hidden" name="id" value="${groupEdit.id}">
                    
                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Код группы</label>
                        <div class="col-sm-8"><input type="text" class="form-control" readonly value="${groupEdit.id}"></div>
                    </div>
                    
                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Наименование</label>
                        <div class="col-sm-8"><input type="text" name="groupName" class="form-control" value="${groupEdit.groupName}" required></div>
                    </div>
                    
                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Факультет</label>
                        <div class="col-sm-8">
                            <select name="faculty" class="form-control" required>
                                <option value="Факультет информационных технологий" ${groupEdit.faculty == 'Факультет информационных технологий' ? 'selected' : ''}>Факультет информационных технологий</option>
                                <option value="Факультет экономики и управления" ${groupEdit.faculty == 'Факультет экономики и управления' ? 'selected' : ''}>Факультет экономики и управления</option>
                                <option value="Инженерный факультет" ${groupEdit.faculty == 'Инженерный факультет' ? 'selected' : ''}>Инженерный факультет</option>
                                <option value="Факультет гуманитарных наук" ${groupEdit.faculty == 'Факультет гуманитарных наук' ? 'selected' : ''}>Факультет гуманитарных наук</option>
                                <option value="Юридический факультет" ${groupEdit.faculty == 'Юридический факультет' ? 'selected' : ''}>Юридический факультет</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Курс</label>
                        <div class="col-sm-8">
                            <select name="course" class="form-control" required>
                                <option value="1" ${groupEdit.course == 1 ? 'selected' : ''}>1</option>
                                <option value="2" ${groupEdit.course == 2 ? 'selected' : ''}>2</option>
                                <option value="3" ${groupEdit.course == 3 ? 'selected' : ''}>3</option>
                                <option value="4" ${groupEdit.course == 4 ? 'selected' : ''}>4</option>
                                <option value="5" ${groupEdit.course == 5 ? 'selected' : ''}>5</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="mb-3 row">
                        <label class="col-sm-4 col-form-label">Вид обучения</label>
                        <div class="col-sm-8">
                            <select name="studyType" class="form-control" required>
                                <option value="Очное" ${groupEdit.studyType == 'Очное' ? 'selected' : ''}>Очное</option>
                                <option value="Заочное" ${groupEdit.studyType == 'Заочное' ? 'selected' : ''}>Заочное</option>
                                <option value="Очно-заочное" ${groupEdit.studyType == 'Очно-заочное' ? 'selected' : ''}>Очно-заочное</option>
                                <option value="Дистанционное" ${groupEdit.studyType == 'Дистанционное' ? 'selected' : ''}>Дистанционное</option>
                            </select>
                        </div>
                    </div>
                    
                    <p>
                        <button type="submit" class="btn btn-primary">Сохранить</button>
                        <a href="group" class="btn btn-secondary">Отмена</a>
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
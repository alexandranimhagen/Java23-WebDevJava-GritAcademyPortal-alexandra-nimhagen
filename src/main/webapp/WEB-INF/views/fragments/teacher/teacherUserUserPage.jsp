<main>
<%@ include file="teacherUserNavbar.jsp" %>

<h4>Teacher User</h4>

<c:if test="${param.showCoursesForTeacher != null || param.teacherChooserSubmit != null}">
    <%@ include file="../misc/teacherChooser.jsp" %>
</c:if>

<c:if test="${param.showCoursesForStudent != null || param.studentChooserSubmit != null}">
    <%@ include file="../misc/studentChooser.jsp" %>
</c:if>

<%@ include file="../misc/dataTable.jsp" %>


</main>
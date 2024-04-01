<form action="/userPage" method="post">
<select id="teacherChooserChoiceId" name="teacherChooserChoiceId">
    <c:forEach items="${chooserData}" var="dataPunkt">
        <option value="${dataPunkt[0]}">${dataPunkt[1]}</option>
    </c:forEach>
</select>
    <input type="submit" id="teacherChooserSubmit" name="teacherChooserSubmit" value="teacherChooser">
    <button onclick=location.href='/userPage'>Go Back</button>
</form>
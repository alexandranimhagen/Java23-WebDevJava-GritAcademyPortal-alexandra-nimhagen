<form action="/userPage" method="post">
<select id="studentChooserChoiceId" name="studentChooserChoiceId">
    <c:forEach items="${chooserData}" var="dataPunkt">
        <option value="${dataPunkt[0]}">${dataPunkt[1]}</option>
    </c:forEach>
</select>
    <input type="submit" id="studentChooserSubmit" name="studentChooserSubmit" value="studentChooser">
    <button onclick=location.href='/userPage'>Go Back</button>
</form>
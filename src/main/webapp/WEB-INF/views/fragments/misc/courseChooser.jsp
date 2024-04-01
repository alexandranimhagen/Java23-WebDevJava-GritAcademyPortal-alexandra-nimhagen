<form action="/userPage" method="post">
<select id="courseChooserChoiceId" name="courseChooserChoiceId">
    <c:forEach items="${chooserData}" var="dataPunkt">
        <option value="${dataPunkt[0]}">${dataPunkt[1]}</option>
    </c:forEach>
</select>
    <input type="submit" id="courseChooserSubmit" name="courseChooserSubmit" value="courseChooser">
    <button onclick=location.href='/userPage'>Go Back</button>
</form>
(function () {
    // Ищем форму по имени
    const form = document.forms.userProfile;
    const userId = window.location.search.split("=")[1];

        form.addEventListener("submit", function (event) {
        event.preventDefault();
        var ownerId = document.getElementById('hiddenVisitorId').value;
        if (userId===ownerId){
            let body = {};
            for (let element of form.elements) {
                body[element.name] = element.value;
            }
            console.log(body);
            // Делаем запрос на сервер
            fetch(`/user/`+ userId + `/update`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                body: JSON.stringify(body)
            })

                // Получаем из ответа JSON
                .then(res => res.json())
                .then(res => alert(JSON.stringify(res)))
                // Обрабатываем ошибки с сервера
                .catch(error => alert(error))
        }
        else {
            alert("Access denied!");
            document.getElementById('submit').disabled = true;
        }


    })



})()
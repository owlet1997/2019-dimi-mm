const form = document.forms.changepassword;
const pictureForm = document.forms.picture;

var newPasswd = document.getElementById("confirm-new-password");

var id = document.getElementById("hiddenId");

id.value = window.location.search.split("=")[1];

    form.addEventListener("submit", function (event) {
    event.preventDefault();
        var ownerId = document.getElementById('hiddenVisitorId').value;
        if (id.value===ownerId){
            let body = {};

            if (!(form["newPassword"].value===form["confirm-password"].value)){
                alert("Entered passwd don't match!");
                newPasswd.innerHTML = "";
            }

            for (let element of form.elements) {
                if (!(element.name === "confirm-password")&&!(element.name === ""))
                    body[element.name] = element.value;
            }
            console.log(body);

            fetch(`/user/`+ id.value + `/change-passwd`,{
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                body: JSON.stringify(body)
            })
                // Получаем из ответа JSON
                .then(res => res.json())
                .then(res => {
                    console.log(JSON.stringify(res));
                    alert("Пароль изменен успешно!");
                    for (let element of form.elements) {
                        if (!(element.name === "id"))
                            element.value = "";
                    }
                })
                // Обрабатываем ошибки с сервера
                .catch(error => {
                    alert("Старый пароль введен неверно!");
                    console.log(error)})}
        else {
            alert("Access denied!");
            for (let element of form.elements) {
                if (!(element.name === "id"))
                    element.value = "";
            }
            document.getElementById('passwd-submit').disabled = true;
        }
})

    pictureForm.addEventListener("submit", function (event) {
    event.preventDefault();
    var input = $("#uploadimage");
    const fd = new FormData();

    fd.append('img', input.prop('files')[0]);
    console.log(fd);
    fetch(`/user/`+ id.value + `/add-picture`,{
        method: 'PUT',
        body: fd
    })
        // Получаем из ответа JSON
        .then(res => res.json())
        .then(res => {
            console.log(JSON.stringify(res));
            alert("Картинка изменена успешно!");
        })
        // Обрабатываем ошибки с сервера
        .catch(error => {
            alert("Ошибка!");
            console.log(error)})

})

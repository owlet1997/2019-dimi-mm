// приём данных со страницы авторизации
(function () {
    var form = document.forms.signIn;

    form.addEventListener("submit", function (event) {
        event.preventDefault();
        let body = {};

        for (let element of form.elements) {
            if (!(element.name === "")){
                body[element.name] = element.value;
            }
        }
        console.log(body);

        fetch(`/login?username=`+body["username"]+`&password=`+body["password"],{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(body)
        })
            // Получаем из ответа JSON
            .then(res => res.json())
            .then(res => {
                console.log(JSON.stringify(res));
                alert("Вход выполнен успешно!");
                window.location.assign("/");
            })
            // Обрабатываем ошибки с сервера
            .catch(error => {
                alert("Данные введены неверно!");
                console.log(error)})
         // window.location.assign("/");

    })
})()


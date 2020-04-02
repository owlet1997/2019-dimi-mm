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

        fetch(`/log-in`,{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(body)
        })
            // Получаем из ответа JSON
            .then(res => res.json())
            .then(res => {

                console.log(JSON.stringify(res))})
            // Обрабатываем ошибки с сервера
            .catch(error => {
                console.log(error)})
        // window.location.assign("/log-in");

    })
})


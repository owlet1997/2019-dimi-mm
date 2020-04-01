// приём данных со страницы авторизации
 /*(function () {

    const username = document.getElementById("form-sign-in-username").value;

    const password = document.getElementById("form-sign-in-password").value;
    //var text = document.querySelector("input");

    console.log(username + " " + password);



    fetch(`/log-in`,{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        data: username, password,
        body: JSON.stringify(body)
    })
        .then(res => res.json())
        .then(res => {

            console.log(JSON.stringify(res))})
        // Обрабатываем ошибки с сервера
        .catch(error => {
            console.log(error)})
}); */

/*var user = {
    username: document.getElementById("form-sign-in-username").valur
} */

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


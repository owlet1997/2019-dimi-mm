// приём данных со страницы регистрации
var template = _.template(
    $('script.template').html()
);

var container = document.querySelector('.register');

const form = document.forms.register;

form.addEventListener("submit", function (event) {

    event.preventDefault();
    let body = {};

    for (let element of form.elements) {
        if (!(element.name === "")){
            body[element.name] = element.value;
        }
    }
    console.log(body);


fetch(`/register`,{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(body)
    })
        // Получаем из ответа JSON
        .then(res => {
            if (confirm("Регистрация прошла успешно! Перейти на страницу входа?")){
                window.location.assign("/login");
            } else {
                for (let element of form.elements) {
                    if (!(element.name === "")){
                        element.value = "";
                    }
                }
            }
        })
        // Обрабатываем ошибки с сервера
        .catch(error =>
            alert("Ошибка!"))

})
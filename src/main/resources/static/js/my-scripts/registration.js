// приём данных со страницы регистрации
var template = _.template(
    $('script.template').html()
);
var container = document.querySelector('.page');

var form = document.forms.register;

form.addEventListener("submit", function (event) {

    event.preventDefault();
    let body = {};

    for (let element of form.elements) {
        if (!(element.name === "")){
            body[element.name] = element.value;
        }
    }

    fetch(`/register`,{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(body)
    })
        // Получаем из ответа JSON
        .then(res => res.json())
        .then(res => {
            while (container.firstChild) {
                container.removeChild(container.firstChild);
            }
            container.append(template(res));
            console.log(JSON.stringify(res))})
        // Обрабатываем ошибки с сервера
        .catch(error => {
            console.log(error)})
    // window.location.assign("/log-in");

})
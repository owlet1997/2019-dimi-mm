(function () {
    const form = document.forms.addevent;
    // Слушаем, когда на форму подпишутся
    form.addEventListener("submit", function (event) {
        event.preventDefault();
        let body = {};

        for (let element of form.elements) {
            if (!(element.name === "")){
                body[element.name] = element.value;
            }
        }
        console.log(body);

        // Делаем запрос на сервер
        fetch(`/add-event`,{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(body)
        })
            // Получаем из ответа JSON
            .then(res => res.json())
            .then(res => alert(JSON.stringify(res)))
            // Обрабатываем ошибки с сервера
            .catch(error => console.log(error))
        window.location.assign("/map");
    })

})()
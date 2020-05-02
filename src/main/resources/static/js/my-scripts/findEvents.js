(function () {
    // Ищем форму по имени
    const form = document.forms.find;
    _.templateSettings.variable = 'item';

    var template = _.template(
        $('script.template').html()
    );

    var templateEmpty = _.template(
        $('script.templateEmpty').html()
    );

    // Слушаем, когда на форму подпишутся
    form.addEventListener("submit", function (event) {
        event.preventDefault();
        // const userId = window.location.pathname.split("/")[2];
        var params = [];
        for (let element of form.elements) {
            if (!(element.name === "")){
                params.push(element.value);
                console.log(element.name + " " + element.value);
            }
        }
        console.log(params);

        var container = document.querySelector('.js-events');

        // Делаем запрос на сервер
        fetch(`/api/events?city=`+ params[0] + '&dateStart=' + params[1] + '&type=' + params[2], {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
        })
            // Получаем из ответа JSON
            .then(res => res.json())
            .then(res =>
            {
                if (res.length>0)
                {
                    while (container.firstChild) {
                    container.removeChild(container.firstChild);
                    }
                    res.forEach(function (element) {
                        console.log(element);
                        $('.js-events').append(template(element));
                    })
                }
                else{
                        while (container.firstChild) {
                            container.removeChild(container.firstChild);
                        }
                        var answer = { name: "Мероприятия не найдены! Попробуйте изменить параметры запроса"};
                        $('.js-events').append(templateEmpty(answer));
                    }


            })
            // Обрабатываем ошибки с сервера
            .catch(error => alert(error))

    })
})()
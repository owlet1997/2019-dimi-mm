(function () {
    _.templateSettings.variable = 'item';

    var templateEvent = _.template(
        $('script.templateEvent').html()
    );

    var templateItem = _.template(
        $('script.templateItem').html()
    );

    var templateTitle = _.template(
        $('script.templateTitle').html()
    );
    var container = document.getElementById('items');

    var buttonEvents = document.getElementById('all-events');

    var buttonItems = document.getElementById('all-items');

    const userId = window.location.search.split("=")[1];
    console.log(userId);

    fetch(`/api/users/`+userId,{
        method: 'GET',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    })
        // Получаем из ответа JSON
        .then(res => res.json())
        .then(res => {
            console.log(res);
            $('.nav-pills').append(templateTitle(res));
        })
        // Обрабатываем ошибки с сервера
        .catch(error => console.log(error))


    fetch(`/api/user/`+userId+`/events`,{
        method: 'GET',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    })
        // Получаем из ответа JSON
        .then(res => res.json())
        .then(res => {
            console.log(res);
            res.forEach(function (element) {
                $('.items').append(templateEvent(element));
            })
        })
        // Обрабатываем ошибки с сервера
        .catch(error => console.log(error))

    buttonEvents.onclick = function (event) {
        event.preventDefault();

        fetch(`/api/user/`+userId+`/events`,{
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            }
        })
            // Получаем из ответа JSON
            .then(res => res.json())
            .then(res => {
                console.log(res);

                while (container.firstChild) {
                    container.removeChild(container.firstChild)
                }
                res.forEach(function (element) {

                    $('.items').append(templateEvent(element));

                })
            })
            // Обрабатываем ошибки с сервера
            .catch(error => console.log(error))
    }

    buttonItems.onclick = function (event) {
        event.preventDefault();

        fetch(`/api/user/`+userId+`/items`,{
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            }
        })
            // Получаем из ответа JSON
            .then(res => res.json())
            .then(res => {
                console.log(res);

                while (container.firstChild) {
                    container.removeChild(container.firstChild)
                }
                res.forEach(function (element) {
                    $('.items').append(templateItem(element));
                })
            })
            // Обрабатываем ошибки с сервера
            .catch(error => console.log(error))
    }

})()
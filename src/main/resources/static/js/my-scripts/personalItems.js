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
    var templateTitleLine = _.template(
        $('script.templateTitleLine').html()
    );

    var templateEmpty = _.template(
        $('script.templateEmpty').html()
    );

    var container = document.getElementById('items');

    var buttonEvents = document.getElementById('all-events');

    var buttonEventsCreator = document.getElementById('all-events-creator');

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


    fetch(`/api/user/`+userId+`/events/guest`,{
        method: 'GET',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    })
        // Получаем из ответа JSON
        .then(res => res.json())
        .then(res => {
            console.log(res);
            if (res.length>0){
                res.forEach(function (element) {
                    $('.items').append(templateEvent(element));
                })
            }
            else {
                var answer = { name: "Нет мероприятий для посещения"};
                $('.items').append(templateEmpty(answer));
            }

        })
        // Обрабатываем ошибки с сервера
        .catch(error => console.log(error))

    buttonEvents.onclick = function (event) {
        event.preventDefault();
        var containerTitle = document.getElementById('templateTitleClass');

        while (containerTitle.firstChild) {
            containerTitle.removeChild(containerTitle.firstChild)
        }
        var titleElement = { name: "Мероприятия для посещения"};

        $('.templateTitleClass').append(templateTitleLine(titleElement));

        fetch(`/api/user/`+userId+`/events/guest`,{
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
                if (res.length>0){
                    res.forEach(function (element) {
                        $('.items').append(templateEvent(element));
                        var eventItem = document.getElementById('del-event-img'+element.id);
                        eventItem.onclick = function (event) {
                            event.preventDefault();
                            if (confirm("Вы точно передумали идти?")){
                                fetch(`/api/event-visit?eventId=`+ element.id,{
                                    method: 'POST',
                                    headers: {
                                        'Content-Type': 'application/json;charset=utf-8'
                                    }
                                })
                                    // Получаем из ответа JSON
                                    .then(res => res.json())
                                    .then(res => {
                                        console.log(res);
                                        window.location.reload();
                                    })
                                    .catch(error => console.log(error))
                            }
                        }
                    })
                }
                else {
                    var answer = { name: "Нет посещенных мероприятий"};
                    $('.items').append(templateEmpty(answer));
                }
            })
            // Обрабатываем ошибки с сервера
            .catch(error => console.log(error))
    }

    buttonEventsCreator.onclick = function (event) {
        event.preventDefault();
        var containerTitle = document.getElementById('templateTitleClass');

        while (containerTitle.firstChild) {
            containerTitle.removeChild(containerTitle.firstChild)
        }
        var titleElement = { name: "Организованные мероприятия"};

        $('.templateTitleClass').append(templateTitleLine(titleElement));

        fetch(`/api/user/`+userId+`/events/creator`,{
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
                if (res.length>0){
                    res.forEach(function (element) {
                        $('.items').append(templateEvent(element));
                        document.getElementById('del-event-img'+element.id).hidden = true;
                    })
                }
                else {
                    var answer = { name: "Нет созданных мероприятий"};
                    $('.items').append(templateEmpty(answer));
                }
            })
            // Обрабатываем ошибки с сервера
            .catch(error => console.log(error))
    }

    buttonItems.onclick = function (event) {
        event.preventDefault();
        var containerTitle = document.getElementById('templateTitleClass');

        while (containerTitle.firstChild) {
            containerTitle.removeChild(containerTitle.firstChild)
        }
        var titleElement = { name: "Избранные площадки"};

        $('.templateTitleClass').append(templateTitleLine(titleElement));


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
                if (res.length>0){
                    res.forEach(function (element) {
                        $('.items').append(templateItem(element));
                        var eventItem = document.getElementById('del-item-img'+element.id);
                        eventItem.onclick = function (event) {
                            event.preventDefault();
                            if (confirm("Вы точно хотите убрать площадку из избранных?")){
                                fetch(`/api/item-visit?itemId=`+ element.id,{
                                    method: 'POST',
                                    headers: {
                                        'Content-Type': 'application/json;charset=utf-8'
                                    }
                                })
                                    // Получаем из ответа JSON
                                    .then(res => res.json())
                                    .then(res => {
                                    console.log(res);
                                    window.location.reload();
                                    })
                                    .catch(error => console.log(error))
                            }
                        }
                    })
                }
                else {
                    var answer = { name: "Нет избранных площадок"};
                    $('.items').append(templateEmpty(answer));
                }
            })
            // Обрабатываем ошибки с сервера
            .catch(error => console.log(error))
    }

})()
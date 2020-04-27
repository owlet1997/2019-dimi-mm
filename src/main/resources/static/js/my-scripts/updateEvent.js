const eventId = window.location.search.split("=")[1];

_.templateSettings.variable = 'item';

var templateEventForm = _.template(
    $('script.templateEventForm').html()
);

var templateEventItem = _.template(
    $('script.items-list-template').html()
);

document.getElementById('hiddenId').value = eventId;

fetch(`/api/events/`+eventId, {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json;charset=utf-8'
    }
})
    .then(res => res.json())
    .then(res => {
        console.log(res);
        $('.form-container').append(templateEventForm(res));
        console.log("форма добавлена");
        res.itemsList.forEach(function (element) {
            $('.event-items').append(templateEventItem(element));
            var item = document.getElementById('del-item-img'+element.eventItem.id);
            item.onclick = function (event) {
                    event.preventDefault();
                    if (confirm("Вы точно хотите удалить площадку?")){
                        fetch(`/api/items/`+ element.eventItem.id,{
                            method: 'DELETE',
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
        });
        console.log("Плошадки добавлены");

        const selectCity = document.querySelector('.js-city');
        const selectEventType = document.querySelector('.js-event-types');

        fetch(`/api/cities/` + res.city.abbrev, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            }
        })
            .then(res => res.json())
            .then(res => {
                res.forEach(function (element) {
                    console.log(element);
                    const node = '<option value="' + element.abbrev + '">' + element.name + '</option>';
                    selectCity.insertAdjacentHTML("beforeend", node);
                    $(".js-city").selectpicker("refresh");
                })
            })
            .catch(error => console.log(error));

            fetch(`/api/event-types/` + res.type.id, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json;charset=utf-8'
                    }
                })
                    .then(res => res.json())
                    .then(res => {
                        res.forEach(function (element) {
                            console.log(element);
                            const node = '<option value="' + element.id + '">' + element.type + '</option>';
                            selectEventType.insertAdjacentHTML("beforeend", node);
                            $(".js-event-types").selectpicker("refresh");
                        })
                    })
                    .catch(error => alert(error));

    })
    .then(res => {
        const itemForm = document.forms.additem;

        const selectSpeaker = document.querySelector('.js-speaker');
        fetch(`/api/users/`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            }
        })
            .then(res => res.json())
            .then(res => {
                res.forEach(function (element) {
                    const node = '<option value="'+ element.id + '">'+ element.name + '</option>';
                    selectSpeaker.insertAdjacentHTML("beforeend",node);
                    $(".js-speaker").selectpicker("refresh");
                })
            })
            .catch(error => alert(error));

        itemForm.addEventListener("submit", function (event) {
            event.preventDefault();
            let body = {};

            for (let element of itemForm.elements) {
                if (!(element.name === "")){
                    body[element.name] = element.value;
                }
            }
            console.log(body);

            fetch(`/add-event-item`,{
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                body: JSON.stringify(body)
            })
                // Получаем из ответа JSON
                .then(res => res.json())
                .then(res => {
                    $('.event-items').append(itemstemplate(res));
                    console.log(JSON.stringify(res));
                    alert("Элемент события добавлено успешно!");
                    for (let element of itemForm.elements) {
                        if (!(element.name === "parent"))
                            element.value = "";
                    }
                })
                // Обрабатываем ошибки с сервера
                .catch(error => console.log(error))
        })})
    .catch(err => console.log(err))

var form = document.forms.updateevent;

form.addEventListener("submit", function (event) {
    event.preventDefault();

    var body = {};
    body["id"] = eventId;

    for (let element of form.elements) {
        if (!(element.name===""))
        body[element.name] = element.value;
    }
    console.log(body);
    // Делаем запрос на сервер
    fetch(`/api/events`, {
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
            alert("Мероприятие изменено успешно!");
        })
        // Обрабатываем ошибки с сервера
        .catch(error => alert(error))

})
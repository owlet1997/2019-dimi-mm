(function () {
    const form = document.forms.addevent;

    _.templateSettings.variable = 'item';

    var eventtemplate = _.template(
        $('script.event-template').html()
    );

    var itemstemplate = _.template(
        $('script.items-list-template').html()
    );

    var button = document.getElementById('add-item-button');

    // Слушаем, когда на форму подпишутся
    form.addEventListener("submit", function (event) {
        event.preventDefault();
        let body = {};

        for (let element of form.elements) {
            if (!(element.name === "")){
                body[element.name] = element.value;
            }
        }
        // console.log(body);

        // Делаем запрос на сервер
        fetch(`/add-event`,{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(body)
        })

            .then(res => res.json())
            .then(res => {
                console.log(JSON.stringify(res));
                document.getElementById("add-event").style.display = "none";
                $('.event').append(eventtemplate(res));
                document.getElementById('hiddenId').value = res["id"];
                alert("Событие добавлено успешно!");
                document.getElementById('add-item-event').style.display = "block";
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
                })}

    )
                        // Обрабатываем ошибки с сервера
            .catch(error => console.log(error))

    })



})()
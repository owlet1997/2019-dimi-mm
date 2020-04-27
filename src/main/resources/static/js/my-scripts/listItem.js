(function () {

    const eventId = window.location.search.split("=")[1];

    _.templateSettings.variable = 'item';

    var templateEventTitle = _.template(
        $('script.templateEventTitle').html()
    );

    var templateEventSidebar = _.template(
        $('script.templateEventSidebar').html()
    );

    var templateEventHeader = _.template(
        $('script.templateEventHeader').html()
    );

    var templateEventBody = _.template(
        $('script.templateEventBody').html()
    );

    fetch(`/api/events/`+eventId, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    })
        .then(res => res.json())
        .then(res => {
            console.log(res);
            $('.event-head').prepend(templateEventTitle(res));
            $('.event-sidebar').prepend(templateEventSidebar(res));
            $('.event-header').append(templateEventHeader(res));
            $('.event-body').append(templateEventBody(res));
            $.ajax({
                dataType: "json",
                url: '/download',
                success: function (data) {
                    if (data == null) {
                        document.profile_pic.src = "icons/person.png";
                    } else {
                        document.profile_pic.src = data;
                    }
                },
                error: function (status) {
                    console.log(status)
                }
            });

            var deleteButton = document.getElementById('event-delete'+res.id);
            var deleteContainer = document.querySelector('.button-container');

            var cancelButton = document.getElementById('event-cancel'+res.id);
            var cancelContainer = document.querySelector('.button-container-cancel');
            var updateContainer = document.querySelector('.button-update-container');

            var owner = document.getElementById('hidden-owner-id').value;

            console.log("Owner id = " + owner);
            console.log("Creator id = " + res.creator.id);
            if (owner==res.creator.id){
                deleteContainer.hidden = false;
                updateContainer.hidden = false;
                deleteButton.onclick = function (event) {
                    if (confirm("Вы уверены, что хотите удалить событие? Действие нельзя отменить!")){
                        fetch(`/api/events/`+res.id,{
                            method: 'DELETE',
                            headers: {
                                'Content-Type': 'application/json;charset=utf-8'
                            }
                        })
                            // Получаем из ответа JSON
                            .then(res => res.json())
                            .then(res => {
                                alert("Событие удалено!");
                                console.log(res);
                                window.location.assign("/");

                            })
                            .catch(error => console.log(error))
                    }

                }

                cancelContainer.hidden = false;
                var question = "Вы уверены, что хотите сделать мероприятие действующим?";
                cancelButton.onclick = function (event) {
                    if (res.cancelled===false){
                        question = "Вы уверены, что хотите отменить мероприятие?"
                    }
                    if (confirm(question)){
                        fetch(`/api/events/`+res.id+`/cancel`,{
                            method: 'PUT',
                            headers: {
                                'Content-Type': 'application/json;charset=utf-8'
                            }
                        })
                            // Получаем из ответа JSON
                            .then(res => res.json())
                            .then(res => {
                                alert("Готово, вы восхитительны!");
                                console.log(res);
                                window.location.reload();
                            })
                            .catch(error => console.log(error))
                    }
                }
            }

            var items = res["itemsList"];
            console.log(items);
            if (res.cancelled===true){
                items.forEach(function (element) {
                    document.forms.namedItem('featureItem'+element.eventItem.id).hidden = true;
                    document.forms.visitevent.hidden = true;
                })

            } else{
                items.forEach(function (element) {
                    console.log('featureItem'+element.eventItem.id);
                    const formName = document.forms.namedItem('featureItem'+element.eventItem.id);
                    formName.addEventListener("submit", function (event) {
                        event.preventDefault();
                        let newbody = {};

                        for (let element of formName.elements) {
                            if (!(element.name === "")){
                                newbody[element.name] = element.value;
                            }
                        }
                        console.log(newbody);

                        fetch(`/api/item-visit?itemId=`+newbody["itemId"],{
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/json;charset=utf-8'
                            }
                        })
                            // Получаем из ответа JSON
                            .then(res => res.json())
                            .then(res => {
                                console.log(res);
                                let buttonItem = document.getElementById('item-featured' + newbody["itemId"]);
                                buttonItem.src = visitItem(res);
                                // buttonItem.value = visitItem(res);
                            })
                            .catch(error => console.log(error))

                    })
                })
                let buttonVisit = document.getElementById('item-visited');
                const form = document.forms.visitevent;

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
                    fetch(`/api/event-visit?eventId=`+body["eventId"],{
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json;charset=utf-8'
                        }
                    })
                        // Получаем из ответа JSON
                        .then(res => res.json())
                        .then(res => {
                            buttonVisit.value = visitEvent(res);
                            console.log(buttonVisit.value);

                        })
                        .catch(error => console.log(error))

                })
            }



        })
        .catch(error => alert(error));
}())
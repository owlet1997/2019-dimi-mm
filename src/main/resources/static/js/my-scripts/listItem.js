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

    let userId = document.getElementById('hidden-id').value;

    fetch(`/api/events/`+eventId+`/user/`+userId, {
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

            const form = document.forms.visitevent;
            const formFeat = document.forms.featureItem;

            var container = document.querySelector('.event-header');
            var containerSecond = document.querySelector('.event-body');

            form.addEventListener("submit", function (event) {
                event.preventDefault();
                var userFormId = document.getElementById('hidden-user-id');
                userFormId.value = document.getElementById('hidden-id').value;

                let body = {};

                for (let element of form.elements) {
                    if (!(element.name === "")){
                        body[element.name] = element.value;
                    }
                }
                console.log(body);

                // Делаем запрос на сервер
                fetch(`/api/event-visit?userId=`+body["userId"] + `&eventId=`+body["eventId"],{
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json;charset=utf-8'
                    }
                })
                    // Получаем из ответа JSON
                    .then(res => res.json())
                    .then(res => {
                        while (container.firstChild) {
                            container.removeChild(container.firstChild);
                        }
                        $('.event-header').append(templateEventHeader(res));
                    })
                    .catch(error => console.log(error))

            })

            formFeat.addEventListener("submit", function (event) {
                event.preventDefault();
                var userFormId = document.getElementById('hidden-user-featur-id');
                userFormId.value = document.getElementById('hidden-id').value;

                let newbody = {};

                for (let element of formFeat.elements) {
                    if (!(element.name === "")){
                        newbody[element.name] = element.value;
                    }
                }
                console.log(newbody);

                // Делаем запрос на сервер
                fetch(`/api/item-visit?itemId=`+newbody["itemId"] + `&userId=` + newbody["userId"],{
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json;charset=utf-8'
                    }
                })
                    // Получаем из ответа JSON
                    .then(res => res.json())
                    .then(res => {
                        while (containerSecond.firstChild) {
                            containerSecond.removeChild(containerSecond.firstChild);
                        }
                        $('.event-body').append(templateEventBody(res));
                    })
                    .catch(error => console.log(error))

            })
        })
        .catch(error => alert(error));
}())
(function () {

    const eventId = window.location.search.split("=")[1];
    console.log(eventId);
    var userId = [];

    _.templateSettings.variable = 'item';

    var template = _.template(
        $('script.templateList').html()
    );

    var templateEvent = _.template(
        $('script.templateEvent').html()
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
            $('.event-head').append(templateEvent(res));
            $('.event-body').append(templateEventBody(res));
        })
        .catch(error => alert(error));

}())
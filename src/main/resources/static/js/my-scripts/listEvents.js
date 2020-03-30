_.templateSettings.variable = 'item';

var template = _.template(
    $('script.template').html()
);

fetch(`/api/events/`, {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json;charset=utf-8'
    }
})
    .then(res => res.json())
    .then(res => {
        res.forEach(function (element) {
            console.log(element);
            $('.js-events').append(template(element));
        })
    })
    .catch(error => alert(error));


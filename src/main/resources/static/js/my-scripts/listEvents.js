_.templateSettings.variable = 'item';

var template = _.template(
    $('script.template').html()
);
var templateEmpty = _.template(
    $('script.templateEmpty').html()
);

fetch(`/api/events/`, {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json;charset=utf-8'
    }
})
    .then(res => res.json())
    .then(res => {
        if (res.length>0){
            res.forEach(function (element) {
            $('.js-events').append(template(element));
        })}
        else{
            var answer = { name: "Нет мероприятий!"};
            $('.js-events').append(templateEmpty(answer));
        }

    })
    .catch(error => alert(error));


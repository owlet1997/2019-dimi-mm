const selectCity = document.querySelector('.js-city');
const selectEventType = document.querySelector('.js-event-types');

fetch(`/api/cities/`, {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json;charset=utf-8'
    }
})
    .then(res => res.json())
    .then(res => {
        res.forEach(function (element) {
            console.log(element);
            const node = '<option value="'+ element.abbrev + '">'+ element.name + '</option>';
            selectCity.insertAdjacentHTML("beforeend",node);
            $(".js-city").selectpicker("refresh");

        })
    })
    .catch(error => alert(error));

fetch(`/api/event-types/`, {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json;charset=utf-8'
    }
})
    .then(res => res.json())
    .then(res => {
        res.forEach(function (element) {
            console.log(element);
            const node = '<option value="'+ element.id + '">'+ element.type + '</option>';
            selectEventType.insertAdjacentHTML("beforeend",node);
            $(".js-event-types").selectpicker("refresh");
        })
    })
    .catch(error => alert(error));
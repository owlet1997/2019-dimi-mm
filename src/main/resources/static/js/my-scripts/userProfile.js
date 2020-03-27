(function () {

    const userId = window.location.search.split("=")[1];
    console.log(userId);
    _.templateSettings.variable = 'item';

    var template = _.template(
        $('script.template').html()
    );

    fetch( `/api/users/`+userId, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    })
        .then(res => res.json())
        .then(res =>
            $('.user-info').prepend(template(res))
        )
        .catch(error => alert(error));

}())
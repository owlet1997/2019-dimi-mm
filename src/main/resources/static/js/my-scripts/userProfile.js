(function () {

    const userId = window.location.search.split("=")[1];
    console.log(userId);
    _.templateSettings.variable = 'item';

    var template = _.template(
        $('script.template').html()
    );

    var templateTitle = _.template(
        $('script.templateTitle').html()
    );

    var templateAuth = _.template(
        $('script.templateHeaderAuth').html()
    );

    var templateUnauth = _.template(
        $('script.templateHeaderUnauth').html()
    );
    var container = document.querySelector('.nav-header');

    fetch( `/api/users/`+userId, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    })
        .then(res => res.json())
        .then(res =>{
            if (!(res.valueOf()==="null")){
                while (container.firstChild) {
                    container.removeChild(container.firstChild);
                }
                $('.nav-header').append(templateAuth(res));
            }
            $('.user-info').prepend(template(res));
            $('.nav-pills').prepend(templateTitle(res))
        })
        .catch(error => alert(error));

}())
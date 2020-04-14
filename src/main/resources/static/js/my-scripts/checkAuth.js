    _.templateSettings.variable = 'item';

    var templateAuth = _.template(
        $('script.templateHeaderAuth').html()
    );

    var templateUnauth = _.template(
        $('script.templateHeaderUnauth').html()
    );
    var container = document.querySelector('.nav-header');

    fetch(`/check-auth`,{
        method: 'GET',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    })
        // Получаем из ответа JSON
        .then(res => res.json())
        .then(res => {
            if (!(res.valueOf()==="null")){
                while (container.firstChild) {
                    container.removeChild(container.firstChild);
                }
                $('.nav-header').append(templateAuth(res));
            }
        })
        .catch(error => {
            console.log(error);
            while (container.firstChild) {
                container.removeChild(container.firstChild);
            }
            $('.nav-header').append(templateUnauth);
        })
// Обрабатываем ошибки с сервера


async function getJson() {
    _.templateSettings.variable = 'item';

    var template = _.template(
        $('script.template').html()
    );
    var templateEmpty = _.template(
        $('script.templateEmpty').html()
    );
    let arr = {type: "FeatureCollection", features: []};

    return await fetch(`/api/events/`, {
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
            console.log(res);
            res.forEach(function (element) {
                var split = [];
                split.push(parseFloat(element.coord.point.split(',')[1]));
                split.push(parseFloat(element.coord.point.split(',')[0]));
                var temp = {
                    type: "Feature", id: element.id,
                    geometry: {type: "Point", coordinates: split},
                    properties: {
                        balloonContentHeader: "<b><a href=#collapse" + element.id + ">" + element.name + "</a></b>",
                        balloonContentBody: "<p>" + element.city.name + "</p>",
                        balloonContentFooter: "<font size=1>Тип: </font> <strong>" + element.type.type + "</strong>",
                        clusterCaption: "<strong>"+ element.name +"</strong>",
                        hintContent: _.identity(dateWrite(element.timeStart))
                    }
                };
                arr.features.push(temp);
            })
        })
        .then(res => {

            return arr;
        })
        .catch(error => alert(error));

}
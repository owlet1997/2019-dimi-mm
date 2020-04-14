
function getJson(){
    let arr =  {"type": "FeatureCollection", "features":[]};

    fetch(`/api/events/`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    })
        .then(res => res.json())
        .then(res => {
            res.forEach(function (element) {
                var split = [];
                split.push(parseFloat(element.coord.point.split(',')[0]));
                split.push(parseFloat(element.coord.point.split(',')[1]));
                var temp = {type:"Feature", id: element.id,
                    geometry: {type:"Point", coordinates: split },
                    properties: {
                        balloonContentHeader:"<b><a href=#collapse" + element.id +">Ссылка на элемент в списке</a></b>",
                        balloonContentBody: "<p>"+ element.name + "</p>",
                        balloonContentFooter: "<font size=1>Информация предоставлена: </font> <strong>этим балуном</strong>",
                        clusterCaption: "<strong><s>Еще</s> одна</strong> метка",
                        hintContent: _.identity(dateWrite(element.timeStart))}};
                arr.features.push(temp);
            })
        })
        .then(res => {
            console.log(JSON.stringify(arr));
            return JSON.stringify(arr); })
        .catch(error => alert(error));


}


ymaps.ready(init);

function init () {
    var myMap = new ymaps.Map('map', {
            center: [53.5303, 49.3461],
            zoom: 10
        }, {
            searchControlProvider: 'yandex#search'
        }),
        objectManager = new ymaps.ObjectManager({
            // Чтобы метки начали кластеризоваться, выставляем опцию.
            clusterize: true,
            // ObjectManager принимает те же опции, что и кластеризатор.
            gridSize: 32,
            clusterDisableClickZoom: true
        });

    // Чтобы задать опции одиночным объектам и кластерам,
    // обратимся к дочерним коллекциям ObjectManager.
    objectManager.objects.options.set('preset', 'islands#greenDotIcon');
    objectManager.clusters.options.set('preset', 'islands#greenClusterIcons');
    myMap.geoObjects.add(objectManager);

    var arr =  { type: "FeatureCollection", features:[]};

    fetch(`/api/events/`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    })
        .then(res => res.json())
        .then(res => {
            res.forEach(function (element) {
                var temp = {type: "Feature", id: element.id,
                    geometry: { type:"Point", coordinates: element.coord.point },
                    properties: {balloonContentHeader:"<b><a href=#collapse" + element.id +">Ссылка на элемент в списке</a></b>",
                        balloonContentBody: "<p>element.name</p>",
                        balloonContentFooter: "<font size=1>Информация предоставлена: </font> <strong>этим балуном</strong>",
                        clusterCaption: "<strong><s>Еще</s> одна</strong> метка",
                        hintContent:_.identity(dateWrite(element.timeStart))}};
                arr.features.push(temp);
            })
            objectManager.add(JSON.stringify(arr));
        })
        .catch(error => alert(error));

}


//
// ymaps.ready(init);
//
// function init () {
//     var myMap = new ymaps.Map("map", {
//         center: [53.5303, 49.3461],
//         zoom: 11
//     }, {
//         searchControlProvider: 'yandex#search'
//     });
//
//     $.ajax({
//         url:`/api/events/`,
//         method: 'GET',
//         headers: {
//         'Content-Type': 'application/json;charset=utf-8'
//     }
//     }).done(function(data) {
//         data.forEach(function (element) {
//
//             var myPlacemark = new ymaps.Placemark(element.coord.point, {
//                 // Чтобы балун и хинт открывались на метке, необходимо задать ей определенные свойства.
//                 balloonContentHeader: element.name,
//                 balloonContentBody: "Содержимое <em>балуна</em> метки",
//                 balloonContentFooter: "Подвал",
//                 hintContent: "Хинт метки"
//             });
//
//             myMap.geoObjects.add(myPlacemark);
//         })
//     });
//
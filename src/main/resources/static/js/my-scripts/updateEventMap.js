ymaps.ready(init);
var address = document.getElementById('hidden-address');
var formcoord = document.getElementById('hidden-coords');
function init() {
    const eventId = window.location.search.split("=")[1];

    fetch(`/api/events/`+eventId, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    })
        .then(res => res.json())
        .then(res => {
            var myPlacemark,
                myMap = new ymaps.Map('map', {
                    center: getCoord(res.city.point),
                    zoom: 11
                }, {
                    searchControlProvider: 'yandex#search'
                });
            console.log("Координаты события: "+res.coord.point);
            myPlacemark = new ymaps.Placemark(getCoord(res.coord.point), {
                iconCaption: res.name,
            }, {
                preset: 'islands#violetDotIconWithCaption',
                draggable: true
            });
            console.log(myPlacemark);

            myMap.geoObjects.add(myPlacemark);

            // Слушаем клик на карте.
            myMap.events.add('click', function (e) {
                var coords = e.get('coords');

                // Если метка уже создана – просто передвигаем ее.
                if (myPlacemark) {
                    myPlacemark.geometry.setCoordinates(coords);
                }
                // Если нет – создаем.
                else {
                    myPlacemark = createPlacemark(coords);
                    myMap.geoObjects.add(myPlacemark);
                    // Слушаем событие окончания перетаскивания на метке.
                    myPlacemark.events.add('dragend', function () {
                        getAddress(myPlacemark.geometry.getCoordinates());
                    });
                }
                formcoord.value = coords;
                getAddress(coords);
            });

            // Создание метки.
            function createPlacemark(coords) {
                return new ymaps.Placemark(coords, {
                    iconCaption: 'поиск...'
                }, {
                    preset: 'islands#violetDotIconWithCaption',
                    draggable: true
                });
            }

            // Определяем адрес по координатам (обратное геокодирование).
            function getAddress(coords) {
                myPlacemark.properties.set('iconCaption', 'поиск...');
                ymaps.geocode(coords).then(function (res) {
                    var firstGeoObject = res.geoObjects.get(0);

                    myPlacemark.properties
                        .set({
                            // Формируем строку с данными об объекте.
                            iconCaption: [
                                firstGeoObject.getLocalities().length ? firstGeoObject.getLocalities() : firstGeoObject.getAdministrativeAreas(),
                                firstGeoObject.getThoroughfare() || firstGeoObject.getPremise()
                            ].filter(Boolean).join(', '),
                            balloonContent: firstGeoObject.getAddressLine()
                        });
                    address.value = firstGeoObject.getAddressLine();
                });
            }

        })
        .catch(error => console.log(error))

}
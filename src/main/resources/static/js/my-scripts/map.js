ymaps.ready(init);
async function init() {
    var myMap = new ymaps.Map('map', {
            center: [53.5303, 49.3461],
            zoom: 10
        }, {
            searchControlProvider: 'yandex#search'
        }),
        objectManager = new ymaps.ObjectManager({
            clusterize: true,
            gridSize: 32,
            clusterDisableClickZoom: true
        });

    objectManager.objects.options.set('preset', 'islands#greenDotIcon');
    objectManager.clusters.options.set('preset', 'islands#greenClusterIcons');

    console.log("Добавлен ObjectManager")
    myMap.geoObjects.add(objectManager);

    getJson().then((value) => {
        console.log(value);
    objectManager.add(value);
    })

}

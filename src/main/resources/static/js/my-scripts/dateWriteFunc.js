const tdStr = "сегодня";
const tmrStr = "завтра";
const datStr = "послезавтра";

function dateWrite(varDate) {
    var itemDate = moment(varDate);
    itemDate.lang('ru');
    var date = itemDate.format('DD-MM-YYYY');
    var nowMoment = moment().format('DD-MM-YYYY');
    var tom = moment().add('days', 1).format('DD-MM-YYYY');
    var dAT = moment().add('days', 2).format('DD-MM-YYYY');
    if (date === nowMoment) {
        return tdStr + " в " + itemDate.format('HH:mm');
    } else if (date === tom) {
        return tmrStr + " в " + itemDate.format('HH:mm');
    } else if (date === dAT) {
        return datStr + " в " + itemDate.format('HH:mm');
    }
    return date + " в " + itemDate.format('HH:mm');
}

function getDate(varDate) {
    var itemDate = moment(varDate);
    itemDate.lang('ru');
    var date = new Date(itemDate.getFullYear(),itemDate.getMonth(),itemDate.getDate());
    return date;
}

function getTime(varDate) {
    var itemDate = moment(varDate);
    itemDate.lang('ru');
    var date = new('HH:mm');
    return date;
}

function getCoord(varCoord) {
    var coordinates = {};
    coordinates[0] = (varCoord.split(",")[1]);
    coordinates[1] = (varCoord.split(",")[0]);
    console.log(coordinates)
    return coordinates;

}
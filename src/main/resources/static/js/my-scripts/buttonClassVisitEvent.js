// const cancel = "Убрать из избранных";
// const add = "Добавить в избранные";
const cancel = "icons/full-star.png";
const add = "icons/empty-star.png";


const visit = "Посетить";
const refuse = "Отказаться от участия";

function visitItem(varBool) {
    console.log(varBool);

    if (varBool===true){
        return cancel;
    } else if (varBool===false){
        return add;
    }
}

function visitEvent(varBool) {
    console.log(varBool);

    if (varBool===true){
        return refuse;
    } else if (varBool===false){
        return visit;
    }
}
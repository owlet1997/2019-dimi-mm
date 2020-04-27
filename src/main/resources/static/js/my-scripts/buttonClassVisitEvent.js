const cancel = "icons/full-star.png";
const add = "icons/empty-star.png";

const visit = "Посетить";
const refuse = "Отказаться от участия";

const cancelled = "Отменено";
const existed = "";

const buttonCancel = "Отменить мероприятие";
const buttonExist = "Активировать";

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

function isCancelled(varBool) {
    console.log(varBool);
    if (varBool===true){
        return cancelled;
    } else if (varBool===false){
        return existed;
    }
}

function buttonCancelled(varBool) {
    console.log(varBool);
    if (varBool===true){
        return buttonExist;
    } else if (varBool===false){
        return buttonCancel;
    }
}


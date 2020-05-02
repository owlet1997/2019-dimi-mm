function getImage() {
var imgSrc = "icons/person.png";
    $.ajax({
        dataType: "json",
        url: '/download',
        success: function (data) {
            if (data == null) {
                imgSrc = "icons/person.png";
                console.log(imgSrc);
                return imgSrc;
            } else {
                imgSrc = data;
                console.log(imgSrc);
                return imgSrc;
            }
        },
        error: function (status) {
            console.log(status)
        }
    });

}
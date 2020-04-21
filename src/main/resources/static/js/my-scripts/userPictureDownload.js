$(function () {

    $.ajax({
        dataType: "json",
        url: '/download',
        success: function (data) {
            if (data == null) {
                document.profile_pic.src = "icons/person.png";
            } else {
                document.profile_pic.src = data;
            }
        },
        error: function (status) {
            console.log(status)
        }
    })
});
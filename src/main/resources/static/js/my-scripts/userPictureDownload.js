$(function () {
    const userId = window.location.search.split("=")[1];
    $.ajax({
        dataType: "json",
        url: '/download/' + userId,
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
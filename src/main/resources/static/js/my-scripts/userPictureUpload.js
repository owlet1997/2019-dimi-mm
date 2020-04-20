$(function () {

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        var form = $('#add-picture')[0];
        var data = new FormData(form);

        console.log(data);

        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "/api/file/upload",
            data: data,
            processData: false, //prevent jQuery from automatically transforming the data into a query string
            contentType: false,
            cache: false,
            success: (data) => {
                $("#add-picture").text(data);
            },
            error: (e) => {
                $("#add-picture").text(e.responseText);
            }
        });
    });
});
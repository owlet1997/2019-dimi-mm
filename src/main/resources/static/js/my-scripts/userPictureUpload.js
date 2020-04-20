// $(function () {
//
//     form.addEventListener("submit", function(event) {
//         event.preventDefault();
//
//         var form = $('#add-picture')[0];
//         var data = new FormData(form);
//
//         console.log(data);
//
//         $.ajax({
//             type: "POST",
//             enctype: 'multipart/form-data',
//             url: "/api/file/upload",
//             data: data,
//             processData: false, //prevent jQuery from automatically transforming the data into a query string
//             contentType: false,
//             cache: false,
//             success: (data) => {
//                 $("#add-picture").text(data);
//             },
//             error: (e) => {
//                 $("#add-picture").text(e.responseText);
//             }
//         });
//     });
// });


$(document).ready( () => {
    $("#btnSubmit").click((event) => {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        doAjax();
    });

});

function doAjax() {

    // Get form
    // var form = $('#uploadimage')[0];
    // var data = new FormData(form);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/api/file/upload",
        data: data,
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        success: (data) => {
            $("#listFiles").text(data);
        },
        error: (e) => {
            $("#listFiles").text(e.responseText);
        }
    });
}
window.notify = function (message) {
    $.notify(message, {
        position: "right bottom",
        className: "success"
    });
}

window.ajax = function (data, success) {
    $.ajax({
        type: "POST",
        url: "",
        dataType: "json",
        data,
        success: function (response) {
            if (response.redirect) {
                location.href = response.redirect;
            }
            success(response);
        }
    });
}

window.loginForm = function (action, login, password, $error) {
    ajax({action, login, password},
        function (response) {
            if (response["error"]) {
                $error.text(response["error"]);
            }
        });
}

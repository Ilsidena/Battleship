$(".login_button").onclick = function() {
    $(".login_form").css("visibility", "visible");
    $(".signup_form").css("visibility", "hidden");
};

$(".signup_button").onclick = function() {
    $(".signup_form").css("visibility", "visible");
    $(".login_form").css("visibility", "hidden");
};
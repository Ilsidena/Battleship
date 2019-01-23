$('#signup_form').css('display', 'none');
console.log('here');
$('#login_form').css('display', 'none');
console.log('here');

$("#login_button").click(function() {
    $("#login_form").show();
    $("#signup_form").hide();
});

$("#signup_button").click(function() {
    $("#signup_form").show();
    $("#login_form").hide();
});
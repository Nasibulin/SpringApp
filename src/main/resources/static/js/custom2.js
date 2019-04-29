$(document).ready(function() {
    $("#phone").mask("+7 (999) 999-99-99");
});
$(function(){
    //Listen for a click on any of the dropdown items
    $(".custom_list li a").click(function(){
        //Get the value
        var value = $(this).attr("value");
        //Put the retrieved value into the hidden input
        $("input[name='category']").val(value);
    });
});
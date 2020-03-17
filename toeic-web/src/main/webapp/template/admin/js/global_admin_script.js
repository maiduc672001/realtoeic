$(document).ready(function () {
    bindEventCheckAllCheckBox('checkAll');
    enableOrDisableDeleteAll();
})
function bindEventCheckAllCheckBox(id) {
    $('#'+id).on('change',function () {
        if((this).checked()) {
            $(this).closest('table').find('input[type=checkbox]').prop('checked', true);
        }else {
            $(this).closest('table').find('input[type=checkbox]').prop('checked', false);
        }

    })

}
function bindEventCheckAllCheckBox(id) {
    $('#' + id).on('change', function () {
        if ((this).checked) {
            //enable all checkbox
            $(this).closest('table').find('input[type=checkbox]').prop('checked', true);
        } else {
            //disable all checkbox
            $(this).closest('table').find('input[type=checkbox]').prop('checked', false);
        }
    });
}


function checkActivity(timeout, interval, elapsed,logoutUrl,heartBeat) {
    if ($.active) {
        elapsed = 0;
        $.active = false;
        // $.get(heartBeat);
    }


    if (elapsed < timeout) {
        elapsed += interval;
        setTimeout(function() {
            checkActivity(timeout, interval, elapsed,logoutUrl,heartBeat);
        }, interval);
    } else {
        window.parent.location = logoutUrl;
    }
}




function showError(field){
    $($('[name="'+field+'"]').parents('div.control-group')).addClass('error');
}
function gotoUrl(url){
    window.location = url;
    return false;
}

// $(".tutup-otomatis").each(function() {
//     $(this).datepicker();
// });
//
// $(".tutup-otomatis").click(function() {
//     $(".datepicker-days .day").click(function() {
//         $('.datepicker').hide();
//     });
// });


function date_format_full(timestamp) {
    if (timestamp == null) {
        return "";
    }
    var _date = new Date(timestamp);
    var _month = _date.getMonth() + 1;
    var _day = _date.getDate();
    var _hour = _date.getHours();
    var _minute = _date.getMinutes();
    var _second = _date.getSeconds();
    return (_day < 10 ? "0" + _day : _day) + "-"
        + (_month < 10 ? "0" + _month : _month) + "-"
        + _date.getFullYear() + " "
        + (_hour < 10 ? "0" + _hour : _hour) + ":"
        + (_minute < 10 ? "0" + _minute : _minute) + ":"
        + (_second < 10 ? "0" + _second : _second);
}
function date_format(timestamp) {
    if (timestamp == null) {
        return "";
    }
    var _date = new Date(timestamp);
    var _month = _date.getMonth() + 1;
    var _day = _date.getDate();
    return (_day < 10 ? "0" + _day : _day) + "-"
        + (_month < 10 ? "0" + _month : _month) + "-"
        + (_date.getFullYear());
}
function toggleTarget(target){
    $(target).toggle('slow');
}
function openWindow(url){
    return window.open(url, "_blank");
}
function parseToFloat(obj){
    if(obj == undefined || obj == ""){
        return 0;
    }
    var value = parseFloat(obj);
    if(isNaN(value)){
        return 0;
    }
    return value;
}
jQuery.ajaxSetup({
    error: function (xhr, ajaxOptions, thrownError) {
        if(xhr.status == 901){
            alert('Session Anda telah habis, Silahkan login ulang');
            window.top.location = "login";
        }
        if(xhr.status == 403){
            alert('Anda tidak mempunyai Hak Akses');
        }
        // 404, 403
        //alert(xhr.status);
        //alert(thrownError);
    }
});
if (typeof $.fn.dataTable !== 'undefined') {
    $.extend(true, $.fn.dataTable.defaults, {
        "deferLoading": 0,
        'bSort': false,
        'bFilter': false,
        "bProcessing": true,
        "bServerSide": true,
        "bAutoWidth": true,
        "pagingType": "full_numbers",
        "iDisplayLength": 10,
        "aLengthMenu": [[5, 10, 25, 50], [5, 10, 25, 50]],
        "createdRow": function (nRow, aData, index) {
            if (aData["statusData"] != null)
                $(nRow).addClass("status_data_" + aData["statusData"]);
        },
        "aoColumnDefs": [
            /*{ 'bSortable': true, 'aTargets': [1] },
            { 'bSortable': false, 'aTargets': [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11] },*/
        ]
    });
}

if (jQuery().datepicker) {
    $.fn.datepicker.defaults.format = "mm/dd/yyyy";
    $.fn.datepicker.defaults.autoclose = true;
    $('[data-date]').each(function() {
        var _this = $(this);
        _this.datetimepicker({
            
        });
    });
}

// input mask common
var unmask_form, mask_form;
if (jQuery().inputmask) {
    Inputmask.extendDefaults({
        'removeMaskOnSubmit' : true,
        'autounmask': true
    });
    unmask_form = function() {
        $('[data-mask]').each(function() {
            var _this = $(this);
            // console.log(_this);
            _this.inputmask('remove');
            // console.log(_this.val());
        });
    }
    mask_form = function() {
        $('[data-mask]').each(function() {
            var _this = $(this);
            // console.log(_this);

            var _type = _this.data('mask');
            // console.log(_type);
            if (_type == "date") {
                _this.inputmask({
                    mask : '99-99-9999',
                    clearMaskOnLostFocus : true
                });
            } else if (_type == "currency") {
                _this.inputmask("numeric", {
                    // mask : $(this).data('mask'),
                    // clearMaskOnLostFocus : true,
                    // numericInput: true,
                    // prefix : "Rp ",
                    digits: 0,
                    placeholder : '0',
                    groupSeparator : ',',
                    autoGroup : true,
                    removeMaskOnSubmit: true,
                    allowPlus: false,
                    allowMinus: false
                });

            } else if (_type == "numeric") {
                // console.log('masking numeric')
                _this.inputmask("numeric", {
                    // mask : $(this).data('mask'),
                    clearMaskOnLostFocus : true,
                    // numericInput: true,
                    // prefix: "Rp ",
                    radixPoint: ".",
                    digits: 2,
                    placeholder : '0.00',
                    groupSeparator: ',',
                    autoGroup : true
                });
            } else if (_type == "integer") {
                // console.log('masking numeric')
                _this.inputmask("numeric", {
                    clearMaskOnLostFocus : true,
                    digits: 0,
                    placeholder : '0',
                    groupSeparator: ',',
                    autoGroup : true
                });
            } else if (_type == "number") {
                // console.log('masking numeric')
                _this.inputmask("numeric", {
                    mask : '9{15}',
                    clearMaskOnLostFocus : true,
                    greedy: false,
                    rightAlign: false
                });
                // _this.prop("style", "text-align:left");
            } else if (_type == "luas-tanah") {
                // console.log('masking numeric')
                _this.inputmask("numeric", {
                    clearMaskOnLostFocus : true,
                    digits: 2,
                    placeholder : '0.00',
                    groupSeparator: ',',
                    autoGroup : true,
                    allowMinus: false
                });
            } else if (_type == "devisiasi") {
                // console.log('masking numeric')
                _this.inputmask("numeric", {
                    clearMaskOnLostFocus : true,
                    digits: 2,
                    placeholder : '0.00',
                    groupSeparator: ',',
                    autoGroup : true,
                    allowMinus: true
                });
            }else if (_type == "persen") {
                // console.log('masking numeric')
                // _this.inputmask('decimal',{min:1, max:100});
                _this.inputmask('Regex', {
                    regex: "^[0-9][0-9]?$|^100$",
                    digits: 2,
                    groupSeparator: ',',
                    rightAlign: true
                });
            } else {
                _this.inputmask({
                    mask : $(this).data('mask'),
                    clearMaskOnLostFocus : true
                });
            }
        });
    }

    $('[data-mask]').each(function() {
        var _this = $(this);
        // console.log(_this);

        var _type = _this.data('mask');
        // console.log(_type);
        if (_type == "date") {
            _this.inputmask({
                mask : '99-99-9999',
                clearMaskOnLostFocus : true
            });
        } else if (_type == "currency") {
            _this.inputmask("numeric", {
                // mask : $(this).data('mask'),
                // clearMaskOnLostFocus : true,
                // numericInput: true,
                // prefix : "Rp ",
                digits: 0,
                placeholder : '0',
                groupSeparator : ',',
                autoGroup : true,
                removeMaskOnSubmit: true,
                allowPlus: false,
                allowMinus: false
            });

        } else if (_type == "numeric") {
            // console.log('masking numeric')
            _this.inputmask("numeric", {
                // mask : $(this).data('mask'),
                clearMaskOnLostFocus : true,
                // numericInput: true,
                // prefix: "Rp ",
                radixPoint: ".",
                digits: 2,
                placeholder : '0.00',
                groupSeparator: ',',
                autoGroup : true
            });
        } else if (_type == "integer") {
            // console.log('masking numeric')
            _this.inputmask("numeric", {
                clearMaskOnLostFocus : true,
                digits: 0,
                placeholder : '0',
                groupSeparator: ',',
                autoGroup : true
            });
        } else if (_type == "number") {
            // console.log('masking numeric')
            _this.inputmask("numeric", {
                mask : '9{15}',
                clearMaskOnLostFocus : true,
                greedy: false,
                rightAlign: false
            });
            // _this.prop("style", "text-align:left");
        } else if (_type == "luas-tanah") {
            // console.log('masking numeric')
            _this.inputmask("numeric", {
                clearMaskOnLostFocus : true,
                digits: 2,
                placeholder : '0.00',
                groupSeparator: ',',
                autoGroup : true,
                allowMinus: false
            });
        }else if (_type == "devisiasi") {
            // console.log('masking numeric')
            _this.inputmask("numeric", {
                clearMaskOnLostFocus : true,
                digits: 2,
                placeholder : '0.00',
                groupSeparator: ',',
                autoGroup : true,
                allowMinus: true
            });
        } else if (_type == "persen") {
            // console.log('masking numeric')
            // _this.inputmask('decimal',{min:1, max:100});
            _this.inputmask('Regex', {
                regex: "^[0-9][0-9]?$|^100$",
                digits: 2,
                groupSeparator: ',',
                rightAlign: true
            });
        } else {
            _this.inputmask({
                mask : $(this).data('mask'),
                clearMaskOnLostFocus : true
            });
        }
    });
}

if ($.validator) {
    jQuery.extend(jQuery.validator.messages, {
        required: "Tidak Boleh kosong",
        remote: "Please fix this field.",
        email: "Please enter a valid email address.",
        url: "Please enter a valid URL.",
        date: "Please enter a valid date.",
        dateISO: "Please enter a valid date (ISO).",
        number: "Please enter a valid number.",
        digits: "Please enter only digits.",
        creditcard: "Please enter a valid credit card number.",
        equalTo: "Please enter the same value again.",
        accept: "Please enter a value with a valid extension.",
        maxlength: jQuery.validator.format("Please enter no more than {0} characters."),
        minlength: jQuery.validator.format("Please enter at least {0} characters."),
        rangelength: jQuery.validator.format("Please enter a value between {0} and {1} characters long."),
        range: jQuery.validator.format("Please enter a value between {0} and {1}."),
        max: jQuery.validator.format("Please enter a value less than or equal to {0}."),
        min: jQuery.validator.format("Please enter a value greater than or equal to {0}.")
    });


}
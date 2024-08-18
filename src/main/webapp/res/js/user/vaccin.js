function registForm() {
    console.log('registerForm()');

    let form = document.regist_form;
    if (form.fvp_mail.value === '') {
        alert('접종자 메일을 입력해주세요');
        form.fvp_mail.focus();
    } else if (form.fvp_phone.value === '') {
        alert('접종자 연락처를 입력해주세요');
        form.fvp_phone.focus();
    } else if (form.fv_type_no.value === '') {
        alert('백신 타입을 입력해주세요');
        form.fv_type_no.focus();
    } else if (form.fv_location_no.value === '') {
        alert('백신 접종처를 입력해주세요');
        form.fv_location_no.focus();
    } else {
        form.submit();
    }
}
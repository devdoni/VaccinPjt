function adminSignInForm() {
    console.log('adminSignInForm()');

    let form = document.admin_signin_form;

    if (form.am_id.value === '') {
        alert('아이디를 입력해주세요');
        form.am_id.focus();

    } else if (form.am_pw.value === '') {
        alert('비밀번호를 입력해주세요');
        form.am_pw.focus();

    } else {
        form.submit();
    }
}
function adminSignUpForm() {
    console.log('adminSignUpForm()');

    let form = document.admin_signup_form;

    if (form.am_id.value === '') {
        alert('아이디를 입력해주세요');
        form.am_id.focus();

    } else if (form.am_pw.value === '') {
        alert('비밀번호를 입력해주세요');
        form.am_pw.focus();

    } else if (form.am_name.value === '') {
            alert('비밀번호를 입력해주세요');
            form.am_name.focus();

    } else if (form.am_auth_code.value === ''){
        alert('관리자 인증번호를 입력해주세요');
        form.am_auth_code.focus();

    } else {
        form.submit();
    }
}
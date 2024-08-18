function signupForm() {
    console.log('signupForm()');

    let form = document.signup_form;
    if (form.m_id.value === '') {
        alert('아이디를 입력해주세요');
        form.m_id.focus();
    } else if (form.m_pw.value === '') {
        alert('비밀번호를 입력해주세요');
        form.m_pw.focus();
    } else if (form.m_mail.value === '') {
        alert('메일을 입력해주세요');
        form.m_mail.focus();
    } else if (form.m_phone.value === '') {
        alert('전화번호를 입력해주세요');
        form.m_phone.focus();
    } else if (form.sc_number.value === '') {
        alert('주민등록번호를 입력해주세요');
        form.sc_number.focus();
    } else {
        form.submit();
    }
}

function signInForm() {
    console.log('signInForm()');

    let form = document.signin_form;
    if (form.m_id.value === '') {
        alert('아이디를 입력해주세요');
        form.m_id.focus();
    } else if (form.m_pw.value === '') {
        alert('비밀번호를 입력해주세요');
        form.m_pw.focus();
    } else {
        form.submit();
    }
}

function modifyForm() {
    console.log('modifyForm()');

    let form = document.modify_form;
    if (form.m_pw.value === '') {
        alert('비밀번호를 입력해주세요');
        form.m_pw.focus();
    } else if (form.m_mail.value === '') {
        alert('메일을 입력해주세요');
        form.m_mail.focus();
    } else if (form.m_phone.value === '') {
        alert('전화번호를 입력해주세요');
        form.m_phone.focus();
    } else {
        form.submit();
    }
}


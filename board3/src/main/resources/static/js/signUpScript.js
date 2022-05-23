window.onload = () => {
    document.getElementById("userId").focus();
}

/**
 * 유효성 검사
 */
function isUserValid() {

    const form = document.getElementById('form');

    if (!form.userId.value.trim()) {
        alert('로그인시 사용될 ID를 입력해주세요.');
        form.userId.value = '';
        form.userId.focus();
        return false;
    }

    if (!form.userPw.value.trim()) {
        alert('로그인시 사용될 PW를 입력해주세요.');
        form.userPw.value = '';
        form.userPw.focus();
        return false;
    }

    if (!form.userName.value.trim()) {
        alert('고객 성명을 입력해주세요.');
        form.userName.value = '';
        form.userName.focus();
        return false;
    }

    if (!form.userPhone.value.trim()) {
        alert('고객 전화번호를 입력해주세요.');
        form.userPhone.value = '';
        form.userPhone.focus();
        return false;
    }

    if (!form.userBirth.value.trim()) {
        alert('고객 생년월일을 입력해주세요.');
        form.userBirth.value = '';
        form.userBirth.focus();
        return false;
    }
    return true;
}

/**
 * 게시글 등록(생성/수정)
 */
function signUp() {

    if (!isUserValid()) {
        return false;
    }
    const form = document.getElementById('form');

    // const titleValue = $('input[id=title]').val();
    // const contentValue = $('textarea[id=content]').val();

    // const idValue = form.userId.value.trim();
    // const pwValue = form.userPw.value.trim();
    // const nameValue = form.userName.value.trim();
    // const phoneValue = form.userPhone.value.trim();
    // const birthValue = form.userBirth.value.trim();

    // 태그 무시 추후 적용 예정
    // console.log("contentValue let", contentValue);
    // contentValue = contentValue.replace(/<br\/>/ig, "\n");
    // console.log("contentValue replace 1", contentValue);
    // contentValue = contentValue.replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, "");
    // console.log("contentValue replace 2", contentValue);

    const params = {
        userId: form.userId.value.trim(),
        userPw : form.userPw.value.trim(),
        userName : form.userName.value.trim(),
        userPhone : form.userPhone.value.trim(),
        userBirth : form.userBirth.value.trim()
    };

    console.log(params);

    fetch('/user/signUp', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(params)
    }).then(response => {
        if (!response.ok) {
            throw new Error('failed');
        }
        alert('저장되었습니다.');
        location.href = '/';
    }).catch(error => {
        alert('오류가 발생하였습니다.');
    });
}

function goHome() {
    const userId = $('input[id=userId]').val();
    const userPw = $('input[id=userPw]').val();
    const userName = $('input[id=userName]').val();
    const userPhone = $('input[id=userPhone]').val();
    const userBirth = $('input[id=userBirth]').val();
    if (userId.trim().length > 0 || userPw.trim().length > 0 || userName.trim().length > 0 || userPhone.trim().length > 0 || userBirth.trim().length > 0) {
        if (confirm('작성 중인 정보가 삭제됩니다. 이동하시겠습니까?')) {
            location.href = "/";
            return true;
        } else {
            return false;
        }
    }
    location.href = "/";
    return true;
}
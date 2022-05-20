/**
 * 유효성 검사
 */
function isValid() {

    const form = document.getElementById('form');
    const titleValue = form.title.value.trim();
    const contentValue = form.content.value.trim();
    console.log("titleValue", titleValue);
    console.log("contentValue", contentValue);
    if (!form.title.value.trim()) {
        alert('글 제목을 입력해주세요.');
        form.title.value = '';
        form.title.focus();
        return false;
    }

    if (!form.content.value.trim()) {
        alert('글 내용을 입력해주세요.');
        form.content.value = '';
        form.content.focus();
        return false;
    }
    return true;
}

/**
 * 게시글 등록(생성/수정)
 */
function save() {

    if (!isValid()) {
        return false;
    }
    const form = document.getElementById('form');

    const titleValue = $('input[id=title]').val();
    const contentValue = $('textarea[id=content]').val();
    console.log(titleValue);
    console.log(contentValue);
    // 태그 무시 추후 적용 예정
    // console.log("contentValue let", contentValue);
    // contentValue = contentValue.replace(/<br\/>/ig, "\n");
    // console.log("contentValue replace 1", contentValue);
    // contentValue = contentValue.replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, "");
    // console.log("contentValue replace 2", contentValue);

    const params = {
        title: titleValue,
        content: contentValue
    };

    fetch('/board/board', {
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
        location.href = '/boards';
    }).catch(error => {
        alert('오류가 발생하였습니다.');
    });
}
function goBoardList() {
    const title = $('input[id=title]').val();
    const content = $('textarea[id=content]').val();
    if (title.trim().length>0 || content.trim().length>0) {
        if (confirm('작성중인 글이 삭제됩니다. 뒤로가시겠습니까?')) {
            location.href = "/boards";
            return true;
        } else {
            return false;
        }
    }
    location.href = "/boards";
    return true;
}
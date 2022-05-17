/**
 * 유효성 검사
 */
function isValid() {

    const form = document.getElementById('form');

    // const boardTitle = $('input[id=boardTitle]').val();
    // const boardContent = $('textarea[id=boardContent]').val();

    if (!form.title.value.trim()) {
        alert('글 제목을 입력해주세요.');
        form.title.value = '';
        form.title.focus();
        return false;
    }

    if (!form.content.value.trim()) {
        alert('글 제목을 입력해주세요.');
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
    // const boardTitle = $('input[id=boardTitle]').val();
    // const boardContent = $('textarea[id=boardContent]').val();

    const params = {
        boardTitle: form.title.value,
        boardContent: form.content.value,
        boardDeleteYn: 'N'
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
        location.href = '/board/list';

    }).catch(error => {
        alert('오류가 발생하였습니다.');
    });
}

function goBoardList() {
    const boardTitle = $('input[id=boardTitle]').val();
    const boardContent = $('textarea[id=boardContent]').val();
    if (boardTitle.trim().length != 0 || boardContent.trim().length != 0) {
        if (confirm('작성중인 글이 삭제됩니다. 뒤로가시겠습니까?')) {
            location.href = "/board";
            return true;
        } else {
            return false;
        }
    }
    location.href = "/board";
    return true;
}


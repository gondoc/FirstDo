/**
 * 유효성 검사
 */
function isValid() {

    const boardTitle = $('input[id=boardTitle]').val();
    const boardContent = $('textarea[id=boardContent]').val();
    if (boardTitle.trim().length == 0) {
        alert('글 제목을 입력해주세요.');
        return false;
    }

    if (boardContent.trim().length == 0) {
        alert('글 내용을 입력해주세요.');
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

    const boardTitle = $('input[id=boardTitle]').val();
    const boardContent = $('textarea[id=boardContent]').val();

    const params = {
        boardTitle: boardTitle,
        boardContent : boardContent
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


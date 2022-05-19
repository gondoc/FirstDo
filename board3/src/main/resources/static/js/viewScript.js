window.onload = () => {
    findBoard();
}

/**
 * 게시글 조회
 */
function findBoard() {

    const id = `${id}`;

    fetch(`/board/board/${id}`).then(response => {
        if (!response.ok) {
            throw new Error('Request failed...');
        }
        return response.json();

    }).then(json => {
        console.table(json);
        json.boardRegDate = moment(json.boardRegDate).format('YYYY-MM-DD HH:mm:ss');

        Object.keys(json).forEach(key => {
            const elem = document.getElementById(key);
            if (elem) {
                elem.innerText = json[key];
            }
        });

    }).catch(error => {
        alert('게시글 정보를 찾을 수 없습니다.');
        goList();
    });
}

/**
 * 뒤로가기
 */
function goList() {
    location.href = '/board';
}

/**
 * 수정하기
 */
function goUpdate() {
    location.href = `/board/board?id=[[ ${id} ]]`;
}

/**
 * 삭제하기
 */
function del() {

    const id = `${id}`;

    if (!confirm(`게시글을 삭제할까요?`)) {
        return false;
    }

    fetch(`/board/board/${id}`, {
        method: 'DELETE',
        headers: {'Content-Type': 'application/json'},

    }).then(response => {
        if (!response.ok) {
            throw new Error('Request failed...');
        }

        alert('삭제되었습니다.');
        goList();

    }).catch(error => {
        alert('오류가 발생하였습니다.');
    });
}

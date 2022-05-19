/**
 * 페이지 로딩 시점에 실행되는 함수
 */
window.onload = () => {
    findAll();
}

/**
 * 게시글 리스트 조회
 */
function findAll() {

    fetch('/board/board?boardDeleteYn=N').then(response => {
        if (response.ok) {
            console.log("response 로그 {} ", response);
            return response.json();
        }
    }).then(json => {
        console.log("json 로그 ", json);
        let html = '';
        if (!json.length) {
            html = '<tr><td colspan="3">등록된 게시글이 없습니다.</td></tr>';
        } else {
            json.forEach((obj, idx) => {
                html += `
                        <tr>
                        <td>${json.length - idx}</td>
                        <td><a href="/board/board/${obj.id}">${obj.boardTitle}</a></td>
                        <td>${moment(obj.boardRegDate).format('YYYY-MM-DD HH:mm:ss')}</td>
                        </tr>`;
            });
        }
        document.getElementById('boardList').innerHTML = html;
    });
}

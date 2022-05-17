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
        console.log("json 로그 {} ", json);
        let html = '';
        const tbodyData = [];
        if (!json.length) {
            tbodyData.push(`
                    <tr>
                    <td colspan="3">등록된 게시글이 없습니다.</td>
                    </tr>
                `)
            document.querySelector('.table>tbody').innerHTML = tbodyData.join('');
        } else {
            for (const iterator of json) {
                // iterator.boardRegDate = format('YYYY-MM-DD HH:mm');
                tbodyData.push(`
                    <tr>
                    <td>${iterator.id}</td>
                    <td><a href='/board/view/`'{iterator.id}'`'>${iterator.boardTitle}</a></td>
                    <td>${iterator.boardRegDate}</td>
                    </tr>
                `)
            }
            document.querySelector('.table>tbody').innerHTML = tbodyData.join('');
        }
    });
}

// ----= 작업 전 원본
// function findAll() {
//
//     fetch('/board/board?boardDeleteYn=N').then(response => {
//         if (response.ok) {
//             console.log("response 로그 {} ", response);
//             return response.json();
//         }
//     }).then(json => {
//         console.log("json 로그 {} ", json);
//         let html = '';
//         const tbodyData = [];
//         if (!json.length) {
//             tbodyData.push(`
//                     <tr>
//                     <td colspan="3">등록된 게시글이 없습니다.</td>
//                     </tr>
//                 `)
//             document.querySelector('.table>tbody').innerHTML = tbodyData.join('');
//         } else {
//             for (const iterator of json) {
//                 // iterator.boardRegDate = format('YYYY-MM-DD HH:mm');
//                 tbodyData.push(`
//                     <tr>
//                     <td>${iterator.id}</td>
//                     <td><a href='/board/view/`'{iterator.id}'`'>${iterator.boardTitle}</a></td>
//                     <td>${iterator.boardRegDate}</td>
//                     </tr>
//                 `)
//             }
//             document.querySelector('.table>tbody').innerHTML = tbodyData.join('');
//         }
//     });
// }
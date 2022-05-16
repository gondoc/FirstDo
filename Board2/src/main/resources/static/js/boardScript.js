
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

    fetch('/board/board').then(response => {
        if (response.ok) {
            console.log("response 로그 {} ", response);
            return response.json();
        }
    }).then(json => {
        console.log("json 로그 {} ", json);
        let html = '';
        let tbodyData = [];
        if (json.length === 0) {
            html = '<td colspan="3">등록된 게시글이 없습니다.</td>';
        } else {
            for (const iterator of json) {
                tbodyData.push(`
                    <tr>
                    <td>${iterator.id}</td>
                    <td><a href="/board">${iterator.boardTitle}</a></td>
                    <td>${iterator.boardContent}</td>
                    <td>${iterator.boardRegDate}</td>
                    </tr>
                `)
            }
            document.querySelector('.table>tbody').innerHTML = tbodyData.join('');
        }

        // }     console.log(json);
        //     json.forEach((obj, id) => {
        //         html += `
        // 					<tr>
//        <!--                    ${#dates.format(date)}.format("yyyy-MM-dd HH:mm")-->

        // 						<td>${json.length - id}</td>
        // 						<td class="text-left">
        // 							<a th:href='@{/}' value="${json.boardTitle}"></a>
        // 						</td>
        // 						<td>${boardRegDate.format('YYYY-MM-DD HH:mm:ss')}</td>
        // 					</tr>
        // 				`;
        //     });
        // }

        // document.getElementById('boardList').innerHTML = html;
    });
}


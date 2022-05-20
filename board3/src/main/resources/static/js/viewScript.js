function preUpdate(){
    if(confirm("수정하시겠습니까?")==true){
        document.getElementById("preBtn").style.display = "none";
        document.getElementById("delBtn").style.display = "none";
        document.getElementById("updateTitle").style.display = "flex";
        document.getElementById("updateContent").style.display = "flex";

        document.getElementById("cancelBtn").style.display = "inline-block";
        document.getElementById("updateBtn").style.display = "inline-block";

        document.getElementById("title").style.display = "none";
        document.getElementById("content").style.display = "none";
    } else{
        return false;
    }
    return true;
}

/**
 * 게시글 조회
 */
function goUpdate() {

    const id = document.getElementById("id").value;
    console.log(" id  : ", id);
    location.href = "/board/update/" + id;

    fetch(`/board/board/${id}`).then(response => {
        if (!response.ok) {
            throw new Error('Request failed...');
        }
        return response.json();

    }).then(json => {
        console.table(json);
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
    location.href = '/boards';
}

/**
 * 수정하기
//  */
// function goUpdate() {
//     const id = '${id}';
//
//     location.href = `/board/board?id=[[ ${id} ]]`;
// }

/**
 * 삭제하기
 */
function del() {

    if (!confirm('해당 게시물을 삭제하시겠습니까?')) {
        return false;
    }

    // const id = document.getElementById('id');
    // const boardId = id.id.value;

    const id = document.getElementById('id').value;
    const uri = '/board/board/' + id;

    fetch(uri, {
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

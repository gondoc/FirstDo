window.onload = init;

function init() {
  var map = new ol.Map({
    view: new ol.View({
      // 지도 중심 좌표
      center: new ol.geom.Point([127.125443, 37.442613])
        // 구글 좌표계 사용
        .transform('EPSG:4326', 'EPSG:3857').getCoordinates(),
      // 지도 zoom 단계
      zoom: 18,
      // 줌 확대 최대 단계 
      maxZoom: 20,
      // 줌 확대 최소 단계
      minZoom: 10,
      // 지도를 시계방향으로 돌린다.
      rotation: 0.5,
      enableRotation: false
    }),

    // 기본 지도 해제
    layers: [
      // 뷰, style 등을 관리하기 위해 설정함.
      new ol.layer.Tile({
        source: new ol.source.XYZ({ //vworld api 사용
          url: 'http://xdworld.vworld.kr:8080/2d/Base/202002/{z}/{x}/{y}.png',
          //tileGrid: tileGrid
        })
      }),
    ],

    target: 'map'
  })
};

function company() {
  let bbox = [123, 23 , 164, 21]
map.getView().fit(bbox,{option})
}

// function home(){
//   map.setView().setCenter(
//     new ol.geom.Point([127.125443, 37.442613]).transform('EPSG:4326', 'EPSG:3857').getCoordinates()
//     );
//     map.getView().setZoom(parseInt(17));
// }

function reset() {
  map.setView(new View({
    center: [127.125443, 37.442613],
    zoom: 17
  }));
}

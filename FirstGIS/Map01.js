// const container = document.getElementById('popUp');
// const content1 = document.getElementById('popUpContent');

// const home = fromLonLat([37.442635, 127.125395]);
// const company = fromLonLat([37.401492, 127.101236]);
// let map;
// let mapLayer;
// let mapOverlay; 
// let mapView;
// let hover = null;
// const olCenter = ol.proj.fromLonLat([127.101236, 37.401492]);
// const authKey = '413AA14E-9D09-306B-A949-19F7601D475B';
// const olLayer = new ol.layer.Tile({
//   source: new ol.source.XYZ({
//     url: 'http://api.vworld.kr/req/wmts/1.0.0/413AA14E-9D09-306B-A949-19F7601D475B/Base/{z}/{y}/{x}.png'
//   })
// })

// // // 지도 기본 뷰 설정
// // mapView = new ol.View({
// //   center: olCenter,
// //   zoom: 15,
// //   minZoom: 6,
// //   maxZoom: 19,
// // });

// // // 팝업 옵션 설정
// // mapOverlay = new ol.Overlay({
// //   element: container,
// //   autoPan: true,
// //   autoPanAnimation: {
// //     duration: 250
// //   }
// // });

// 지도 생성
// const olMap = new ol.Map({
//   layers: [olLayer],
//   overlays: [overlay],
//   target: 'map',                     // html에 존재하는 div영역의 id 이름
//   view: olView,
//   moveTolerance: 5
// });

// const Map = ol.Map;
// const View = ol.View;
// const defaultControls = ol.control.defaults;

// map = new ol.Map({
//   target: 'map',
//   layers: [
//     new ol.layer.Tile({
//       source: new ol.source.OSM()
//     })
//   ],
//   view: new ol.View({
//     center: ol.proj.fromLonLat([127.101236, 37.401492]),
//     zoom: 17
//   })
// });


// const mousePositionCtrl = new ol.control.MousePosition({
//   coordinateFormat: ol.coordinate.createStringXY(4),
//   projection: 'EPSG:4326',
//   className: 'custom-mouse-position',
//   target: document.getElementById('mouser-position'),
//   undefinedHTML: '&nbsp;'
// });

// // function addmarker(lon, lat, name) {
// //   // 마커 feature 설정
// //   const feature = new ol.Feature({
// //     geomatry: new ol.geomatry.Point(ol.proj.fromLonLat([lon, lat])), // 경도 위도에 포인트를 설정함.
// //   });

// //   // 마커 스타일 설정
// //   const markerStyle = new ol.style.Style({
// //     image: new ol.style.Icon({ // 마커 이미지 설정
// //       opacity: 1, // 투명도 1=100%
// //       scale: 1.2, // 크기 1=100%
// //       src: 'http://map.vworld.kr/images/ol3/marker_blue.png'
// //     }),
// //   });
// //   zindex: 10
// // }



function tour() {
  const locations = [home, company];
  let index = -1;
  function next(more) {
    if (more) {
      ++index;
      if (index < locations.length) {
        const delay = index === 0 ? 0 : 750;
        setTimeout(function () {
          flyTo(locations[index], next);
        }, delay);
      } else {
        alert('Tour complete');
      }
    } else {
      alert('Tour cancelled');
    }
  }
  next(true);
}

/* 지도(Map) 생성 부분 */
var map = new ol.Map({
  target: 'map', // 지도영역 div의 id 입력
  layers: [
    new ol.layer.Tile({
      source: new ol.source.OSM() // Open Street Map 
    })
  ],
  view: new ol.View({  // 지도의 어느 부분을 보여줄 것인지 설정
    center: ol.proj.fromLonLat([127.101236, 37.401492]), // 중심좌표 지도에 맞는 좌표계로 변환
    zoom: 18,
    enableRotation: false
  })
});

/* 지도(Map) 회전 함수 */
function viewRotation(direction){
var view = map.getView();

var rotation = view.getRotation();

if(direction == 'left'){
  view.setRotation(rotation - (Math.PI/6));
  // Math는 JavaScript 내장 객체로 여러가지 수학적 상수와 함수를 포함하고 있습니다. 
// Math.PI - 원주율
}else{
  view.setRotation(rotation + (Math.PI/6));
}
}

function goCompany() {



  console.log("test!");
}
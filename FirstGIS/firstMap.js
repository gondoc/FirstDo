
const olCenter = ol.proj.fromLonLat([127.101236, 37.401492]);
const key = '413AA14E-9D09-306B-A949-19F7601D475B';
const olLayer = new ol.layer.Tile({
    source: new ol.source.XYZ({
        url: 'http://api.vworld.kr/req/wmts/1.0.0/'+key+'/Base/{z}/{y}/{x}.png'
    })
})

// // 지도 기본 뷰 설정
// const olView = new ol.View({
//     center: olCenter,
//     zoom: 15,
//     minZoom : 6,
//     maxZoom : 19,
// });

// // 팝업 옵션 설정
// const overlay = new ol.Overlay({
//     element: container,
//     autoPan: true,
//     autoPanAnimation: {
//     duration: 250
//     }
// });

// // 지도 생성
// const olMap = new ol.Map({
//     layers: [olLayer],
//     overlays: [overlay],
//     target: 'map',                     // html에 존재하는 div영역의 id 이름
//     view: olView,
//     moveTolerance: 5
// });


const Map = ol.Map;

const View = ol.View;

const defaultControls = ol.control.defaults;

const map = new ol.Map({
    target: 'map',
    layers: [
      new ol.layer.Tile({
        source: new ol.source.OSM()
      })
    ],
    view: new ol.View({
      center: ol.proj.fromLonLat([127.101236, 37.401492]),
      zoom: 17
    })
  });

  let mousePositionCtrl = new ol.control.MousePosition({
    coordinateFormat: ol.coordinate.createStringXY(4),
    projection: 'EPSG:4326',
    className: 'custom-mouse-position',
    target: document.getElementById('mouser-position'),
    undefinedHTML: '&nbsp;'
  });






var travelMap = {

		/** Objeto Mapa Openlayers de la ventana Delivery Points */
		map: null,

		/** Función de inicialización del mapa de la ventana GIS */
		init: function() {
			travelMap.map = new OpenLayers.Map('travelMap', {
				controls: [],
				maxExtent : new OpenLayers.Bounds(-128 * 156543.0339, -128 * 156543.0339, 128 * 156543.0339, 128 * 156543.0339),
				maxResolution : 156543.0339,
				units : 'm',
				projection : new OpenLayers.Projection('EPSG:4326'),
				displayProjection : new OpenLayers.Projection("EPSG:4326"),
			});
			var mapnik = new OpenLayers.Layer.OSM();
			travelMap.map.addLayer(mapnik);
			travelMap.map.addControl(new OpenLayers.Control.MousePosition());
			travelMap.map.addControl(new OpenLayers.Control.PanZoomBar(), new OpenLayers.Pixel(5, 5));
			travelMap.map.addControl(new OpenLayers.Control.NavToolbar(), new OpenLayers.Pixel(80, 5));
			travelMap.map.addControl(new OpenLayers.Control.ScaleLine());
			var dpMarkersLayer = new OpenLayers.Layer.Markers("travelMarkers");
			travelMap.map.addLayer(dpMarkersLayer);
		},

		/** Centra el mapa de la ventana GIS utilizando el valor de los labels ZK de lon, lat y zoom */
		centerMap: function (lon, lat, zoomLevel) {
			console.log("Center Map: " + lon + " " + lat + " " + zoomLevel);
			commonMap.centerMap(travelMap.map, lon, lat, zoomLevel);
		},

		/** Borra todas las capas del mapa */
		removeAllLayers: function() {
			commonMap.removeAllLayers(travelMap.map);
		},

		/** Agrega un marcador en el mapa */
		addMarker: function(lon, lat, html, layerName, iconUrl, hSize, vSize) {
			console.log(lon + " " + lat + " " + html + " " + layerName + " " + iconUrl + " " + hSize + " " + vSize);
			var marker_layer = travelMap.map.getLayersByName(layerName)[0];
			if (typeof(marker_layer) == 'undefined') {
				marker_layer = new OpenLayers.Layer.Markers(layerName);
				travelMap.map.addLayer(marker_layer);
			}
			var popupClass = OpenLayers.Popup.FramedCloud;
			var popupContentHTML = html;
			var size = new OpenLayers.Size(hSize, vSize);
			var ll = new OpenLayers.LonLat(lon,lat).transform(new OpenLayers.Projection("EPSG:4326"), new OpenLayers.Projection("EPSG:900913"));
			commonMap.addMarkerToMap(travelMap.map, marker_layer, ll, popupClass, popupContentHTML, true, true, iconUrl, size);
		},

		zoomToAllLayers: function() {
			commonMap.zoomToAllLayers(travelMap.map);
		}

};
var distributionMap = {

		/** Objeto Mapa Openlayers de la ventana Delivery Points */
		map: null,

		/** Función de inicialización del mapa de la ventana GIS */
		init: function() {
			distributionMap.map = new OpenLayers.Map('distributionMap', {
				controls: [],
				maxExtent : new OpenLayers.Bounds(-128 * 156543.0339, -128 * 156543.0339, 128 * 156543.0339, 128 * 156543.0339),
				maxResolution : 156543.0339,
				units : 'm',
				projection : new OpenLayers.Projection('EPSG:4326'),
				displayProjection : new OpenLayers.Projection("EPSG:4326"),
			});
			var mapnik = new OpenLayers.Layer.OSM();
			distributionMap.map.addLayer(mapnik);
			distributionMap.map.addControl(new OpenLayers.Control.MousePosition());
			distributionMap.map.addControl(new OpenLayers.Control.PanZoomBar(), new OpenLayers.Pixel(5, 5));
			distributionMap.map.addControl(new OpenLayers.Control.NavToolbar(), new OpenLayers.Pixel(80, 5));
			distributionMap.map.addControl(new OpenLayers.Control.ScaleLine());
			var dpMarkersLayer = new OpenLayers.Layer.Markers("distributionMarkers");
			distributionMap.map.addLayer(dpMarkersLayer);
		},

		/** Centra el mapa de la ventana GIS utilizando el valor de los labels ZK de lon, lat y zoom */
		centerMap: function (lon, lat, zoomLevel) {
			console.log("Center Map: " + lon + " " + lat + " " + zoomLevel);
			commonMap.centerMap(distributionMap.map, lon, lat, zoomLevel);
		},

		/** Borra todas las capas del mapa */
		removeAllLayers: function() {
			commonMap.removeAllLayers(distributionMap.map);
		},

		/** Agrega un marcador en el mapa */
		addMarker: function(lon, lat, html, layerName, iconUrl, hSize, vSize) {
			console.log(lon + " " + lat + " " + html + " " + layerName + " " + iconUrl + " " + hSize + " " + vSize);
			var marker_layer = distributionMap.map.getLayersByName(layerName)[0];
			if (typeof(marker_layer) == 'undefined') {
				marker_layer = new OpenLayers.Layer.Markers(layerName);
				distributionMap.map.addLayer(marker_layer);
			}
			var popupClass = OpenLayers.Popup.FramedCloud;
			var popupContentHTML = html;
			var size = new OpenLayers.Size(hSize, vSize);
			var ll = new OpenLayers.LonLat(lon,lat).transform(new OpenLayers.Projection("EPSG:4326"), new OpenLayers.Projection("EPSG:900913"));
			commonMap.addMarkerToMap(distributionMap.map, marker_layer, ll, popupClass, popupContentHTML, true, true, iconUrl, size);
		},

		zoomToAllLayers: function() {
			commonMap.zoomToAllLayers(distributionMap.map);
		}

};
var gisMap = {

		/** Objeto Mapa Openlayers de la ventana GIS */
		map : null,

		/** Función de inicialización del mapa de la ventana GIS */
		init : function() {
			gisMap.map = new OpenLayers.Map('basicMap', {
				controls : [],
				maxExtent : new OpenLayers.Bounds(-128 * 156543.0339, -128 * 156543.0339, 128 * 156543.0339, 128 * 156543.0339),
				maxResolution : 156543.0339,
				units : 'm',
				projection : new OpenLayers.Projection('EPSG:4326'),
				displayProjection : new OpenLayers.Projection("EPSG:4326"),
			});
			var mapnik = new OpenLayers.Layer.OSM();
			gisMap.map.addLayer(mapnik);
			gisMap.centerMap();
			gisMap.map.addControl(new OpenLayers.Control.MousePosition());
			gisMap.map.addControl(new OpenLayers.Control.PanZoomBar(), new OpenLayers.Pixel(5, 5));
			gisMap.map.addControl(new OpenLayers.Control.NavToolbar(), new OpenLayers.Pixel(10, 320));
			gisMap.map.addControl(new OpenLayers.Control.ScaleLine());
			gisMap.map.addControl(new OpenLayers.Control.OverviewMap());
			gisMap.map.addControl(new OpenLayers.Control.LayerSwitcher());
		},

		/** Centra el mapa de la ventana GIS utilizando el valor de los labels ZK de lon, lat y zoom */
		centerMap : function() {
			var lon = parseFloat(zk.Widget.$(jq('$cityCenterLon')).getValue());
			var lat = parseFloat(zk.Widget.$(jq('$cityCenterLat')).getValue());
			var zoomLevel = parseInt(zk.Widget.$(jq('$cityZoomLevel')).getValue());
			commonMap.centerMap(gisMap.map, lon, lat, zoomLevel);
		},

		/** Borra todas las capas del mapa */
		removeAllLayers: function() {
			commonMap.removeAllLayers(gisMap.map);
		},

		/** Agrega un marcador en el mapa */
		addMarker: function(lon, lat, html, layerName, iconUrl, hSize, vSize, zIndex) {
			var marker_layer = gisMap.map.getLayersByName(layerName)[0];
			if (typeof(marker_layer) == 'undefined') {
				marker_layer = new OpenLayers.Layer.Markers(layerName);
				gisMap.map.addLayer(marker_layer);
				console.log(zIndex);
				marker_layer.setZIndex(zIndex);
			}
			var popupClass = OpenLayers.Popup.FramedCloud;
			var popupContentHTML = html;
			var size = new OpenLayers.Size(hSize, vSize);
			var ll = new OpenLayers.LonLat(lon,lat).transform(new OpenLayers.Projection("EPSG:4326"), new OpenLayers.Projection("EPSG:900913"));
			commonMap.addMarkerToMap(gisMap.map, marker_layer, ll, popupClass, popupContentHTML, true, true, iconUrl, size);
		},

		/** Elimina capas según nombre*/
		removeLayer: function(layerName) {
			commonMap.removeLayersByName(gisMap.map, layerName);
		},

		/**
		 * Oculta o muestra capas según prefijo
		 * @param prefix Prefijo del nombre de la capa
		 * @param visibility Indica si muestra u oculta la capa (boolean) 
		 */
		showLayersByPrefix: function (prefix, visibility) {
			commonMap.showLayersByPrefix(gisMap.map, prefix, visibility);
		},

		/**
		 * Elimina capas según prefijo
		 * @param prefix Prefijo del nombre de la capa
		 */
		removeLayersByPrefix: function (prefix) {
			commonMap.removeLayersByPrefix(gisMap.map, prefix);
		},

		zoomToAllLayers: function() {
			commonMap.zoomToAllLayers(gisMap.map);
		},

		/**
		 * Dibuja una linea entre dos puntos
		 * @param lonFrom Longitud punto de inicio de la linea
		 * @param latFrom Latitud punto de inicio de la linea
		 * @param lonTo Longitud punto final de la linea
		 * @param latTo Latitud punto final de la linea
		 */
		drawLine: function (layerName, lonFrom, latFrom, lonTo, latTo, zIndex) {
			commonMap.drawLine(gisMap.map, layerName, lonFrom, latFrom, lonTo, latTo, zIndex);
		}

};
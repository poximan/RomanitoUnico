
var restrictedAreaMap = {

		/** Objeto Mapa Openlayers */
		map: null,

		/** Función de inicialización del mapa */
		init: function() {
			restrictedAreaMap.map = new OpenLayers.Map('restrictedAreaMap', {
				controls: [],
				maxExtent : new OpenLayers.Bounds(-128 * 156543.0339, -128 * 156543.0339, 128 * 156543.0339, 128 * 156543.0339),
				maxResolution : 156543.0339,
				units : 'm',
				projection : new OpenLayers.Projection('EPSG:4326'),
				displayProjection : new OpenLayers.Projection("EPSG:4326"),
			});
			var mapnik = new OpenLayers.Layer.OSM();
			restrictedAreaMap.map.addLayer(mapnik);

			//Capa de poligonos/zonas
			restrictedAreaMap.areasLayer = new OpenLayers.Layer.Vector("areas"); 
			restrictedAreaMap.map.addLayer(restrictedAreaMap.areasLayer);

			//Capa de marcadores
			var dpMarkersLayer = new OpenLayers.Layer.Markers("DPMarkers");
			restrictedAreaMap.map.addLayer(dpMarkersLayer);

			//Agrega los controles generales
			restrictedAreaMap.map.addControl(new OpenLayers.Control.MousePosition());
			restrictedAreaMap.map.addControl(new OpenLayers.Control.PanZoomBar(), new OpenLayers.Pixel(5, 5));
			restrictedAreaMap.map.addControl(new OpenLayers.Control.NavToolbar(), new OpenLayers.Pixel(80, 5));
			restrictedAreaMap.map.addControl(new OpenLayers.Control.ScaleLine());

			// Serializa/deserealiza los poligonos/zonas
			var in_options = {
					'internalProjection' : restrictedAreaMap.map.baseLayer.projection,
					'externalProjection' : new OpenLayers.Projection('EPSG:4326')
			};
			var out_options = {
					'internalProjection' : restrictedAreaMap.map.baseLayer.projection,
					'externalProjection' : new OpenLayers.Projection('EPSG:4326')
			};
			restrictedAreaMap.formatter = {
					'in' : {
						kml : new OpenLayers.Format.KML(in_options)
					},
					'out' : {
						kml : new OpenLayers.Format.KML(out_options)
					}
			};

			zAu.send(new zk.Event(zk.Widget.$(jq('$restrictedAreaWndw')), 'onAfterInitMap', null));
		},

		/** Centra el mapa utilizando el valor de los labels ZK de lon, lat y zoom */
		centerMap: function (lon, lat, zoomLevel) {
			commonMap.centerMap(restrictedAreaMap.map, lon, lat, zoomLevel);
		},

		/** Deserealiza un KML y lo dibuja en una capa del mapa */
		deserialize: function(kml) {
			commonMap.deserialize(restrictedAreaMap.formatter, kml, restrictedAreaMap.areasLayer, restrictedAreaMap.map);
		},

		addMarkerIcon: function(lon, lat, iconImg) {
			var lonlat = new OpenLayers.LonLat(lon, lat).transform(new OpenLayers.Projection("EPSG:4326"), new OpenLayers.Projection("EPSG:900913"));
			var size = new OpenLayers.Size(21, 25);
			var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
			var icon = new OpenLayers.Icon(iconImg, size, offset);
			var dpMarker = new OpenLayers.Marker(lonlat, icon);
			var dpMarkersLayer = restrictedAreaMap.map.getLayersByName('DPMarkers')[0];
			dpMarkersLayer.addMarker(dpMarker);
		},

		addMarker: function(lon, lat) {
			restrictedAreaMap.addMarkerIcon(lon, lat, '/mel/OpenLayers/img/marker.png');
		},

		addVehicle: function(lon, lat) {
			restrictedAreaMap.addMarkerIcon(lon, lat, '/mel/OpenLayers/img/truck.gif');
		}

};
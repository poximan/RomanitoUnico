var areaMap = {

		/** Objeto Mapa Openlayers de la ventana Zonas */
		map: null,
		areasLayer: null, //Capa de poligonos/zonas
		formatter: null, //Objeto que permite serializar/deserializar los poligonos/zonas
		controls: null, //Controles para dibujar/modificar los poligonos/zonas

		/** Función de inicialización del mapa de Zonas */
		init: function() {
			areaMap.map = new OpenLayers.Map('areaMap', {
				controls: [],
				maxExtent : new OpenLayers.Bounds(-128 * 156543.0339, -128 * 156543.0339, 128 * 156543.0339, 128 * 156543.0339),
				maxResolution : 156543.0339,
				units : 'm',
				projection : new OpenLayers.Projection('EPSG:4326'),
				displayProjection : new OpenLayers.Projection("EPSG:4326"),
			});
			var mapnik = new OpenLayers.Layer.OSM();
			areaMap.map.addLayer(mapnik);
			var areaMarkersLayer = new OpenLayers.Layer.Markers("areaMarkers");
			areaMap.map.addLayer(areaMarkersLayer);

			//Capa de poligonos/zonas
			areaMap.areasLayer = new OpenLayers.Layer.Vector("areas"); 
			areaMap.map.addLayer(areaMap.areasLayer);

			//Agrega los controles generales
			areaMap.map.addControl(new OpenLayers.Control.MousePosition());
			areaMap.map.addControl(new OpenLayers.Control.PanZoomBar(), new OpenLayers.Pixel(5, 5));
			areaMap.map.addControl(new OpenLayers.Control.NavToolbar(), new OpenLayers.Pixel(80, 5));
			areaMap.map.addControl(new OpenLayers.Control.ScaleLine());

			//Agrega los controles para dibujar/modificar los poligonos/zonas
			areaMap.controls = {
					polygon: new OpenLayers.Control.DrawFeature(areaMap.areasLayer, OpenLayers.Handler.Polygon),
					modifyFeature: new OpenLayers.Control.ModifyFeature(areaMap.areasLayer)
			};
			for(var key in areaMap.controls) {
				areaMap.map.addControl(areaMap.controls[key]);
			}


			// Serializa/deserealiza los poligonos/zonas
			var in_options = {
					'internalProjection' : areaMap.map.baseLayer.projection,
					'externalProjection' : new OpenLayers.Projection('EPSG:4326')
			};
			var out_options = {
					'internalProjection' : areaMap.map.baseLayer.projection,
					'externalProjection' : new OpenLayers.Projection('EPSG:4326')
			};
			areaMap.formatter = {
					'in' : {
						kml : new OpenLayers.Format.KML(in_options)
					},
					'out' : {
						kml : new OpenLayers.Format.KML(out_options)
					}
			};

			// Eventos sobre la capa de poligonos/zonas
			areaMap.areasLayer.events.on({
				'featuresadded': areaMap.serialize,
				'featuresremoved': areaMap.serialize,
				'featuremodified': areaMap.serialize,
				'afterfeaturemodified': areaMap.serialize
			});

			//Envia un evento al controller donde esta el mapa. Normalmente se utiliza para centrar el mapa en alguna posicion especifica
			zAu.send(new zk.Event(zk.Widget.$(jq('$areaFormWndw')), 'onAfterInitMap', null));
		},

		/** Centra el mapa utilizando el valor de los labels ZK de lon, lat y zoom */
		centerMap: function (lon, lat, zoomLevel) {
			commonMap.centerMap(areaMap.map, lon, lat, zoomLevel);
		},

		/** Borra todas las capas del mapa */
		removeMarkers: function() {
			var areaMarkersLayer = areaMap.map.getLayersByName('areaMarkers')[0];
			if (areaMarkersLayer)
				areaMarkersLayer.clearMarkers();
		},

		/** Agrega un marcador en el mapa */
		addMarker: function(lon, lat) {
			var lonlat = new OpenLayers.LonLat(lon, lat).transform(new OpenLayers.Projection("EPSG:4326"), new OpenLayers.Projection("EPSG:900913"));
			var size = new OpenLayers.Size(21,25);
			var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
			var icon = new OpenLayers.Icon('/mel/OpenLayers/img/marker.png', size, offset);
			var areaMarker = new OpenLayers.Marker(lonlat,icon);
			var areaMarkersLayer = areaMap.map.getLayersByName('areaMarkers')[0];
			areaMarkersLayer.addMarker(areaMarker);
		},

		zoomToAllLayers: function() {
			commonMap.zoomToAllLayers(areaMap.map);
		},

		/** Serializa a KML un poligono dibujado en una capa */
		serialize: function() {
			commonMap.serialize(areaMap.formatter, areaMap.areasLayer.features, 'kmlTxtbx', 'onSetKml');
		},

		/** Deserealiza un KML y lo dibuja en una capa del mapa */
		deserialize: function(kml) {
			commonMap.deserialize(areaMap.formatter, kml, areaMap.areasLayer, areaMap.map);
		},

		/** Borra todos los poligonos dibujados */
		clearPolygons: function() {
			commonMap.clearFeatures(areaMap.areasLayer);
		},

		/** Dibujar poligonos */
		drawPolygon : function () {
			document.getElementById("areaDrawPolygonDv").setAttribute("class", "myEditingToolbarItemSelected");
			document.getElementById("areaEditPolygonDv").setAttribute("class", "myEditingToolbarItem");
			areaMap.controls.polygon.activate();
			areaMap.controls.modifyFeature.deactivate();
		},

		/** Editar poligonos */
		editPolygon : function () {
			document.getElementById("areaEditPolygonDv").setAttribute("class", "myEditingToolbarItemSelected");
			document.getElementById("areaDrawPolygonDv").setAttribute("class", "myEditingToolbarItem");
			areaMap.controls.polygon.deactivate();
			areaMap.controls.modifyFeature.activate();
		}

};
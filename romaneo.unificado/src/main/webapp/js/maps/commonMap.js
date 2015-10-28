var commonMap = {

		/**
		 * Centra un mapa en un determinado punto utilizando un nivel de zoom
		 * @param map Mapa a centrar 
		 * @param lon Valor de longitud del punto central
		 * @param lat Valor de latitud del punto central
		 * @param zoomLevel nivel de Zoom
		 */
		centerMap: function(map, lon, lat, zoomLevel) {
			if (map && lon && lat && zoomLevel)
				map.setCenter(new OpenLayers.LonLat(lon, lat)
				.transform(new OpenLayers.Projection("EPSG:4326"), new OpenLayers.Projection("EPSG:900913")), zoomLevel);
		},

		/**
		 * Borra todas las capas de un mapa
		 * @param map Mapa OpenLayers
		 */
		removeAllLayers: function (map) {
			var layers = map.getLayersByName(new RegExp(".*", "i"));
			for (var i = 0; i < layers.length; i++) {
				if (!layers[i].isBaseLayer)
					map.removeLayer(layers[i]);
			}
		},

		/**
		 * Borra capas según nombre
		 * @param map Mapa OpenLayers
		 * @param layerName Nombre de la capa OpenLayers
		 */
		removeLayersByName: function (map, layerName) {
			var layers = map.getLayersByName(layerName);
			for (var i = 0; i < layers.length; i++) {
				map.removeLayer(layers[i]);
			}
		},

		/**
		 * Oculta o muestra capas según prefijo
		 * @param map Mapa OpenLayers
		 * @param prefix Prefijo del nombre de la capa
		 * @param visibility Indica si muestra u oculta la capa (boolean)
		 */
		showLayersByPrefix: function (map, prefix, visibility) {
			var layers = map.getLayersByName(new RegExp(prefix + ".*", "i"));
			for (var i = 0; i < layers.length; i++) {
				layers[i].setVisibility(visibility);
			}
		},

		/**
		 * Elimina capas según prefijo
		 * @param map Mapa OpenLayers
		 * @param prefix Prefijo del nombre de la capa
		 */
		removeLayersByPrefix: function (map, prefix) {
			var layers = map.getLayersByName(new RegExp(prefix + ".*", "i"));
			for (var i = 0; i < layers.length; i++) {
				map.removeLayer(layers[i]);
			}
		},

		/**
		 * Agrega un marcador en un mapa
		 * @param map Mapa OpenLayers
		 * @param markersLayer Capa en la que se agregará el marcador
		 * @param longlat Objeto Latitud Longitud de Openlayers
		 * @param popupClass Clase OpenLayers de popups
		 * @param popupContentHTML Contenido HTML del popup
		 * @param closeBox Tipo de botón de cierre del popup 
		 * @param overflow true: auto false: hidden
		 * @param iconURL URL del ícono del marcador
		 * @param size Tamaño del marcador
		 * @param hidePupups Ocultar todos los popups abiertos
		 * @param zkWindow Ventana ZK que recibe el evento "onMarkerClick"
		 * @param point No me acuerdo TODO: corregir
		 */
		addMarkerToMap: function (map, markersLayer, longlat, popupClass, popupContentHTML, closeBox, overflow, iconURL, size, hidePupups, zkWindow, point) {
			var feature = new OpenLayers.Feature(markersLayer, longlat); 
			feature.closeBox = closeBox;
			feature.popupClass = popupClass;
			feature.data.popupContentHTML = popupContentHTML;
			feature.data.overflow = (overflow) ? "auto" : "hidden";

			var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
			feature.data.icon = new OpenLayers.Icon(iconURL, size, offset);

			var marker = feature.createMarker();

			var markerClick = function (evt) {
				if (this.popup == null) {
					this.popup = this.createPopup(this.closeBox);
					map.addPopup(this.popup, hidePupups);
					this.popup.show();
				} else {
					this.popup.toggle();
				}
				currentPopup = this.popup;
				if (zkWindow && point) {
					zAu.send(new zk.Event(zk.Widget.$(jq('$' + zkWindow)), 'onMarkerClick', point));
				}
				OpenLayers.Event.stop(evt);
			};

			marker.events.register("mousedown", feature, markerClick);

			markersLayer.addMarker(marker);
		},

		/**
		 * Elimina todos los pupups de un mapa 
		 * @param mapToClear Mapa Openlayers
		 */
		popupClear: function (mapToClear) {
			if (mapToClear.popups && mapToClear.popups.length > 0) {
				for (var i = 0; i < mapToClear.popups.length; i++) {
					mapToClear.popups[i].hide();
				}
			}
		},

		/**
		 * Ajusta el zoom un nivel más afuera del necesario para mostrar todos los elementos de las capas de Markers
		 * @param map Mapa OpenLayer
		 */
		zoomToAllLayers: function (map) {
			//Obtengo todas las capas de marcadores
			var layers = map.getLayersByClass(new RegExp(".*Markers.*", "i"));
			if (layers && layers.length > 0) {
				var bounds = layers[0].getDataExtent();
				if (layers && layers.length > 1) {
					for (var i = 1; i < layers.length; i++) {
						bounds.extend(layers[i].getDataExtent());
					}
				}
				map.zoomToExtent(bounds);
				map.zoomOut();
			}
		},

		/**
		 * Dibuja una linea entre dos puntos
		 * @param map Mapa OpenLayer
		 * @param lonFrom Longitud punto de inicio de la linea
		 * @param latFrom Latitud punto de inicio de la linea
		 * @param lonTo Longitud punto final de la linea
		 * @param latTo Latitud punto final de la linea
		 */
		drawLine: function (map, layerName, lonFrom, latFrom, lonTo, latTo, zIndex) {
			var startPt = new OpenLayers.Geometry.Point(lonFrom, latFrom).transform(new OpenLayers.Projection("EPSG:4326"), new OpenLayers.Projection("EPSG:900913"));
			var endPt = new OpenLayers.Geometry.Point(lonTo, latTo).transform(new OpenLayers.Projection("EPSG:4326"), new OpenLayers.Projection("EPSG:900913"));

			//make the line:
			var line = new OpenLayers.Geometry.LineString([startPt, endPt]);

			//style
			var style={strokeColor:"#0500bd", strokeWidth:1};

			//make vector 
			var fea = new OpenLayers.Feature.Vector(line, {}, style);

			var vector_layer = map.getLayersByName(layerName)[0];
			if (typeof(vector_layer) == 'undefined') {
				vector_layer = new OpenLayers.Layer.Vector(layerName);
			}
			//add the feature
			vector_layer.addFeatures([fea]);

			//add to map
			map.addLayer(vector_layer);
			vector_layer.setZIndex(zIndex);
		},

		/**
		 * Serializa a KML un poligono dibujado en una capa
		 * @param formatter
		 * @param features Poligono dibujado
		 * @param componentName Nombre del componente donde enviar el poligono serializado
		 * @param eventName Nombre del evento donde enviar el poligono serializado
		 */
		serialize: function (formatter, features, componentName, eventName) {
			var type = 'kml';
			var str = formatter['out'][type].write(features, true);
			if (componentName && eventName)
				zAu.send(new zk.Event(zk.Widget.$(jq('$' + componentName)), eventName, str));
		},

		/**
		 * Deserealiza un KML y lo dibuja en una capa del mapa
		 * @param formatter
		 * @param kml KML
		 * @param layer Capa donde dibujar el poligono 
		 * @param map Mapa donde realizar el zoom sobre el poligono 
		 */
		deserialize: function (formatter, kml, layer, map) {
			var type = "kml";
			var features = formatter['in'][type].read(kml);
			var bounds;
			if(features) {
				if(features.constructor != Array) {
					features = [features];
				}
				for(var i = 0; i < features.length; ++i) {
					if (!bounds) {
						bounds = features[i].geometry.getBounds();
					} else {
						bounds.extend(features[i].geometry.getBounds());
					}
				}
				layer.addFeatures(features);
				map.zoomToExtent(bounds);
			}
		},

		/**
		 * Borrar poligonos dibujados
		 * @param layer Capa donde borrar los poligonos
		 */
		clearFeatures: function (layer) {
			layer.destroyFeatures();
		}

};
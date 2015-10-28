
var deliveryPointMap = {

		/** Objeto Mapa Openlayers de la ventana Delivery Points */
		map: null,

		/** Función de inicialización del mapa de la ventana GIS */
		init: function() {
			deliveryPointMap.map = new OpenLayers.Map('deliveryPointMap', {
				controls: [],
				maxExtent : new OpenLayers.Bounds(-128 * 156543.0339, -128 * 156543.0339, 128 * 156543.0339, 128 * 156543.0339),
				maxResolution : 156543.0339,
				units : 'm',
				projection : new OpenLayers.Projection('EPSG:4326'),
				displayProjection : new OpenLayers.Projection("EPSG:4326"),
			});
			var mapnik = new OpenLayers.Layer.OSM();
			deliveryPointMap.map.addLayer(mapnik);
			deliveryPointMap.map.addControl(new OpenLayers.Control.MousePosition());
			deliveryPointMap.map.addControl(new OpenLayers.Control.PanZoomBar(), new OpenLayers.Pixel(5, 5));
			deliveryPointMap.map.addControl(new OpenLayers.Control.NavToolbar(), new OpenLayers.Pixel(80, 5));
			deliveryPointMap.map.addControl(new OpenLayers.Control.ScaleLine());
			var dpMarkersLayer = new OpenLayers.Layer.Markers("DPMarkers");
			deliveryPointMap.map.addLayer(dpMarkersLayer);
			click = new OpenLayers.Control.Click();
			deliveryPointMap.map.addControl(click);
			click.activate();
			zAu.send(new zk.Event(zk.Widget.$(jq('$deliveryPointFormWndw')), 'onAfterInitMap', null));
		},

		/** Centra el mapa de la ventana GIS utilizando el valor de los labels ZK de lon, lat y zoom */
		centerMap: function (lon, lat, zoomLevel) {
			commonMap.centerMap(deliveryPointMap.map, lon, lat, zoomLevel);
		},

		onClick: function(e) {
			var lonlat = deliveryPointMap.map.getLonLatFromPixel(e.xy);
			var size = new OpenLayers.Size(21,25);
			var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
			var icon = new OpenLayers.Icon('/mel/OpenLayers/img/marker.png', size, offset);

			var dpMarkersLayer = deliveryPointMap.map.getLayersByName('DPMarkers')[0];
			console.log("ANTES IF");
			if (dpMarkersLayer) {
				console.log("DENTRO IF");
				dpMarkersLayer.clearMarkers();

				var dpMarker = new OpenLayers.Marker(lonlat,icon);
				dpMarkersLayer.addMarker(dpMarker);

				lonlat = lonlat.transform(new OpenLayers.Projection("EPSG:900913"), new OpenLayers.Projection("EPSG:4326"));
				zAu.send(new zk.Event(zk.Widget.$(jq('$deliveryPointFormWndw')), 'onMapClicked', [lonlat.lat.toFixed(6), lonlat.lon.toFixed(6)]));
			}
		},

		addMarker: function(lon, lat) {
			var lonlat = new OpenLayers.LonLat(lon, lat).transform(new OpenLayers.Projection("EPSG:4326"), new OpenLayers.Projection("EPSG:900913"));
			var size = new OpenLayers.Size(21,25);
			var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
			var icon = new OpenLayers.Icon('/mel/OpenLayers/img/marker.png', size, offset);
			var dpMarker = new OpenLayers.Marker(lonlat,icon);
			var dpMarkersLayer = deliveryPointMap.map.getLayersByName('DPMarkers')[0];
			if (dpMarkersLayer)
				dpMarkersLayer.clearMarkers();
			dpMarkersLayer.addMarker(dpMarker);
		}

};

/** Clase Click para el control que recupera las coordenadas */
OpenLayers.Control.Click = OpenLayers.Class(OpenLayers.Control, {                
	defaultHandlerOptions: {
		'single': true,
		'double': false,
		'pixelTolerance': 0,
		'stopSingle': false,
		'stopDouble': false
	},

	initialize: function(options) {
		this.handlerOptions = OpenLayers.Util.extend(
				{}, this.defaultHandlerOptions
		);
		OpenLayers.Control.prototype.initialize.apply(
				this, arguments
		); 
		this.handler = new OpenLayers.Handler.Click(
				this, {
					'click': this.onClick
				}, this.handlerOptions
		);
	}, 

	/** Acciones a ejecutar cuando hago click en una posición */
	onClick: function(e) {
		deliveryPointMap.onClick(e);
	}

});
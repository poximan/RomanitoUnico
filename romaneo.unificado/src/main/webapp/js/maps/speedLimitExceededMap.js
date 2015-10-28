
var speedLimitExceededMap = {

		/** Objeto Mapa Openlayers */
		map: null,

		/** Función de inicialización del mapa */
		init: function() {
			speedLimitExceededMap.map = new OpenLayers.Map('speedLimitExceededMap', {
				controls: [],
				maxExtent : new OpenLayers.Bounds(-128 * 156543.0339, -128 * 156543.0339, 128 * 156543.0339, 128 * 156543.0339),
				maxResolution : 156543.0339,
				units : 'm',
				projection : new OpenLayers.Projection('EPSG:4326'),
				displayProjection : new OpenLayers.Projection("EPSG:4326"),
			});
			var mapnik = new OpenLayers.Layer.OSM();
			speedLimitExceededMap.map.addLayer(mapnik);
			speedLimitExceededMap.map.addControl(new OpenLayers.Control.MousePosition());
			speedLimitExceededMap.map.addControl(new OpenLayers.Control.PanZoomBar(), new OpenLayers.Pixel(5, 5));
			speedLimitExceededMap.map.addControl(new OpenLayers.Control.NavToolbar(), new OpenLayers.Pixel(80, 5));
			speedLimitExceededMap.map.addControl(new OpenLayers.Control.ScaleLine());
			var dpMarkersLayer = new OpenLayers.Layer.Markers("DPMarkers");
			speedLimitExceededMap.map.addLayer(dpMarkersLayer);

			click = new OpenLayers.Control.Click();
			speedLimitExceededMap.map.addControl(click);
			click.activate();

			zAu.send(new zk.Event(zk.Widget.$(jq('$speedLimitExceededWndw')), 'onAfterInitMap', null));
		},

		/** Centra el mapa utilizando el valor de los labels ZK de lon, lat y zoom */
		centerMap: function (lon, lat, zoomLevel) {
			commonMap.centerMap(speedLimitExceededMap.map, lon, lat, zoomLevel);
		},

		addMarker: function(lon, lat) {
			var lonlat = new OpenLayers.LonLat(lon, lat).transform(new OpenLayers.Projection("EPSG:4326"), new OpenLayers.Projection("EPSG:900913"));
			var size = new OpenLayers.Size(21,25);
			var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
			var icon = new OpenLayers.Icon('/mel/OpenLayers/img/marker.png', size, offset);
			var dpMarker = new OpenLayers.Marker(lonlat,icon);
			var dpMarkersLayer = speedLimitExceededMap.map.getLayersByName('DPMarkers')[0];
			dpMarkersLayer.addMarker(dpMarker);
		},

		addVehicle: function(lon, lat) {
			var lonlat = new OpenLayers.LonLat(lon, lat).transform(new OpenLayers.Projection("EPSG:4326"), new OpenLayers.Projection("EPSG:900913"));
			var size = new OpenLayers.Size(21,25);
			var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
			var icon = new OpenLayers.Icon('/mel/OpenLayers/img/truck.gif', size, offset);
			var dpMarker = new OpenLayers.Marker(lonlat,icon);
			var dpMarkersLayer = speedLimitExceededMap.map.getLayersByName('DPMarkers')[0];
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
	} 

});
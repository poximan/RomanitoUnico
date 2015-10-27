/**
 * Permite conocer la altura que tiene el listbox en el cliente para calcular
 * las filas que va a tener el listbox.
 * @param component Listbox
 */
function calculateHeight(component) {

	// Obtengo la altura del listbox
	var height = document.getElementById(zk.Widget.$('$' + component).uuid).offsetHeight;

	// Le quito la cabecera del listbox
	height = height - 34;

	// 38 es el tamaño de cada fila
	height = ~~(height / 38);

	// Envio un evento al controller
	zAu.send(new zk.Event(zk.Widget.$(jq('$' + component)), 'onSetPageSize', height));

}
var localidades = Array();

function populateOptions(event, field, add, array){
	var container;
	if ((container = field.getNext()) == null) {
		container = new Element('div', {'class': 'suggestions'}).inject(field, 'after');
	}
	switch(event.keyCode) {
	case 13:			
		if(!add) {
			field.set('value', container.getElement('.selected').get('html'));
		} else {
			var value = container.getElement('.selected').get('html');
			var fieldValue = field.get('value');
			if(fieldValue.indexOf(',') != -1)
				field.set('value', fieldValue.substr(0, fieldValue.lastIndexOf(',')+1).trim() + value);
			else
				field.set('value', value);
		}
		container.empty();
		break;
	case 27:
		container.empty();
		break;
	case 38:
		if(container.getElement('.selected') != container.getFirst()){
			var previous = container.getElement('.selected').getPrevious();
			container.getElement('.selected').removeClass('selected');
			previous.addClass('selected');
		}
		break;
	case 40:
		if(container.getElement('.selected') != container.getLast()){
			var next = container.getElement('.selected').getNext();
			container.getElement('.selected').removeClass('selected');
			next.addClass('selected');
		}
		break;
	default:
		container.empty();
	var query = field.get('value').toLowerCase();
	if(query.length - query.lastIndexOf(',') - 1 < 3)
		return;
	array.each(function(el){				
		if(add && query.lastIndexOf(',') != -1)
			query = query.substr(query.lastIndexOf(',')+1).trim();
		if(el.toLowerCase().indexOf(query) != -1) {
			new Element('p', {
				'html': el
			}).inject(container);				
		}
	});
	if(container.childNodes.length > 0)
		container.firstChild.addClass('selected');
	break;
	}
}
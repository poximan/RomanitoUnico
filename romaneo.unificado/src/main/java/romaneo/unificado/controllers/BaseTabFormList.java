package romaneo.unificado.controllers;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.util.StringUtils;
import org.zkoss.spring.SpringUtil;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Doublespinner;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;

import romaneo.unificado.daos.BaseDao;
import romaneo.unificado.daos.PagedQueryResponse;
import romaneo.unificado.domain.Acondicionador;
import romaneo.unificado.domain.BaseEntity;
import romaneo.unificado.domain.Contacto;
import romaneo.unificado.services.BaseService;
import romaneo.unificado.services.ContactoService;
import romaneo.utileria.abstractFactory.FabricaAbstracta;
import romaneo.utileria.abstractFactory.ProductorFabricas;

public abstract class BaseTabFormList<Entity extends Serializable> extends BaseController {

	@SuppressWarnings("rawtypes")
	protected Map args;

	protected List<Entity> all = new ArrayList<Entity>();
	protected String sortField = null;
	protected String sortOrder = null;
	protected boolean executeAndRenderPagedQueryInitial = true;

	@SuppressWarnings("rawtypes")
	private BaseService service_contacto = null;

	public BaseTabFormList() {
		super();

		if (getEntityServiceContacto() != null)
			setServiceContacto((BaseService) SpringUtil.getBean(getEntityServiceContacto()));
	}

	protected String getEntityServiceContacto() {
		return ContactoService.class.getSimpleName();
	}

	@SuppressWarnings("rawtypes")
	protected BaseService getServiceContacto() {
		return service_contacto;
	}

	@SuppressWarnings("rawtypes")
	protected void setServiceContacto(BaseService service_contacto) {
		this.service_contacto = service_contacto;
	}

	protected void resetSelected() {
		getListComponent().setSelectedIndex(-1);
		enableButtons();
	}

	/** Nuevo */
	@Listen("onClick = #newBttn")
	public void create() {
		((Window) Executions.createComponents(getFormPageName(), getWindowComponent(), null)).doOverlapped();
	}

	/** Editar */
	@Listen("onClick = #editBttn")
	public void edit() {

		if (getListComponent().getSelectedIndex() == -1) {
			Messagebox.show("Primero seleccione una fila de la lista", "Error", Messagebox.OK, Messagebox.ERROR);
			return;
		}

		FabricaAbstracta fabrica = ProductorFabricas.getFabrica();
		BaseEntity objeto_entidad = fabrica.getEntity(getListComponent().getSelectedItem().getAttribute(ENTITY));

		String string_entidad = objeto_entidad.getClass().getSimpleName().toLowerCase();
		String direccion = string_entidad + "/" + string_entidad + "FormNuevo.zul?id="
				+ objeto_entidad.getPK().toString();

		Executions.sendRedirect(direccion);
	}

	/** Eliminar */
	@Listen("onClick = #deleteBttn")
	public void delete() {

		if (getListComponent().getSelectedIndex() == -1) {
			Messagebox.show("Primero seleccione una fila de la tabla", "Error", Messagebox.OK, Messagebox.ERROR);
			return;
		}

		Map<String, Object> arg = new HashMap<String, Object>();
		arg.put(DeleteFormController.ARG_SELECTED, getListComponent().getSelectedItem().getAttribute(ENTITY));
		arg.put(DeleteFormController.ARG_SERVICE, getService());
		((Window) Executions.createComponents(Labels.getLabel("url.deleteForm"), getWindowComponent(), arg))
				.doOverlapped();
	}

	@SuppressWarnings("unchecked")
	public Entity getListSelected() {
		if (getListComponent().getSelectedIndex() == -1)
			return null;
		return (Entity) getListComponent().getSelectedItem().getAttribute(ENTITY);
	}

	/** Filtrar */
	@Listen("onClick = #filterBttn")
	public void filter() {
		buildParameters();
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {

		super.doAfterCompose(comp);

		// Busco los componente que comiencen con filter* y les agrego el evento
		// onOK
		addOnOKEventListener(comp);
		addOnRefreshListsEventListener(comp);

		getListComponent().setModel(new ListModelList<Entity>(new ArrayList<Entity>()));
		getListComponent().setItemRenderer(getListitemRender());
		getWindowComponent().setAttribute(LIST, new ArrayList<Entity>());
		getWindowComponent().setAttribute(LIST_COMPONENT, getListComponent());

		enableButtons();
		securityButtons();
		filterComboBoxComplete();

		getListComponent().addEventListener("onSelect", new EventListener<Event>() {
			@Override
			public void onEvent(Event event) throws Exception {
				enableButtons();
			}
		});

		if (getPageSizeComponent() != null) {
			getPageSizeComponent().addEventListener("onSelect", new EventListener<Event>() {
				@Override
				public void onEvent(Event event) throws Exception {

					if (getPageSizeComponent().getSelectedItem() == null)
						return;

					Integer pageSize = Integer.parseInt(getPageSizeComponent().getSelectedItem().getValue().toString());
					getPagingComponent().setPageSize(pageSize);
					getListComponent().setRows(pageSize);
				}
			});
		}

		// Despues de renderizar el listbox, si hay elementos activo el boton
		// para exportar
		getListComponent().addEventListener("onAfterRender", new EventListener<Event>() {
			@Override
			public void onEvent(Event event) throws Exception {
				if (executeAndRenderPagedQueryInitial)
					buildParameters();
				executeAndRenderPagedQueryInitial = false;
			}
		});

		getWindowComponent().addEventListener("onRefresh", new EventListener<Event>() {
			@Override
			public void onEvent(Event event) throws Exception {
				buildParameters();
			}
		});

		// Le asigno el evento onPaging
		getPagingComponent().addEventListener("onPaging", new EventListener<Event>() {
			@Override
			public void onEvent(Event event) throws Exception {
				buildParameters();
			}
		});
	}

	/**
	 * Recorre todos los componentes de la pagina y les agrega el evento onOK a
	 * los que pertenezcan a los filtros
	 * 
	 * @param comp
	 */
	public void addOnOKEventListener(Component comp) {

		if (comp.getId() != null && comp.getId().matches("^filterBy.*$")
				&& (comp instanceof Combobox || comp instanceof Textbox || comp instanceof Intbox
						|| comp instanceof Datebox || comp instanceof Decimalbox || comp instanceof Longbox)) {
			comp.addEventListener("onOK", new EventListener<Event>() {
				@Override
				public void onEvent(Event event) throws Exception {
					buildParameters();
				}
			});
		}

		List<Component> list = comp.getChildren();

		for (Component child : list) {
			addOnOKEventListener(child);
		}

	}

	public void addOnRefreshListsEventListener(Component comp) {
		getWindowComponent().addEventListener("onRefreshLists", new EventListener<Event>() {
			@Override
			public void onEvent(Event event) throws Exception {
				buildParameters();
			}
		});
	}

	/**
	 * Completa los filtros del tipo Combobox relacionados a una entidad. Para
	 * esto se requiere que los combobox empiecen con la palabra "filter" mas el
	 * nombre del atributo perteneciente a la entidad. Ademas el campo que
	 * representa la entidad debe contener la anotacion ManyToOne. Ejemplo:
	 * filterCitiesCityId.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void filterComboBoxComplete() {

		Class entityClass = null;

		try {
			entityClass = Class.forName(getClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}

		Component element;

		for (Field field : entityClass.getDeclaredFields()) {

			if (getWindowComponent().hasFellow("filterBy" + StringUtils.capitalize(field.getName()))) {

				element = getWindowComponent().getFellow("filterBy" + StringUtils.capitalize(field.getName()));

				// Si el componente es del tipo Combobox y el campo es una
				// entidad realizo la consulta a la
				// base de datos para completar el componente
				if (element instanceof org.zkoss.zul.Combobox
						&& field.getAnnotation(javax.persistence.ManyToOne.class) != null) {

					String query = "FROM " + field.getType().getCanonicalName() + " e";

					List<Entity> q = getService().findQuery(query);

					if (q != null) {
						((Combobox) element).setModel(new ListModelList<Entity>(q));

						// Completo el combobox con los datos de las entidades
						((Combobox) element).setItemRenderer(new ComboitemRenderer<BaseEntity>() {
							@Override
							public void render(Comboitem item, BaseEntity data, int arg) throws Exception {
								item.setLabel(String.valueOf(data));
								item.setAttribute(ENTITY, data);
								if (data.equals(item.getAttribute(SELECTED))) {
									((Combobox) item.getParent()).setValue(data.toString());
								}
							}
						});

						Object value = null;

						// Tomo el valor del primer elemento de la lista y lo
						// guardo como elemento seleccionado
						if (value == null && q.size() > 0) {
							value = q.get(0);
							((Combobox) element).setAttribute(SELECTED, value);
						}

						// Si se selecciono un elemento en el combobox lo guardo
						// como el elemento seleccionado
						((Combobox) element).addEventListener("onSelect", new EventListener<Event>() {
							@Override
							public void onEvent(Event event) throws Exception {

								Combobox combobox = (Combobox) event.getTarget();

								if (combobox.getSelectedItem() != null)
									combobox.setAttribute(SELECTED, combobox.getSelectedItem().getAttribute(ENTITY));
							}
						});
					}
				}
			}
		}

	}

	public void enableButtons() {
		if (getListComponent().getSelectedIndex() != -1) {

			if (getWindowComponent().hasFellow("editBttn"))
				((Button) getWindowComponent().getFellow("editBttn")).setDisabled(false);

			if (getWindowComponent().hasFellow("deleteBttn"))
				((Button) getWindowComponent().getFellow("deleteBttn")).setDisabled(false);

			if (getWindowComponent().hasFellow("printBttn"))
				((Button) getWindowComponent().getFellow("printBttn")).setDisabled(false);

		} else {
			if (getWindowComponent().hasFellow("editBttn"))
				((Button) getWindowComponent().getFellow("editBttn")).setDisabled(true);

			if (getWindowComponent().hasFellow("deleteBttn"))
				((Button) getWindowComponent().getFellow("deleteBttn")).setDisabled(true);

			if (getWindowComponent().hasFellow("printBttn"))
				((Button) getWindowComponent().getFellow("printBttn")).setDisabled(true);

		}
	}

	protected void securityButtons() {

		if (getWindowComponent().hasFellow("editBttn"))
			((Button) getWindowComponent().getFellow("editBttn")).setAttribute("if",
					"${sec:isAllGranted('ROLE_ADMIN')}");

		if (getWindowComponent().hasFellow("deleteBttn"))
			((Button) getWindowComponent().getFellow("deleteBttn")).setAttribute("if",
					"${sec:isAllGranted('ROLE_ADMIN')}");

	}

	/**
	 * Filtra los datos de la lista. Para esto se requiere que los componentes
	 * empiecen con la palabra "filter" mas el nombre de los atributos
	 * pertenecientes a la entidad. En caso de que el atributo sea a a su vez
	 * otra entidad, este debe contener la anotacion ManyToOne. Ejemplo:
	 * filterCityId.
	 * 
	 * @param event
	 */
	@SuppressWarnings({ "rawtypes" })
	public void buildParameters() {

		Map<String, Object> parameters = new HashMap<String, Object>();
		Class entityClass = null;

		try {
			entityClass = Class.forName(getClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}

		Component element;

		for (Field field : entityClass.getDeclaredFields()) {

			String componentName = "filterBy" + StringUtils.capitalize(field.getName());

			// Si el campo no es una entidad
			if (getWindowComponent().hasFellow(componentName)
					&& field.getAnnotation(javax.persistence.ManyToOne.class) == null) {

				Object value = getValueFromComponent(getWindowComponent().getFellow(componentName));

				if (value != null)
					parameters.put(field.getName(), value);
			}

			// Si el campo es una entidad
			if (getWindowComponent().hasFellow(componentName)
					&& field.getAnnotation(javax.persistence.ManyToOne.class) != null) {

				element = getWindowComponent().getFellow(componentName);

				if (element instanceof org.zkoss.zul.Combobox) {
					if (((Combobox) element).getValue() != null && !"".equals(((Combobox) element).getValue())) {
						// Recupero la entidad guardada en el combobox
						BaseEntity entity = (BaseEntity) ((Combobox) element).getAttribute(SELECTED);
						parameters.put(field.getName(), entity);
					}
				}
			}
		}

		executeAndRenderPagedQuery(parameters);
	}

	protected void executeAndRenderPagedQuery(Map<String, Object> parameters, String sortField, String sortOrder) {
		executeAndRenderPagedQuery(parameters);
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	protected void executeAndRenderPagedQuery(Map<String, Object> parameters) {
		executeAndRenderPagedQuery(parameters, false);
	}

	@SuppressWarnings("unchecked")
	protected void executeAndRenderPagedQuery(Map<String, Object> parameters, boolean multiple) {

		// Ejecuto la consulta
		Integer page = getPagingComponent().getActivePage();
		Integer size = getPagingComponent().getPageSize();

		@SuppressWarnings("rawtypes")
		BaseService<BaseEntity, BaseDao> servicio_base = this.getServiceContacto();
		PagedQueryResponse response = servicio_base.pagedFindAll(page, size, sortField, sortOrder, parameters);

		all = (List<Entity>) response.getResult();

		getPagingComponent().setTotalSize(response.getCount());

		ListModelList<Entity> model = new ListModelList<Entity>(all);
		model.setMultiple(multiple);
		getListComponent().setModel(model);
		getListComponent().setItemRenderer(getListitemRender());
		getWindowComponent().setAttribute(LIST, all);
		getWindowComponent().setAttribute(LIST_COMPONENT, getListComponent());
		getListComponent().renderAll();

	}

	public abstract Acondicionador getAcondicionador();

	/**
	 * Dado un componente obtiene el valor del mismo
	 * 
	 * @param element
	 *            Componente del cual obtener el valor
	 * @return Valor que tenia asignado el componente
	 */
	private Object getValueFromComponent(Component element) {

		if (element == null)
			return null;

		if (element instanceof org.zkoss.zul.Combobox) {
			Combobox component = (Combobox) element;

			if (component.getValue() != null && !"".equals(component.getValue().toString().trim()))
				return component.getValue();
		}

		if (element instanceof org.zkoss.zul.Textbox) {
			Textbox component = (Textbox) element;

			if (component.getValue() != null && !"".equals(component.getValue().toString().trim()))
				return component.getValue();
		}

		if (element instanceof org.zkoss.zul.Datebox) {
			Datebox component = (Datebox) element;

			if (component.getValue() != null && !"".equals(component.getValue().toString().trim()))
				return component.getValue();
		}

		if (element instanceof org.zkoss.zul.Checkbox) {
			Checkbox component = (Checkbox) element;

			if (component.getValue() != null && !"".equals(component.getValue().toString().trim()))
				return component.getValue();
		}

		if (element instanceof org.zkoss.zul.Decimalbox) {
			Decimalbox component = (Decimalbox) element;

			if (component.getValue() != null && !"".equals(component.getValue().toString().trim()))
				return component.getValue();
		}

		if (element instanceof org.zkoss.zul.Doublebox) {
			Doublebox component = (Doublebox) element;

			if (component.getValue() != null && !"".equals(component.getValue().toString().trim()))
				return component.getValue();
		}

		if (element instanceof org.zkoss.zul.Doublespinner) {
			Doublespinner component = (Doublespinner) element;

			if (component.getValue() != null && !"".equals(component.getValue().toString().trim()))
				return component.getValue();
		}

		if (element instanceof org.zkoss.zul.Intbox) {
			Intbox component = (Intbox) element;

			if (component.getValue() != null && !"".equals(component.getValue().toString().trim()))
				return component.getValue();
		}

		if (element instanceof org.zkoss.zul.Longbox) {
			Longbox component = (Longbox) element;

			if (component.getValue() != null && !"".equals(component.getValue().toString().trim()))
				return component.getValue();
		}

		if (element instanceof org.zkoss.zul.Spinner) {
			Spinner component = (Spinner) element;

			if (component.getValue() != null && !"".equals(component.getValue().toString().trim()))
				return component.getValue();
		}

		if (element instanceof org.zkoss.zul.Timebox) {
			Timebox component = (Timebox) element;

			if (component.getValue() != null && !"".equals(component.getValue().toString().trim()))
				return component.getValue();
		}

		return null;
	}

	/**
	 * Componente que muestra el listado obtenido en la consulta
	 * 
	 * @return
	 */
	protected abstract Listbox getListComponent();

	/**
	 * Renderiza el listado obtenido en la busqueda paginada
	 * 
	 * @return
	 */
	protected abstract ListitemRenderer<Contacto> getListitemRender();

	/**
	 * Ruta relativa al ZUL que contiene el formulario de alta/edicion
	 * 
	 * @return
	 */
	protected abstract String getFormPageName();

	/**
	 * Componente que permite paginar la consulta
	 * 
	 * @return
	 */
	protected abstract Paging getPagingComponent();

	/**
	 * Componente que permite asignar la cantidad de filas a obtener en cada
	 * consulta
	 * 
	 * @return
	 */
	protected abstract Combobox getPageSizeComponent();

	public void doCompose(Component comp) throws Exception {

		try {
			@SuppressWarnings("rawtypes")
			Class entityClass = Class.forName(getClassName());
			BaseEntity entity = null;

			// Obtengo la entidad de los argumentos
			if (args != null) {
				entity = (BaseEntity) args.get(SELECTED);
			}
			if (entity != null) {

				if (entity.getClass().getName().equalsIgnoreCase(getClassName())) {

					Component element;
					Object value = null;

					// Recorro los elementos y les seteo tomo los valores de la
					// entidad
					for (Method meth : entityClass.getMethods()) {
						if (meth.getName().length() > 3 && meth.getName().matches("^get.+")
								&& getWindowComponent().hasFellow(meth.getName().substring(3))) {

							element = getWindowComponent().getFellow(meth.getName().substring(3));

							if (element != null) {
								if (entity != null) {
									value = meth.invoke(entity);
								} else {
									value = "";
								}
								if (value != null) {
									if (element instanceof org.zkoss.zul.Combobox) {
										if (meth.getReturnType()
												.getAnnotation(javax.persistence.Entity.class) != null) {

											String query = "FROM " + meth.getReturnType().getName() + " e";

											@SuppressWarnings("unchecked")
											List<BaseEntity> q = getService().findQuery(query);

											((Combobox) element).setModel(new ListModelList<BaseEntity>(q));
											((Combobox) element).setItemRenderer(new ComboitemRenderer<BaseEntity>() {
												@Override
												public void render(Comboitem item, BaseEntity data, int arg)
														throws Exception {
													item.setLabel(String.valueOf(data));
													item.setAttribute(BaseController.ENTITY, data);
													if (data.equals(item.getAttribute(BaseController.SELECTED))) {
														((Combobox) item.getParent()).setValue(data.toString());
													}
												}
											});
											((Combobox) element).addEventListener("onSelect",
													new EventListener<Event>() {
														@Override
														public void onEvent(Event event) throws Exception {
															((Combobox) event.getTarget()).setAttribute(
																	BaseController.SELECTED,
																	((Combobox) event.getTarget()).getSelectedItem()
																			.getAttribute(BaseController.ENTITY));
														}
													});
											((Combobox) element).setAttribute(BaseController.SELECTED, value);
										}
										if (((Combobox) element).getItemCount() > 0) {
											((Combobox) element).setSelectedIndex(0);
										}
									} else if (element instanceof org.zkoss.zul.Textbox) {
										((Textbox) element).setValue(String.valueOf(value));
									} else if (element instanceof org.zkoss.zul.Datebox) {
										((Datebox) element).setValue((Date) value);
									} else if (element instanceof org.zkoss.zul.Decimalbox) {
										((Decimalbox) element).setValue(String.valueOf(value));
									} else if (element instanceof org.zkoss.zul.Doublebox) {
										((Doublebox) element).setValue(Double.valueOf(String.valueOf(value)));
									} else if (element instanceof org.zkoss.zul.Doublespinner) {
										((Doublespinner) element).setValue(Double.valueOf(String.valueOf(value)));
									} else if (element instanceof org.zkoss.zul.Intbox) {
										((Intbox) element).setValue(Integer.valueOf(String.valueOf(value)));
									} else if (element instanceof org.zkoss.zul.Longbox) {
										((Longbox) element).setValue(Long.valueOf(String.valueOf(value)));
									} else if (element instanceof org.zkoss.zul.Spinner) {
										((Spinner) element).setValue(Integer.valueOf(String.valueOf(value)));
									} else if (element instanceof org.zkoss.zul.Timebox) {
										((Timebox) element).setValue((Date) value);
									} else if (element instanceof org.zkoss.zul.Checkbox) {
										((Checkbox) element).setChecked((Boolean) value);
									}
								}
							}
						}
					}
				}
			} else {

				Component element;
				Object value = null;
				// Completo solamente los combobox
				for (Method meth : entityClass.getMethods()) {
					if (meth.getName().length() > 3 && meth.getName().matches("^get.+")
							&& getWindowComponent().hasFellow(meth.getName().substring(3))) {

						element = getWindowComponent().getFellow(meth.getName().substring(3));

						if (element != null) {
							if (entity != null)
								value = meth.invoke(entity);

							if (element instanceof org.zkoss.zul.Combobox) {
								if (meth.getReturnType().getAnnotation(javax.persistence.Entity.class) != null) {

									String query = "FROM " + meth.getReturnType().getName() + " e";

									@SuppressWarnings("unchecked")
									List<BaseEntity> q = getService().findQuery(query);

									if (q != null) {
										((Combobox) element).setModel(new ListModelList<BaseEntity>(q));
									}
									((Combobox) element).setItemRenderer(new ComboitemRenderer<BaseEntity>() {
										@Override
										public void render(Comboitem item, BaseEntity data, int arg) throws Exception {
											item.setLabel(String.valueOf(data));
											item.setAttribute(BaseController.ENTITY, data);
											if (data.equals(item.getAttribute(BaseController.SELECTED))) {
												((Combobox) item.getParent()).setValue(data.toString());
											}
										}
									});
									if (value == null && q != null && q.size() > 0) {
										value = q.get(0);
									}
									((Combobox) element).addEventListener("onSelect", new EventListener<Event>() {
										@Override
										public void onEvent(Event event) throws Exception {
											if (((Combobox) event.getTarget()).getSelectedItem() != null
													&& ((Combobox) event.getTarget()).getSelectedItem()
															.getAttribute(BaseController.ENTITY) != null)
												((Combobox) event.getTarget()).setAttribute(BaseController.SELECTED,
														((Combobox) event.getTarget()).getSelectedItem()
																.getAttribute(BaseController.ENTITY));
										}
									});
									((Combobox) element).setAttribute(BaseController.SELECTED, value);
								}
							}
						}
					}
				}
			}
		} catch (IllegalArgumentException | ClassNotFoundException |

		SecurityException e)

		{
			e.printStackTrace();
		}
	}

	private static final long serialVersionUID = 1L;

	// Obtengo un validador de entidades
	Validator validator = (Validator) Validation.buildDefaultValidatorFactory().getValidator();

	protected void showMessage(String msg) {
		super.showMessage(msg);
	}

	protected void showMessage(String msg, Exception ex) {
		Messagebox.show(msg + ex.getMessage());
		ex.printStackTrace();
	}

	@Listen("onClick = #aceptarBttn")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void accept() {
		try {
			Class entityClass = Class.forName(getClassName());
			BaseEntity entity = null;
			Listbox listbox = ((Listbox) getWindowComponent().getParent().getAttribute(BaseController.LIST_COMPONENT));

			if (listbox != null && listbox.getSelectedItem() != null
					&& listbox.getSelectedItem().getAttribute(BaseController.ENTITY) != null) {
				entity = (BaseEntity) listbox.getSelectedItem().getAttribute(BaseController.ENTITY);
			} else {
				for (Constructor constructor : (entityClass).getConstructors()) {
					if (constructor.getParameterTypes().length == 0) {
						entity = (BaseEntity) constructor.newInstance();
						break;
					}
				}
			}
			Component element;
			Object value = null;
			Boolean isValidProperty = true;

			entityClass.getClass();

			// Recorro los elementos y asigno los valores a la entidad
			for (Method meth : entityClass.getMethods()) {

				if (meth.getName().length() > 3 && meth.getName().matches("^set.+")
						&& getWindowComponent().hasFellow(meth.getName().substring(3))) {

					element = getWindowComponent().getFellow(meth.getName().substring(3));

					// En caso de error, traera el mensaje de error del archivo
					// de internacionalizacion
					// Ej: newOrEditClient.nameError. "newOrEditClient" es el
					// nombre del archivo zul, "name" el nombre de la
					// propiedad y "Error" es para distinguir el tipo de
					// etiqueta
					String labelError = getWindowComponent().getId().replaceAll("Windows", "") + "."
							+ StringUtils.uncapitalize(element.getId()) + "Error";

					if (element != null) {

						if (element instanceof org.zkoss.zul.Combobox) {
							value = ((Combobox) element).getAttribute(BaseController.SELECTED);

							// Busco no se selecciono ninguna entidad muestro
							// error
							if (value == null) {
								isValidProperty = false;
								((Combobox) element).setErrorMessage(Labels.getLabel(labelError));
							}
						} else if (element instanceof org.zkoss.zul.Textbox) {
							if ("".equals(((Textbox) element).getValue()))
								value = null;
							else
								value = ((Textbox) element).getValue();

							// En caso de error, muestro un mensaje el
							// componente
							if (!(validator.validateValue(entity.getClass(),
									StringUtils.uncapitalize(meth.getName().substring(3)), value).isEmpty())) {
								isValidProperty = false;
								((Textbox) element).setErrorMessage(Labels.getLabel(labelError));
							}

						} else if (element instanceof org.zkoss.zul.Intbox) {
							if ("".equals(((Intbox) element).getValue()))
								value = null;
							else
								value = ((Intbox) element).getValue();

							// En caso de error, muestro un mensaje el
							// componente
							if (!(validator.validateValue(entity.getClass(),
									StringUtils.uncapitalize(meth.getName().substring(3)), value).isEmpty())) {
								isValidProperty = false;
								((Intbox) element).setErrorMessage(Labels.getLabel(labelError));
							}

						} else if (element instanceof org.zkoss.zul.Datebox) {
							value = ((Datebox) element).getValue();

							if (value == null) {
								isValidProperty = false;
								((Datebox) element).setErrorMessage(Labels.getLabel(labelError));
							}
						} else if (element instanceof org.zkoss.zul.Decimalbox) {
							value = ((Decimalbox) element).getValue();

							// En caso de error, muestro un mensaje el
							// componente
							if (value == null
									|| !(validator
											.validateValue(entity.getClass(),
													StringUtils.uncapitalize(meth.getName().substring(3)), value)
											.isEmpty())) {
								isValidProperty = false;
								((Decimalbox) element).setErrorMessage(Labels.getLabel(labelError));
							}
						} else if (element instanceof org.zkoss.zul.Doublebox) {
							value = ((Doublebox) element).getValue();
						} else if (element instanceof org.zkoss.zul.Doublespinner) {
							value = ((Doublespinner) element).getValue();
						} else if (element instanceof org.zkoss.zul.Intbox) {
							value = ((Intbox) element).getValue();

							// En caso de error, muestro un mensaje el
							// componente
							if (value == null
									|| !(validator
											.validateValue(entity.getClass(),
													StringUtils.uncapitalize(meth.getName().substring(3)), value)
											.isEmpty())) {
								isValidProperty = false;
								((Intbox) element).setErrorMessage(Labels.getLabel(labelError));
							}
						} else if (element instanceof org.zkoss.zul.Longbox) {
							value = ((Longbox) element).getValue();
						} else if (element instanceof org.zkoss.zul.Spinner) {
							value = ((Spinner) element).getValue();
						} else if (element instanceof org.zkoss.zul.Timebox) {
							value = ((Timebox) element).getValue();
						} else if (element instanceof org.zkoss.zul.Checkbox) {
							value = ((Checkbox) element).isChecked();
						}

						if ("".equals(value)) {
							value = null;
						}
					}
				}
			}
			if (isValidProperty) {
				// Si la entidad existe actualizo, sino creo una nueva
				if (((entity.getPK() instanceof String) && "".equals(entity.getPK()))
						|| ((entity.getPK() instanceof Integer) && Integer.valueOf(0).equals(entity.getPK()))
						|| entity.getPK() == null) {
					getService().create(entity);
					((List<BaseEntity>) getWindowComponent().getParent().getAttribute(BaseController.LIST)).add(entity);
				} else {
					getService().update(entity);
				}

				if (args != null) {
					if ((Boolean) args.get("sendEvent")) {
						Events.sendEvent("onAcceptAddButton", getWindowComponent().getParent(), entity);
					}
				}

				if (listbox != null) {
					listbox.setModel(new ListModelList<BaseEntity>(
							((List) getWindowComponent().getParent().getAttribute(BaseController.LIST))));
				}

				getWindowComponent().onClose();
			}
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | ClassNotFoundException
				| SecurityException | InstantiationException e) {
			e.printStackTrace();
		}
	}

	@Listen("onClick = #volverBttn")
	public void cancel() {
		getWindowComponent().onClose();
	}

	public void setArgs(Map<String, Serializable> args) {
		this.args = args;
	}
}
package romaneo.unificado.controllers;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.util.StringUtils;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Listen;
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
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;

import romaneo.unificado.domain.BaseEntity;

public abstract class BaseFormNuevoController extends BaseController {

	@SuppressWarnings("rawtypes")
	protected Map args;

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
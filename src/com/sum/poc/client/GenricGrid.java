package com.sum.poc.client;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.gwtext.client.core.Connection;
import com.gwtext.client.core.UrlParam;
import com.gwtext.client.data.DateFieldDef;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.FloatFieldDef;
import com.gwtext.client.data.HttpProxy;
import com.gwtext.client.data.IntegerFieldDef;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.data.XmlReader;
import com.gwtext.client.data.event.StoreListenerAdapter;
import com.gwtext.client.pagebus.PageBus;
import com.gwtext.client.pagebus.SubscriptionCallback;
import com.gwtext.client.widgets.PagingToolbar;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.grid.CellMetadata;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.Renderer;
import com.gwtext.client.widgets.layout.FitLayout;
import com.sum.poc.shared.ModelColumn;
import com.sum.poc.shared.ServiceUrls;

public class GenricGrid extends Panel {
	private Store storeGrid;
	private GridPanel entityGrid;
	private PagingToolbar pagingToolbar;
	private Renderer dateRenderer = new Renderer() {

		public String render(Object value, CellMetadata cellMetadata,
				Record record, int rowIndex, int colNum, Store store) {
			Date dt = (Date) value;
			return DateTimeFormat.getFormat("yy/dd/MM").format(dt);
		}
	};

	public GenricGrid() {
		setLayout(new FitLayout());
		setHeight(300);
		
		HttpProxy proxy = new HttpProxy("", Connection.GET);
		RecordDef recordDef = new RecordDef(new FieldDef[] {});
		XmlReader reader = new XmlReader("", recordDef);
		UrlParam[] urlParam = new UrlParam[] {};
		storeGrid = new Store(proxy, reader);
		storeGrid.setBaseParams(urlParam);

		ColumnConfig[] columns = new ColumnConfig[] {};
		ColumnModel columnModel = new ColumnModel(columns);

		entityGrid = new GridPanel(storeGrid, columnModel);
		entityGrid.setLayout(new FitLayout());
		entityGrid.setLoadMask(true);

		pagingToolbar = new PagingToolbar(storeGrid);
		pagingToolbar.setPageSize(10);
		pagingToolbar.setDisplayInfo(true);
		pagingToolbar.setDisplayMsg("Displaying records {0} - {1} of {2}");
		pagingToolbar.setEmptyMsg("No records to display");
		entityGrid.setBottomToolbar(pagingToolbar);

		add(entityGrid);
		
/*		PageBus.subscribe("LOAD.GRID",
			     new SubscriptionCallback() {
					public void execute(String subject, Object message) {
						String entityType = (String)message;
						loadGrid(entityType);
					}
				});
*/	}

	public Store getStore() {
		return storeGrid;
	}

	public void setStore(Store store) {
		this.storeGrid = store;
	}

	public GridPanel getEntityGrid() {
		return entityGrid;
	}

	public void setEntityGrid(GridPanel entityGrid) {
		this.entityGrid = entityGrid;
	}
	

	public PagingToolbar getPagingToolbar() {
		return pagingToolbar;
	}

	public void setPagingToolbar(PagingToolbar pagingToolbar) {
		this.pagingToolbar = pagingToolbar;
	}

	public RecordDef getRecordDef(Record[] records) {
		int counter = 0;
		for (Record record : records) {
			if (isTypeSupported(record.getAsString("@attributeType"))) {
				counter++;
			}
		}
		FieldDef[] fields = new FieldDef[counter];
		counter = 0;
		for (Record record : records) {
			String type = record.getAsString("@attributeType");
			String name = record.getAsString("@attributeDisplayName");
			String mapping = record.getAsString("@attributeName");
			FieldDef fieldDef = getFieldDef(type, name, mapping);
			if (fieldDef != null) {
				fields[counter] = fieldDef;
				counter++;
			}
		}
		RecordDef recordDef = new RecordDef(fields);
		return recordDef;
	}

	public ColumnConfig[] getColumnConfig(Record[] records) {
		int counter = 0;
		for (Record record : records) {
			if (isTypeSupported(record.getAsString("@attributeType"))) {
				counter++;
			}
		}
		ColumnConfig[] config = new ColumnConfig[counter];
		counter = 0;
		for (Record record : records) {
			String type = record.getAsString("@attributeType");
			String name = record.getAsString("@attributeDisplayName");
			String mapping = record.getAsString("@attributeName");
			if (isTypeSupported(type)) {
				config[counter] = new ColumnConfig(name, name, 80);
				counter++;
			}
		}
		return config;
	}

	public boolean isTypeSupported(String type) {
		if ("string".equals(type) || "big_decimal".equals(type)
				|| "date".equals(type) || "integer".equals(type)
				|| "short".equals(type)) {
			return true;
		}
		return false;
	}

	public FieldDef getFieldDef(String type, String name, String mapping) {
		FieldDef fieldDef = null;
		if ("string".equals(type)) {
			fieldDef = new StringFieldDef(name, mapping);
			return fieldDef;
		} else if ("date".equals(type)) {
			fieldDef = new DateFieldDef(name, mapping, "Y-m-d");
			return fieldDef;
		} else if ("integer".equals(type) || "short".equals(type)) {
			fieldDef = new IntegerFieldDef(name, mapping);
			return fieldDef;
		} else if ("big_decimal".equals(type)) {
			fieldDef = new FloatFieldDef(name, mapping);
			return fieldDef;
		}
		return fieldDef;
	}

	public ColumnConfig getColumnConfig(String name, String type) {
		ColumnConfig config = null;
		if ("date".equals(type)) {
			config = new ColumnConfig(name, name, 80, true, dateRenderer);
			return config;
		} else {
			config = new ColumnConfig(name, name, 80);
			return config;
		}
	}

	public void loadGrid(final String entity) {
		String configUrl = ServiceUrls.LOAD_ENTITY_CONFIG.replace("{0}", entity);;
		HttpProxy proxyEmpConfig = new HttpProxy(
				configUrl, Connection.GET);
		XmlReader deptReader = new XmlReader("attribute", new RecordDef(
				new FieldDef[] { new StringFieldDef("@attributeName"),
						new StringFieldDef("@attributeType"),
						new StringFieldDef("@attributeDisplayName"), }));
		Store entityConfig = new Store(proxyEmpConfig, deptReader);
		entityConfig.addStoreListener(new StoreListenerAdapter() {

			public void onLoad(Store store, Record[] records) {
				if (records.length > 0) {
					String entityUrl = ServiceUrls.LOAD_ENTITY_LIST.replace("{0}", entity.toLowerCase());
					String entityUrl2 = entityUrl.replace("{1}", entity); 
					HttpProxy proxy = new HttpProxy(
							entityUrl2, Connection.GET);
					RecordDef recordDef = getRecordDef(records);
					XmlReader reader = new XmlReader(entity.toLowerCase(), recordDef);
					reader.setTotalRecords(ModelColumn.COUNT_XML_READER);
					UrlParam[] urlParam = new UrlParam[] {};
					storeGrid = new Store(proxy, reader);
					storeGrid.setBaseParams(urlParam);

					ColumnConfig[] columns = getColumnConfig(records);
					ColumnModel columnModel = new ColumnModel(columns);
					entityGrid.reconfigure(storeGrid, columnModel);
					pagingToolbar.bind(storeGrid);
					storeGrid.load(0, 10);
				}
			}
		});
		entityConfig.load();
	}
}

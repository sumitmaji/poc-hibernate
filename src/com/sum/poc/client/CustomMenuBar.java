package com.sum.poc.client;

import com.gwtext.client.core.Connection;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.HttpProxy;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.data.XmlReader;
import com.gwtext.client.data.event.StoreListenerAdapter;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.menu.BaseItem;
import com.gwtext.client.widgets.menu.Menu;
import com.gwtext.client.widgets.menu.MenuItem;
import com.gwtext.client.widgets.menu.event.MenuListenerAdapter;
import com.sum.poc.shared.ServiceUrls;

public class CustomMenuBar extends Toolbar {

	EmployeeDetail detail;
	Menu menu = new Menu();
	Store entityConfig = null;

	public CustomMenuBar() {

		ToolbarButton menuButton = new ToolbarButton("Tables");
		menuButton.setMenu(menu);

		HttpProxy proxyEmpConfig = new HttpProxy(ServiceUrls.LOAD_TABLE_CONFIG,
				Connection.GET);
		XmlReader deptReader = new XmlReader("hibernateEntityMetaData",
				new RecordDef(
						new FieldDef[] { new StringFieldDef("@entityName"), }));
		entityConfig = new Store(proxyEmpConfig, deptReader);
		entityConfig.addStoreListener(new StoreListenerAdapter() {

			public void onLoadException(Throwable error) {
				// TODO Auto-generated method stub

			}

			public void onLoad(Store store, Record[] records) {
				if (records.length > 0) {
					for (Record record : records) {
						MenuItem item = new MenuItem();
						item.setText(record.getAsString("@entityName"));
						item.setTitle(record.getAsString("@entityName"));
						menu.addItem(item);
					}
				}
			}

		});

		addButton(menuButton);

		menu.addListener(new MenuListenerAdapter() {
			public void onItemClick(BaseItem item, EventObject e) {
				//PageBus.publish("LOAD.GRID", item.getTitle());
				detail.getEmployeeGrid().loadGrid(item.getTitle());
			}
		});

		entityConfig.load();
	}
	
	public void setEmployeeDetail(EmployeeDetail details){
		this.detail = details;
	}
}

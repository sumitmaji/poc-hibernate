package ${rootPackage}.server.model;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.tuple.entity.EntityMetamodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sum.server.entity.EntityTabFactory;
import com.sum.server.entityImpl.HibernateEntityMetaData;
import ${rootPackage}.server.exception.BusinessException;
import ${rootPackage}.server.response.ListData;
import ${rootPackage}.server.response.Response;

@Controller
public class HibernateEntityController{

	@Autowired
	private SessionFactory sessionFactory;

	@RequestMapping(value = "/data/entites/getEntities", method = RequestMethod.GET)
	public @ResponseBody
	Response<HibernateEntityMetaData> getEntities(
			HibernateEntityMetaData metaData) throws BusinessException {

		Map<String, ClassMetadata> classMetaDataMap = sessionFactory
				.getAllClassMetadata();
		for (Map.Entry<String, ClassMetadata> metaDataMap : classMetaDataMap
				.entrySet()) {
			ClassMetadata classMetadata = metaDataMap.getValue();
			EntityTabFactory.getHibernateEntityTabInstance().enter(
					classMetadata);
		}

		if (metaData.getEntityName() != null) {
			HibernateEntityMetaData metaDataLocal = EntityTabFactory
					.getHibernateEntityTabInstance().getEntityMetaData(
							metaData.getEntityName());
			if (metaDataLocal != null) {
				Response<HibernateEntityMetaData> entitiesResponse = new Response<HibernateEntityMetaData>();
				entitiesResponse.setSuccess(true);
				entitiesResponse.setT(metaDataLocal);
				return entitiesResponse;
			} else {
				throw new BusinessException("No Data Found");
			}
		} else {
			throw new BusinessException("Entity Name is mandatory");
		}
	}

	@RequestMapping(value = "/data/entites/listEntites", method = RequestMethod.GET)
	public @ResponseBody
	Response<ListData<HibernateEntityMetaData>> listEntities(
			HttpServletRequest request) {

		Map<String, ClassMetadata> classMetaDataMap = sessionFactory
				.getAllClassMetadata();
		for (Map.Entry<String, ClassMetadata> metaDataMap : classMetaDataMap
				.entrySet()) {
			ClassMetadata classMetadata = metaDataMap.getValue();
			EntityTabFactory.getHibernateEntityTabInstance().enter(
					classMetadata);
		}

		
		
		HashMap<String, HibernateEntityMetaData> map = EntityTabFactory.getHibernateEntityTabInstance().getMap();
		List<HibernateEntityMetaData> entityList = new ArrayList<HibernateEntityMetaData>(map.values());
		
		ListData<HibernateEntityMetaData> list = new ListData<HibernateEntityMetaData>();
		list.setT(entityList);
		list.setCount(map.size());
		
		Response<ListData<HibernateEntityMetaData>> entityListResponse = new Response<ListData<HibernateEntityMetaData>>();
		entityListResponse.setSuccess(true);
		entityListResponse.setT(list);

		return entityListResponse;

	}

}

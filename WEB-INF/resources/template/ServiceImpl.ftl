package ${package_name};

<#list importList as import>
import ${import};
</#list>

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ${root_package_name}.server.model.${entityName}DAO;
import ${root_package_name}.server.exception.BusinessException;
import ${root_package_name}.server.response.ListData;

@Service("${entity_variable}Service")  
public class ${entityName}ServiceImpl implements ${entityName}Service {

	private static transient Logger log = Logger
			.getLogger(${entityName}ServiceImpl.class);
	@Autowired
	private ${entityName}DAO ${entity_variable}DAO;

	@Transactional
	public <E>${entityName} get${entityName}(E searchValue, String searchAttribute) throws Exception {
		try {

			${entityName} ${entity_variable} = ${entity_variable}DAO.get(searchValue, searchAttribute);
			return ${entity_variable};

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional
	public ListData<${entityName}> list${entityName}(String start, String limit) {
		try {

			ListData<${entityName}> list = new ListData<${entityName}>();
			list.setT(${entity_variable}DAO.list(start, limit));
			list.setCount(${entity_variable}DAO.get${entityName}Count());
			return list;

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}


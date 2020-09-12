package ${package_name};

<#list importList as import>
import ${import};
</#list>
import ${root_package_name}.server.exception.BusinessException;
import java.util.List;

public interface ${entityName}DAO{
	public <E>${entityName} get(E searchValue, String searchAttribute) throws Exception;
	public List<${entityName}> list(String start, String limit) throws BusinessException;
	public Integer get${entityName}Count() throws BusinessException;
}
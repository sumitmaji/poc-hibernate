package ${package_name};
import ${root_package_name}.server.response.ListData;
<#list importList as import>
import ${import};
</#list>

public interface ${entityName}Service {
	public <E>${entityName} get${entityName}(E searchValue, String searchAttribute) throws Exception;
	public ListData<${entityName}> list${entityName}(String start, String limit) ;
}

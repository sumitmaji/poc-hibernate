package com.sum.poc.server.model;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.TablesNamesFinder;

import org.apache.commons.lang.WordUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sum.poc.server.exception.BusinessException;

@Controller
public class SqlController {

	@RequestMapping(value = "/data/query/submitQuery", method = RequestMethod.GET)
	public @ResponseBody
	void getQuery(HttpServletRequest request, HttpServletResponse response)
			throws BusinessException {
		String query = request.getParameter("query");
		try {
			Statement statement = CCJSqlParserUtil.parse(query);
			Select selectStatement = (Select) statement;

			TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
			List<String> tableList = tablesNamesFinder
					.getTableList(selectStatement);

			PlainSelect ps = (PlainSelect) selectStatement.getSelectBody();
			if (ps.getWhere() != null) {
				while (true) {
					Object obj = ps.getWhere();
					if (obj instanceof AndExpression) {

					} else if (true) {

					}
				}
			}

			for (String table : tableList) {
				response.sendRedirect("/xml/data/" + table.toLowerCase()
						+ "/list" + WordUtils.capitalize(table) + "");
			}

		} catch (JSQLParserException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		} catch (IOException e) {
			throw new BusinessException(e.getMessage());
		}
		return;
	}

}

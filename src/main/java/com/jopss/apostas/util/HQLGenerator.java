package com.jopss.apostas.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

public abstract class HQLGenerator {
        
        protected StringBuilder selectQuery = new StringBuilder();
	protected StringBuilder fromQuery = new StringBuilder();
	protected StringBuilder orderQuery = new StringBuilder();
	protected List<String> lWhere = new ArrayList<String>();
	protected List<String> lHaving = new ArrayList<String>();
	protected Map<String, Object> mapParametrosQuery = new HashMap<String, Object>();
	protected List<String> listAttr = new ArrayList<String>();

	public void aplicarFiltros() {
		if (!lWhere.isEmpty()) {
			fromQuery.append(" WHERE ");
			Iterator iWhere = lWhere.iterator();
			while (iWhere.hasNext()) {
				fromQuery.append("(").append(iWhere.next()).append(")");
				if (iWhere.hasNext()) {
					fromQuery.append(" AND ");
				}
			}
		}
	}
        
        public abstract String getQuery();
        
        public String getCountQuery(){
                StringBuilder query = new StringBuilder();
                query.append(" SELECT COUNT(*) ").append(fromQuery);
                return query.toString();
        }

	public void setParameters(Query query) {
		for (String parameter : mapParametrosQuery.keySet()) {
			query.setParameter(parameter, mapParametrosQuery.get(parameter));
		}
	}

	public StringBuilder getFromQuery() {
		return fromQuery;
	}

	public Map<String, Object> getMapParametrosQuery() {
		return mapParametrosQuery;
	}
}

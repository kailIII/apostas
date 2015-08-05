package com.jopss.apostas.util;

import com.jopss.apostas.excecoes.DataNaoPermitidaException;
import java.util.Date;

public class ApostaHQLGenerator extends HQLGenerator {

        public ApostaHQLGenerator() {
                selectQuery.append("SELECT a ");
                fromQuery.append(" FROM Aposta a ");
        }

        @Override
        public String getQuery() {
                StringBuilder query = new StringBuilder();
                query.append(selectQuery).append(fromQuery).append(orderQuery);
                return query.toString();
        }

        public void filtrarPorDatas(Date dataInicial, Date dataFinal){
                if (dataInicial != null && dataFinal != null) {
                        lWhere.add("a.dateFinalizacao BETWEEN :dataInicial AND :dataFinal");
                        mapParametrosQuery.put("dataInicial", DateUtilsApostas.arredondaDataZerandoHora(dataInicial));
                        mapParametrosQuery.put("dataFinal", DateUtilsApostas.arredondaDataComMaximaHora(dataFinal));
                }
        }

        public void ordenarPorDataDesc() {
                orderQuery.append(" ORDER BY a.dateFinalizacao DESC ");
        }

}

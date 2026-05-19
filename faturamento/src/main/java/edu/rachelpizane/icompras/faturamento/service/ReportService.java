package edu.rachelpizane.icompras.faturamento.service;

import edu.rachelpizane.icompras.faturamento.model.Pedido;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class ReportService {
    @Value("classpath:reports/nota-fiscal.jrxml")
    private Resource notaFiscal;

    @Value("classpath:reports/logo.png")
    private Resource logo;

    public byte[] gerarNota(Pedido pedido) {
        try (InputStream inputStream = notaFiscal.getInputStream()) {
            Map<String, Object> params = new HashMap<>();

            params.put("CLIENTE_NOME", pedido.cliente().nome());
            params.put("CLIENTE_CPF", pedido.cliente().cpf());
            params.put("CLIENTE_EMAIL", pedido.cliente().email());
            params.put("CLIENTE_TELEFONE", pedido.cliente().telefone());
            params.put("CLIENTE_LOGRADOURO", pedido.cliente().logradouro());
            params.put("CLIENTE_NUMERO", pedido.cliente().numero());
            params.put("CLIENTE_BAIRRO", pedido.cliente().bairro());

            params.put("PEDIDO_LOGO", logo.getFile().getAbsolutePath());
            params.put("PEDIDO_DATA", pedido.dataPedido());
            params.put("PEDIDO_TOTAL", pedido.total());

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(pedido.itens());

            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

            return  JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}

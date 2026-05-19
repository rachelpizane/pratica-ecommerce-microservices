package edu.rachelpizane.icompras.faturamento.service;

import edu.rachelpizane.icompras.faturamento.bucket.BucketFile;
import edu.rachelpizane.icompras.faturamento.bucket.BucketService;
import edu.rachelpizane.icompras.faturamento.dto.DetalhePedidoDTO;
import edu.rachelpizane.icompras.faturamento.mapper.PedidoMapper;
import edu.rachelpizane.icompras.faturamento.model.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotaFiscalService {

    private final PedidoMapper mapper;
    private final ReportService reportService;
    private final BucketService bucketService;

    public void gerar(DetalhePedidoDTO detalhes) {
        Pedido pedido = mapper.map(detalhes);

        try {

            byte[] bytestNotaFiscal = reportService.gerarNota(pedido);
            String nomeArquivo = String.format("nf_pedido_%d.pdf", pedido.id());

            BucketFile file = new BucketFile(
                    nomeArquivo,
                    new ByteArrayInputStream(bytestNotaFiscal),
                    MediaType.APPLICATION_PDF,
                    bytestNotaFiscal.length);

            bucketService.upload(file);

            log.info("Gerada nota fiscal: {}", file.name());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}

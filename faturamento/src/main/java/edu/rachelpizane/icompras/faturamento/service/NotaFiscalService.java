package edu.rachelpizane.icompras.faturamento.service;

import edu.rachelpizane.icompras.faturamento.bucket.BucketFile;
import edu.rachelpizane.icompras.faturamento.bucket.BucketService;
import edu.rachelpizane.icompras.faturamento.dto.AtualizacaoStatusPedidoDTO;
import edu.rachelpizane.icompras.faturamento.dto.DetalhePedidoDTO;
import edu.rachelpizane.icompras.faturamento.enums.PedidoStatus;
import edu.rachelpizane.icompras.faturamento.mapper.PedidoMapper;
import edu.rachelpizane.icompras.faturamento.model.Pedido;
import edu.rachelpizane.icompras.faturamento.publisher.FaturamentoPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotaFiscalService {

    private final PedidoMapper mapper;
    private final ReportService reportService;
    private final BucketService bucketService;
    private final FaturamentoPublisher publisher;

    private static final String NOME_ARQUIVO = "nf_pedido_%d.pdf";

    public void gerar(DetalhePedidoDTO detalhes) {
        Pedido pedido = mapper.map(detalhes);

        try {
            BucketFile notaFiscal = salvarNotaFiscal(pedido);
            publicarNotaFiscal(pedido, notaFiscal.name());

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private BucketFile salvarNotaFiscal(Pedido pedido) {
        byte[] bytesNotaFiscal = reportService.gerarNota(pedido);

        String nomeArquivo = String.format(NOME_ARQUIVO, pedido.id());

        BucketFile file = new BucketFile(
                nomeArquivo,
                new ByteArrayInputStream(bytesNotaFiscal),
                MediaType.APPLICATION_PDF,
                bytesNotaFiscal.length);

        bucketService.upload(file);

        log.info("Gerada nota fiscal: {}", file.name());

        return file;
    }

    private void publicarNotaFiscal(Pedido pedido, String nomeArquivo) {
        AtualizacaoStatusPedidoDTO atualizacao = new AtualizacaoStatusPedidoDTO(
                pedido.id(),
                PedidoStatus.FATURADO,
                bucketService.getUrl(nomeArquivo)
        );

        publisher.publicar(atualizacao);
    }
}

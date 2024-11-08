package br.com.techchallenge4.msprodutocarga;

import org.springframework.batch.item.ItemProcessor;
import java.time.LocalDateTime;

public class ProdutoProcessor implements ItemProcessor<Produto,Produto> {

    @Override
    public Produto process(Produto item) throws Exception {
        item.setAtivo(true);
        item.setDtImportacao(LocalDateTime.now());
        return item;
    }

}

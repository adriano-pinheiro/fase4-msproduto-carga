package br.com.techchallenge4.msprodutocarga;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoProcessorTest {

    private ProdutoProcessor produtoProcessor;

    @BeforeEach
    void setUp() {
        produtoProcessor = new ProdutoProcessor();
    }

    @Test
    void testProcess() throws Exception {
        // Arrange
        Produto produto = new Produto();
        produto.setAtivo(false); // Valor inicial
        produto.setDtImportacao(null);

        // Act
        Produto result = produtoProcessor.process(produto);

        // Assert
        assertNotNull(result, "O produto processado não deve ser nulo");
        assertTrue(result.isAtivo(), "O produto deve ser marcado como ativo");
        assertNotNull(result.getDtImportacao(), "A data de importação não deve ser nula");

        // Verificar se a data de importação está próxima ao momento atual
        LocalDateTime now = LocalDateTime.now();
        assertTrue(result.getDtImportacao().isBefore(now.plusSeconds(1)) &&
                        result.getDtImportacao().isAfter(now.minusSeconds(1)),
                "A data de importação deve ser definida corretamente");
    }
}

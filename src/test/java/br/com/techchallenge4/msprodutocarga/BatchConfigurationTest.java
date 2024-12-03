package br.com.techchallenge4.msprodutocarga;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BatchConfigurationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private DataSource dataSource;

    private BatchConfiguration batchConfiguration;

    @BeforeEach
    void setUp() {
        batchConfiguration = applicationContext.getBean(BatchConfiguration.class);
    }

    @Test
    void testJobBeanCreation() {
        Job job = batchConfiguration.ProcessarProduto(
                applicationContext.getBean("jobRepository", org.springframework.batch.core.repository.JobRepository.class),
                applicationContext.getBean("steap", Step.class)
        );

        assertThat(job).isNotNull();
        assertThat(job.getName()).isEqualTo("processarProduto");
    }

    @Test
    void testStepBeanCreation() {
        Step step = batchConfiguration.steap(
                applicationContext.getBean("jobRepository", org.springframework.batch.core.repository.JobRepository.class),
                applicationContext.getBean("platformTransactionManager", org.springframework.transaction.PlatformTransactionManager.class),
                applicationContext.getBean("itemReader", ItemReader.class),
                applicationContext.getBean("itemWriter", ItemWriter.class),
                applicationContext.getBean("itemProcessor", ItemProcessor.class)
        );

        assertThat(step).isNotNull();
    }

    @Test
    void testItemReaderBeanCreation() {
        ItemReader<Produto> itemReader = batchConfiguration.itemReader();
        assertThat(itemReader).isNotNull();
    }

    @Test
    void testItemProcessorBeanCreation() {
        ItemProcessor<Produto, Produto> itemProcessor = batchConfiguration.itemProcessor();
        assertThat(itemProcessor).isNotNull();
        assertThat(itemProcessor).isInstanceOf(ProdutoProcessor.class);
    }

    @Test
    void testItemWriterBeanCreation() {
        ItemWriter<Produto> itemWriter = batchConfiguration.itemWriter(dataSource);
        assertThat(itemWriter).isNotNull();
    }
}

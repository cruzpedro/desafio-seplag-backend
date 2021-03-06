package br.com.seplag.service;

import br.com.seplag.integration.Deployments;
import br.com.seplag.util.DBUnitUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class CategoriaServiceTest {

    @Inject
    private CategoriaService service;

    @Deployment
    public static Archive<?> createDeployment() {
        return Deployments.getBaseDeployment("categoria-service.war");
    }

    @Before
    public void initDataset() {
        DBUnitUtils.createDataset("categoria.json");
    }

    @After
    public void clear() {
        DBUnitUtils.deleteDataset("categoria.json");
    }

    @Test
    public void serviceInitialized() {
        assertThat(service, notNullValue());
    }

    @Test
    public void shouldFindAllCategoria (){
        assertThat(service.findAll(), hasSize(4));
    }
}

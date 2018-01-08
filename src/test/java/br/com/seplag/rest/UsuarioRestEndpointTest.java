package br.com.seplag.rest;

import br.com.seplag.integration.Deployments;
import br.com.seplag.util.DBUnitUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URL;

import static com.jayway.restassured.RestAssured.given;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.Matchers.hasItem;

@RunAsClient
@RunWith(Arquillian.class)
public class UsuarioRestEndpointTest {

    private final String BASE_RESOURCE_PATH = "api/usuario/";

    @ArquillianResource
    private URL basePath;

    @Deployment
    public static Archive<?> createDeployment() {
        return Deployments.getBaseDeployment("usuario-rest.war");
    }

    @Before
    public void initDataset() {
        DBUnitUtils.createRemoteDataset(basePath, "usuario.json");
    }

    @After
    public void clear() {
        DBUnitUtils.deleteRemoteDataset(basePath, "usuario.json");
    }

    @Test
    public void shouldListAll() {
        given()
            .when()
            .get(basePath + BASE_RESOURCE_PATH)
            .then()
            .log().all()
            .statusCode(OK.getStatusCode())
            .body("nome", hasItem("Usuario1"))
            .body("nome", hasItem("Usuario2"))
            .body("cpf", hasItem("111.111.111-11"))
            .body("cpf", hasItem("222.222.222-22"));
    }
}

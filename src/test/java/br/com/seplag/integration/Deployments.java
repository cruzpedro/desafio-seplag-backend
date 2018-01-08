package br.com.seplag.integration;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.MavenResolverSystem;

public class Deployments {

    public static WebArchive getBaseDeployment(String name) {
        WebArchive war = ShrinkWrap.create(WebArchive.class, name);
        war.addPackages(true, "br.com.seplag");

        war.addAsResource("beans.xml", "META-INF/beans.xml");
        war.addAsResource("persistence.xml", "META-INF/persistence.xml");
        war.addAsResource("apache-deltaspike.properties", "META-INF/apache-deltaspike.properties");

        war.addAsResource("datasets/categoria.json", "categoria.json");
        war.addAsResource("datasets/tramitacao.json", "tramitacao.json");
        war.addAsResource("datasets/usuario.json", "usuario.json");
        war.addAsResource("datasets/movimento.json", "movimento.json");
        war.addAsResource("datasets/beneficio.json", "beneficio.json");

        MavenResolverSystem resolver = Maven.resolver();

        war.addAsLibraries(resolver.loadPomFromFile("pom.xml").resolve("com.fasterxml.jackson.core:jackson-core").withTransitivity().asFile());
        war.addAsLibraries(resolver.loadPomFromFile("pom.xml").resolve("com.fasterxml.jackson.core:jackson-annotations").withTransitivity().asFile());
        war.addAsLibraries(resolver.loadPomFromFile("pom.xml").resolve("com.fasterxml.jackson.core:jackson-databind").withTransitivity().asFile());
        war.addAsLibraries(resolver.loadPomFromFile("pom.xml").resolve("com.fasterxml.jackson.datatype:jackson-datatype-jsr310").withTransitivity().asFile());
        war.addAsLibraries(resolver.loadPomFromFile("pom.xml").resolve("org.apache.deltaspike.core:deltaspike-core-api").withTransitivity().asFile());
        war.addAsLibraries(resolver.loadPomFromFile("pom.xml").resolve("org.apache.deltaspike.core:deltaspike-core-impl").withTransitivity().asFile());
        war.addAsLibraries(resolver.loadPomFromFile("pom.xml").resolve("org.apache.deltaspike.modules:deltaspike-data-module-api").withTransitivity().asFile());
        war.addAsLibraries(resolver.loadPomFromFile("pom.xml").resolve("org.apache.deltaspike.modules:deltaspike-data-module-impl").withTransitivity().asFile());
        war.addAsLibraries(resolver.loadPomFromFile("pom.xml").resolve("com.javaslang:javaslang").withTransitivity().asFile());
        war.addAsLibraries(resolver.loadPomFromFile("pom.xml").resolve("org.codehaus.jackson:jackson-mapper-asl").withTransitivity().asFile());

        return war;
    }
}

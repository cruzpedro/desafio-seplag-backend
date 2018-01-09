# Desafio SEPLAG Backend #
-----

Esse projeto visa a implementação de um sistema, requisitado na 2° fase do processo seletivo da SEPLAG

Para tal, é necessário algumas configurações minimas para que o projeto seja executado com sucesso. São elas:

## Criação do Banco de Dados ##

No seu PostgreSQL local, é necessário a criação de uma base vazia chamada `seplag`, usando seguinte comando:

Banco da Aplicação
```sql

CREATE DATABASE seplag WITH ENCODING='UTF8';

```

Banco de Testes
```sql

CREATE DATABASE seplag_test WITH ENCODING='UTF8';

```

Feito isso, basta partir para o próximo passo.


## Configuração do DataSource ##

Lembrando que os procedimentos descritos abaixo devem ser realizados com o seu servidor (wildfly local) desligado, no arquivo de configuração do seu **WildFly** ( `standalone.xml` ),
você precisa incluir o seguinte data-source definido abaixo:

Datasource da Aplicação
```xml
<datasource jndi-name="java:jboss/datasources/SeplagDS" pool-name="SeplagDS">
    <connection-url>jdbc:postgresql://localhost:5432/seplag</connection-url>
    <driver>postgres</driver>
    <security>
        <user-name>postgres</user-name>
        <password>postgres</password>
    </security>
    <timeout>
        <blocking-timeout-millis>60000</blocking-timeout-millis>
        <idle-timeout-minutes>20</idle-timeout-minutes>
    </timeout>
    <statement>
        <prepared-statement-cache-size>128</prepared-statement-cache-size>
        <share-prepared-statements>true</share-prepared-statements>
    </statement>
</datasource>
```

Datasource de Testes
```xml
<datasource jndi-name="java:jboss/datasources/SeplagTestDS" pool-name="SeplagTestDS">
    <connection-url>jdbc:postgresql://localhost:5432/seplag_test</connection-url>
    <driver>postgres</driver>
    <security>
        <user-name>postgres</user-name>
        <password>postgres</password>
    </security>
    <timeout>
        <blocking-timeout-millis>60000</blocking-timeout-millis>
        <idle-timeout-minutes>20</idle-timeout-minutes>
    </timeout>
    <statement>
        <prepared-statement-cache-size>128</prepared-statement-cache-size>
        <share-prepared-statements>true</share-prepared-statements>
    </statement>
</datasource>
```

## Configuração diretorio de arquivos ##

Adicionar no standalone do wildfly:
```xml
<host name="default-host" alias="localhost">
    <location name="/desafio-seplag-backend/arquivos" handler="arquivos-content"/>
</host>

<handlers>
    <file name="arquivos-content" directory-listing="true" path="${jboss.home.dir}/standalone/tmp/vfs/arquivos"/>
</handlers>
```

## Carga no banco de dados ##

No projeto existe um arquivo chamado load.sql, usado para dar carga no banco.


## Configuração do Projeto na IDE ##

O projeto é do tipo Maven, então basta importa-lo como tal.


## Execução dos Testes de Integração ##

Para executar os testes, basta roda-lo com o profile Tests usando o seguinte comando:

`mvn clean package install -PTests`

Lembrando que, a variável de ambiente **JBOSS_HOME** deve estar configurada para apontar para o diretório onde o mesmo está instalado.


Após isso, está concluido.
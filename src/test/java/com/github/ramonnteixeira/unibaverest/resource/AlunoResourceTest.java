package com.github.ramonnteixeira.unibaverest.resource;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.ramonnteixeira.unibaverest.model.Aluno;
import java.util.HashMap;
import java.util.Map;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AlunoResourceTest {
    
    @Inject
    private TestRestTemplate restTemplate;

    private static final String BASE_URI = "/api/alunos";
    
    @Test
    public void _000_stupid_lombok() {
        assertThat(Aluno.builder().nome("ramon").toString()).isNotNull();
    }
    
    @Test
    public void _001_adicionaAluno() {
        final Aluno aluno = Aluno
                .builder()
                .nome("Ramon")
                .idade(27)
                .cidade("Tubarao")
                .build();
        
        ResponseEntity<Aluno> response = restTemplate
                .postForEntity(BASE_URI, aluno, Aluno.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        
        Aluno alunoCriado = response.getBody();
        assertThat(alunoCriado.getCodigo()).isNotNull();
        assertThat(alunoCriado.getNome()).isEqualTo("Ramon");
        assertThat(alunoCriado.getCidade()).isEqualTo("Tubarao");
        assertThat(alunoCriado.getIdade()).isEqualTo(27);        
    }

    @Test
    public void _002_editaAlunoComErro() {
        final Aluno aluno = Aluno.builder().build();
        aluno.setCodigo(3L);
        aluno.setNome("Ramon Teste");
        aluno.setIdade(27);
        aluno.setCidade("Tubarao");
        
        ResponseEntity<Aluno> response = restTemplate
                .exchange(BASE_URI + "/1", HttpMethod.PUT, new HttpEntity<>(aluno), Aluno.class);
        
        assertThat(response.getStatusCode().is5xxServerError()).isTrue();        
    }

    @Test
    public void _003_editaAluno() {
        final Aluno aluno = Aluno
                .builder()
                .codigo(1L)
                .nome("Ramon Teste")
                .idade(27)
                .cidade("Tubarao")
                .build();
        
        ResponseEntity<Aluno> response = restTemplate
                .exchange(BASE_URI + "/1", HttpMethod.PUT, new HttpEntity<>(aluno), Aluno.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        
        Aluno alunoCriado = response.getBody();
        assertThat(alunoCriado.getCodigo()).isNotNull();
        assertThat(alunoCriado.getNome()).isEqualTo("Ramon Teste");
        assertThat(alunoCriado.getCidade()).isEqualTo("Tubarao");
        assertThat(alunoCriado.getIdade()).isEqualTo(27);        
    }

    @Test
    public void _004_buscaAluno() {
        ResponseEntity<Aluno> response = restTemplate
                .exchange(BASE_URI + "/1", HttpMethod.GET, null, Aluno.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        
        Aluno alunoCriado = response.getBody();
        assertThat(alunoCriado.getCodigo()).isNotNull();
        assertThat(alunoCriado.getNome()).isEqualTo("Ramon Teste");
        assertThat(alunoCriado.getCidade()).isEqualTo("Tubarao");
    }

    @Test
    public void _005_listaAlunos() {
        ResponseEntity<PageDTO<Aluno>> response = restTemplate
                .exchange(BASE_URI, HttpMethod.GET, null, getePageTypeReference());
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        
        PageDTO<Aluno> alunoCriado = response.getBody();
    }

    @Test
    public void _005_buscaAlunoPorNome() {
        ResponseEntity<PageDTO<Aluno>> response = restTemplate
                .exchange(BASE_URI + "?nome=Ramon", HttpMethod.GET, null, 
                        getePageTypeReference());
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void _006_buscaAlunoInexistente() {
        ResponseEntity<Aluno> response = restTemplate
                .exchange(BASE_URI + "/999", HttpMethod.GET, null, Aluno.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);        
    }

    @Test
    public void _007_deletaAluno() {
        ResponseEntity<Aluno> response = restTemplate
                .exchange(BASE_URI + "/1", HttpMethod.DELETE, null, Aluno.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
    
    private ParameterizedTypeReference<PageDTO<Aluno>> getePageTypeReference() {
        return new ParameterizedTypeReference<PageDTO<Aluno>>(){
        };
    }
        
}

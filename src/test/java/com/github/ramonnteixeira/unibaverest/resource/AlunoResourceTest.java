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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AlunoResourceTest {
    
    @Inject
    private TestRestTemplate restTemplate;

    private static final String BASE_URI = "/api/alunos";
    
    @Test
    public void adicionaAluno() {
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
    
}

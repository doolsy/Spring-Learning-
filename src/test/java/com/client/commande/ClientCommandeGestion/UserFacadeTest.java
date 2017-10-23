package com.client.commande.ClientCommandeGestion;

import com.client.commande.ClientCommandeGestion.config.security.TokenProvider;
import com.client.commande.ClientCommandeGestion.controller.UserFacade;
import com.client.commande.ClientCommandeGestion.controller.utils.RESTServices;
import com.client.commande.ClientCommandeGestion.dao.UserRepository;
import com.client.commande.ClientCommandeGestion.entity.Utilisateur;
import com.client.commande.ClientCommandeGestion.model.DTOFactory;
import com.client.commande.ClientCommandeGestion.model.LoginDTO;
import com.client.commande.ClientCommandeGestion.model.UtilisateurDTO;
import com.client.commande.ClientCommandeGestion.service.UserService;
import com.client.commande.ClientCommandeGestion.service.impl.UserServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by asy on 20/10/2017.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = ClientCommandeGestionApplication.class)
@WebAppConfiguration
public class UserFacadeTest {

    private MockMvc mockMvc;

    private static final String AUTHORITIES_KEY = "auth";
    private String secretKey ="la-cle-tres-tres-secrete";

    private long tokenValidityInMilliseconds ;
    @Mock
    UserService userService;

    @Mock
    TokenProvider tokenProvider = new TokenProvider();

    @InjectMocks
    UserFacade userFacade;

    @Mock
    AuthenticationManager authenticationManager;

    private static final String email = "dsky@gmail.com";
    private static final String passwprd = "tototiretataa";


    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));


    private HttpMessageConverter mappingJackson2HttpMessageConverter =new MappingJackson2HttpMessageConverter();

    @Before
    public void setup() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userFacade).build();//webAppContextSetup(webApplicationContext).build();

        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        tokenValidityInMilliseconds =  ((Integer)(1000 * 36000)).longValue();


    }

    @Test
    public void signedIn() throws Exception {

        TokenProvider tokenProviderGest = new TokenProvider();

        LoginDTO loginDTO= new LoginDTO();
        loginDTO.setEmail(email);
        loginDTO.setPassword(passwprd);

        Authentication principal = new UsernamePasswordAuthenticationToken(email, passwprd, AuthorityUtils.createAuthorityList("USER"));




        Date now = new Date();
        Date validity = new Date(now.getTime() + this.tokenValidityInMilliseconds);

        String authorities = principal.getAuthorities().stream().map(authority -> authority.getAuthority())
                .collect(Collectors.joining(","));

        String token =  Jwts.builder().setId(UUID.randomUUID().toString()).setSubject(principal.getName())
                .setIssuedAt(now).signWith(SignatureAlgorithm.HS512, this.secretKey)
                .setExpiration(validity).claim(AUTHORITIES_KEY,authorities).compact();

        when(authenticationManager.authenticate(Matchers.any())).thenReturn(principal);
        when(tokenProvider.createToken(Matchers.any())).thenReturn(token);

        ResultActions result = mockMvc
                .perform(post(RESTServices.ROOT.PATH_ROOT+ RESTServices.ROOT.AUTHENTICATE)
                        .contentType(contentType)
                        .content(json(loginDTO)))
                .andDo(print())
                .andExpect(status().isOk());

        System.out.println(result.andReturn().getResponse().getContentAsString());

    }

    @Test
    public void inscription() throws Exception {

        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setEmail("omggggg@lezy.fr");utilisateurDTO.setNom("omggggg");
        utilisateurDTO.setMotDePasse("lololala");
        utilisateurDTO.setDateInscription(new java.sql.Date(java.util.Calendar.getInstance().getTime().getTime()));

        Utilisateur utilisateur =DTOFactory.utilisateurDTOToUtilisateur(utilisateurDTO);
        utilisateur.setDateInscription(new java.sql.Date(java.util.Calendar.getInstance().getTime().getTime()));
        utilisateur.setMotDePasse("lololala");


        when(userService.inscription(Matchers.any(Utilisateur.class))).thenReturn(utilisateur);
        when(userService.isEmailExists(Matchers.anyString())) .thenReturn(false);


        ResultActions result = mockMvc
                .perform(post(RESTServices.ROOT.PATH_ROOT + RESTServices.ROOT.USERS.REGISTER)
                        .contentType(contentType)
                        .content(json(utilisateurDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                ;

        System.out.println(result.andReturn().getResponse().getContentAsString());


    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}

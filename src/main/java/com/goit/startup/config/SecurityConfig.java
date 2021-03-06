package com.goit.startup.config;

import com.goit.startup.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 * Spring configuration class.
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.goit.startup.service")
@PropertySource("classpath:security.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Request default success.
     */
    @Value("${request.default-success}")
    String requestDefaultSuccess;
    /**
     * Request prefix for administrators.
     */
    @Value("${request.admin}")
    private String requestAdmin;
    /**
     * Request for authorization.
     */
    @Value("${request.login}")
    private String requestLogin;
    /**
     * Request.access-denied-page.
     */
    @Value("${request.access-denied-page}")
    private String requestAccessDeniedPage;
    /**
     * It is always use default success request.
     */
    @Value("${request.default-success.always}")
    private boolean alwaysUseDefaultSuccess;

    /**
     * Parameter username title.
     */
    @Value("${parameter.username}")
    private String parameterUsername;

    /**
     * Parameter password title.
     */
    @Value("${parameter.password}")
    private String parameterPassword;

    @Value("${parameter.password.encryptionStrength}")
    private int encryptionStrength;
    /**
     * An instance of UserDetailsService for working with registered users.
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * The Method sets users access to pages of the site.
     *
     * @param httpSecurity An instance of {@link HttpSecurity} class
     * @throws Exception An exception that can be thrown by HttpSecurity class methods
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .logout()
                .invalidateHttpSession(false)
                .and()
                .authorizeRequests()
                .antMatchers(this.requestAdmin).hasRole(UserRole.ADMIN.name())
                .antMatchers("/investments/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage(this.requestLogin)
                .usernameParameter(this.parameterUsername)
                .passwordParameter(this.parameterPassword)
                .defaultSuccessUrl(this.requestDefaultSuccess, alwaysUseDefaultSuccess)
                .and()
                .exceptionHandling()
                .accessDeniedPage(this.requestAccessDeniedPage)
                .and()
                .csrf().disable();
    }

    /**
     * Method configures an instance of {@link SimpleUrlAuthenticationSuccessHandler}
     *
     * @return instance of {@link SimpleUrlAuthenticationSuccessHandler}
     */
    @Bean
    public SimpleUrlAuthenticationSuccessHandler successHandler() {
        SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
        successHandler.setUseReferer(true);
        return successHandler;
    }
    /**
     * Method for setting user's roles.
     *
     * @param builder An instance of {@link AuthenticationManagerBuilder} class
     * @throws Exception An exception that can be thrown by AuthenticationManagerBuilder class methods
     */
    @Override
    protected void configure(final AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * Method defines authentication bean
     *
     * @return instance of {@link AuthenticationManager}
     * @throws Exception if authentication fails
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(encryptionStrength);
    }
}
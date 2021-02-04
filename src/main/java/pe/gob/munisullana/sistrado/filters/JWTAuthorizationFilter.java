package pe.gob.munisullana.sistrado.filters;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pe.gob.munisullana.sistrado.entities.Ciudadano;
import pe.gob.munisullana.sistrado.entities.UserType;
import pe.gob.munisullana.sistrado.repositories.CiudadanoRepository;
import pe.gob.munisullana.sistrado.utils.JwtTokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer ";

    private static final Logger log = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CiudadanoRepository ciudadanoRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            log.info("Security filter");
            if (hasJWTToken(request, response)) {
                String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
                String username = jwtTokenUtil.getUsernameFromToken(jwtToken);

                Ciudadano ciudadano = ciudadanoRepository.findByEmail(username);

                if (ciudadano == null || !ciudadano.getEstado().equals(Ciudadano.Estado.ACTIVO)) {
                    SecurityContextHolder.clearContext();
                }

                User user = new User(ciudadano.getEmail(), ciudadano.getClave(), AuthorityUtils
                        .commaSeparatedStringToAuthorityList(UserType.USER_APP.toString()));

                if (jwtTokenUtil.validateToken(jwtToken, user)) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            ciudadano.getEmail(),
                            null,
                            AuthorityUtils
                                    .commaSeparatedStringToAuthorityList(UserType.USER_APP.toString()));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } else {
                    SecurityContextHolder.clearContext();
                }
            } else {
                SecurityContextHolder.clearContext();
            }
            chain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }

    private boolean hasJWTToken(HttpServletRequest request, HttpServletResponse res) {
        String authenticationHeader = request.getHeader(HEADER);
        return authenticationHeader != null && authenticationHeader.startsWith(PREFIX);
    }
}

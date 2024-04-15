package francescoandrisani.progettou5w2.security;

import francescoandrisani.progettou5w2.exceptions.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeaer =request.getHeader("Authorization");
        if(authHeaer == null || !authHeaer.startsWith("Bearer ")) throw new UnauthorizedException("Per favore inserisci il token nell'Authorization Header");

        String accessToken = authHeaer.substring(7);

        jwtTools.verifyToken(accessToken);

        filterChain.doFilter(request, response);

    }

    protected boolean shouldNotFilter(HttpServletRequest request){
        return new AntPathMatcher().match("/login", request.getContextPath());
    }
}

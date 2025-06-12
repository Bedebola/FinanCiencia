package com.financiencia.config;

import com.financiencia.entities.Usuario;
import com.financiencia.jwt.TokenService;
import com.financiencia.repositories.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        String method = request.getMethod();

        if (
                (method.equals("POST") && path.equals("/financiencia/usuarios/novoUsuario")) ||
                        (method.equals("POST") && path.equals("/financiencia/projeto/novo")) ||
                        (method.equals("GET") && path.startsWith("/financiencia/projeto/"))
        ) {
            filterChain.doFilter(request, response);
            return;
        }

        var token = this.recoverToken(request);
        var login = tokenService.validateToken(token);

        if (login != null) {
            Usuario usuario = usuarioRepository.findByEmail(login)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken (HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer", "");

    }
}


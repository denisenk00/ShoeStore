package ua.edu.j2ee.shoestore.config;

import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {NestedRuntimeException.class})
    public ResponseEntity handleRestConflict(HttpServletRequest request, Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(value = {RuntimeException.class, NamingException.class,
            UnsupportedOperationException.class, UsernameNotFoundException.class})
    public ModelAndView handleConflict(HttpServletRequest request, Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", e.getMessage());
        mav.setViewName("error");
        if (e instanceof UsernameNotFoundException) {
            mav.setStatus(HttpStatus.UNAUTHORIZED);
        }
        else {
            mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return mav;
    }

    @ExceptionHandler(value = {Exception.class})
    public ModelAndView handleUnexpectedConflict(HttpServletRequest request, Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "Sorry, something went wrong");
        mav.setViewName("error");
        mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return mav;
    }
}

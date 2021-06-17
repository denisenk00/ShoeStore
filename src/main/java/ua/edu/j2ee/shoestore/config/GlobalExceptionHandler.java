package ua.edu.j2ee.shoestore.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ua.edu.j2ee.shoestore.exceptions.DaoRuntimeException;
import ua.edu.j2ee.shoestore.exceptions.RestServiceException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {RestServiceException.class})
    public ResponseEntity handleRestConflict(HttpServletRequest request, RestServiceException e) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if(e.getFormat().equalsIgnoreCase("xml")) httpHeaders.setContentType(MediaType.APPLICATION_XML);
        else httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(httpHeaders).body(e.getMessage());
    }

    @ExceptionHandler(value = {DaoRuntimeException.class})
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
        mav.addObject("message", "К сожалению, что-то пошло не так...");
        mav.setViewName("error");
        mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return mav;
    }
}
